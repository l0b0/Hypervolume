/**
 * 
 */
package net.l0b0.hypervolume;

import java.util.*;
import java.lang.NullPointerException; // Only for debugging

/**
 * Sorted (according to position in hyperspace) set (only unique objects) of Hypercubes.
 * 
 * If you want to add the "dimensions" variable, take into account that all methods which change
 * the Hypercube set must be overloaded to change this variable. Probably not worth the complexity.
 * 
 * @author Victor Engmark
 */
public class HypercubeCollection implements Hypervolume {
	
	private TreeSet<Hypercube> hypercubes;

	public HypercubeCollection() {
		this.hypercubes = new TreeSet<Hypercube>(Hypercube.POSITION_ORDER);
	}

	/**
	 * @param hypervolumes a sorted set of Hypercubes.
	 */
	public HypercubeCollection(TreeSet<Hypercube> hypercubes) {
		this.hypercubes = new TreeSet<Hypercube>(Hypercube.POSITION_ORDER);
		for (Hypercube hypercube : hypercubes) {
			if (!this.hypercubes.add(hypercube)) {
				System.out.println("Could not add Hypercube: " + hypercube.getPosition().toString());
			}
		}
	}

	/**
	 * @return the hypercubes.
	 */
	public TreeSet<Hypercube> getHypercubes() {
		return this.hypercubes;
	}

	/**
	 * @param hypercubes the hypercubes to set.
	 * @return whether the command succeeded.
	 */
	public boolean setHypercubes(TreeSet<Hypercube> hypercubes) {
		this.hypercubes.clear();
		return this.hypercubes.addAll(hypercubes);
	}

	/**
	 * @return dimension count
	 */
	public int getDimensions() {
		if (this.hypercubes.isEmpty()) {
			return 0;
		} else {
			// The comparator ensures that all members of the collection have the same dimensionality.
			return this.hypercubes.first().getDimensions();
		}
	}

	/**
	 * @param potentialNeighbor set of Hypercubes which may be neighbors to this set.
	 * @return Whether one of the constituent Hypercubes of this object are
	 *         neighbor to one of the Hypercubes of the potential neighbor.
	 */
	public boolean isNeighbor(HypercubeCollection potentialNeighbor) throws ArrayIndexOutOfBoundsException {
		// Empty collections can't be neighbors
		if (this.hypercubes.isEmpty() || potentialNeighbor.getHypercubes().isEmpty()) {
			return false;
		}
		if (this.getDimensions() != potentialNeighbor.getDimensions()) {
			throw new ArrayIndexOutOfBoundsException("Can't compare Hypercubes of different dimensionality");
		}
		// Can't reliably consider overlapping volumes "neighbors"
		if (this.overlaps(potentialNeighbor)) {
			return false;
		}

		// Finished sanity checks
		for (Hypercube cube : this.hypercubes) {
			for (Hypercube neighborCube : potentialNeighbor.getHypercubes()) {
				if (cube.isNeighbor(neighborCube)) {
					return true; // Only need one match
				}
			}
		}
		// No matches found
		return false;
	}
	
	/**
	 * @param potentialNeighbor
	 * @return whether this HypercubeCollection and the potiential neighbor overlap.
	 */
	public boolean overlaps(HypercubeCollection potentialNeighbor) {
		for (Hypercube cube : this.hypercubes) {
			for (Hypercube neighborCube : potentialNeighbor.getHypercubes()) {
				if (cube.compareTo(neighborCube) == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returning an array could be an alternative, but we already have a data structure fit to hold
	 * collections of Hypercubes.
	 * @param cube the Hypercube
	 * @return the neighbors to this Hypercube.
	 */
	public HypercubeCollection getNeighbors(Hypercube cube) {
		if (cube == null) {
			return null;
		}
		// Initalize with first Hypercube
		HypercubeCollection neighbors = new HypercubeCollection();
		for (Hypercube potentialNeighbor : this.getHypercubes()) {
			if (cube.isNeighbor(potentialNeighbor)) {
				neighbors.getHypercubes().add(potentialNeighbor);
			}
		}
		return neighbors;
	}
	
	public HypercubeCollection getIsland() throws NullPointerException {
		final boolean debug = false;
		HypercubeCollection island = new HypercubeCollection(); // Result
		HypercubeCollection tempIsland = new HypercubeCollection();
		HypercubeCollection continent = new HypercubeCollection(this.hypercubes); // Copy which will be destroyed
		Hypercube cube = null; // Needs to be used because .first() can change when items are inserted
		if (debug) System.out.println("Continent size: " + continent.hypercubes.size());
		int panicCounter = 0; // Debugging feature
		
		tempIsland.hypercubes.add(continent.hypercubes.first()); // Initialization

		 while (!tempIsland.hypercubes.isEmpty()) {
			cube = tempIsland.hypercubes.first();
		 	if (debug && panicCounter++ > 100) {
				// Debugging
				System.out.println("Current hypercube: ("
				+ cube.getPosition()[0]
				+ ", "
				+ cube.getPosition()[1]
				+ ")");
				System.out.print("Island (size " + island.hypercubes.size() + "): ");
				for (Hypercube hc : island.hypercubes) {
					System.out.print("("
							+ hc.getPosition()[0]
							+ ", "
							+ hc.getPosition()[1]
							+ "), ");
				}
				System.out.print("\nContinent (size " + continent.hypercubes.size() + "): ");
				for (Hypercube hc : continent.hypercubes) {
					System.out.print("("
							+ hc.getPosition()[0]
							+ ", "
							+ hc.getPosition()[1]
							+ "), ");
				}
				throw new NullPointerException("Failure in island.\n");
			}

			// Add the current cube to the island
			if (!island.hypercubes.add(cube)) {
				if (debug) {
					System.out.println("Can't add existing hypercube to island again: ("
							+ cube.getPosition()[0]
							+ ", "
							+ cube.getPosition()[1]
							+ ")");
				}
			}
			
			// Remove the current cube from continent
			if (!continent.hypercubes.remove(cube)) {
				if (debug) {
					System.out.println("Can't remove non-existing hypercube from continent: ("
							+ cube.getPosition()[0]
							+ ", "
							+ cube.getPosition()[1]
							+ ")");
				}
			}
			
			// Get the neighbors of the current cube
			if (!tempIsland.hypercubes.addAll(continent.getNeighbors(cube).hypercubes)) {
				if (debug && continent.getNeighbors(cube).hypercubes.size() != 0) {
					System.out.println("At least one of the neighbors of this hypercube already exist in the temporary island: ("
							+ cube.getPosition()[0]
							+ ", "
							+ cube.getPosition()[1]
							+ ")");
				}
			}
			
			// Remove the processed cube
			if (!tempIsland.hypercubes.remove(cube)) {
				if (debug) {
					System.out.println("Can't remove non-existing hypercube from temporary island: ("
							+ cube.getPosition()[0]
							+ ", "
							+ cube.getPosition()[1]
							+ ")");
				}
			}
		}
		
		if (debug) System.out.println("Finished island size: " + island.hypercubes.size());
		return island;
	}
	
	/**
	 * Useful validation method for large or multi-dimensional maps, where it may be unfeasible to check
	 * that all tiles are connected properly.
	 * @return a collection of disjoint Hypercube collections
	 */
	public ArrayList<HypercubeCollection> getIslands() { // throws NullPointerException (debug)
		//final boolean debug = false;
		ArrayList<HypercubeCollection> islands = new ArrayList<HypercubeCollection>(); // Result set
		HypercubeCollection continent = new HypercubeCollection(this.hypercubes); // Copy which will be destroyed
		HypercubeCollection island = new HypercubeCollection(); // Single island
		
		//int panicCounter = 0; // Debugging feature

		while (!continent.hypercubes.isEmpty()) {
			/*if (debug && panicCounter++ > 100) {
				// Debugging
				System.out.println("Islands: " + islands.size());
				System.out.println("Continent size: " + continent.hypercubes.size());
				throw new NullPointerException("Failure in islands.\n");
			}*/
			
			// Get the island for the first remaining element in the original collection
			island.hypercubes.clear();
			island.hypercubes.addAll(continent.getIsland().hypercubes);
			
			// Add new island with the result
			islands.add(new HypercubeCollection());
			islands.get(islands.size() - 1).getHypercubes().addAll(island.hypercubes);
			
			// Remove from original collection
			// TODO Remove island variable and instead remove the last of the islands here. Optimizes a bit.
			continent.hypercubes.removeAll(island.hypercubes);
		}
		//if (debug) System.out.println("Finished islands!\n");
		return islands;
	}
	
	/**
	 * Note that the borders may be disjoint, if the collection has islands.
	 * @return the "border" of the collection, i.e., all Hypercubes which have less than 2^dimension neighbors.
	 */
	public HypercubeCollection getBorder() {
		HypercubeCollection border = new HypercubeCollection();
		int maxNeighbors = 2*this.getDimensions(); // Can have only two neighbors in each direction
		for (Hypercube cube : this.hypercubes) {
			if (this.getNeighbors(cube).hypercubes.size() != maxNeighbors) {
				border.hypercubes.add(cube);
			}
		}
		return border;
	}
}