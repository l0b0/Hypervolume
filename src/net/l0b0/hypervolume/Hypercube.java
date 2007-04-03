package net.l0b0.hypervolume;

import java.util.*;

/**
 * @author Victor Engmark
 * 
 * N-dimensional cube with all sides of length 1. The only properties are thus
 * the position and the implicit dimension count.
 */
public class Hypercube implements Hypervolume, Comparable<Hypercube> {

	private final int[] position;
	private final int dimensions;

	public static final Comparator<Hypercube> POSITION_ORDER = 
		new Comparator<Hypercube>() {
		public int compare(Hypercube h1, Hypercube h2) throws ArrayIndexOutOfBoundsException {
	    	// Fail-fast check
	    	if (h1.getPosition().length != h2.getPosition().length) {
	    		throw new ArrayIndexOutOfBoundsException("Cannot compare Hypercubes of different dimensionality.");
	    	}
	    	// Look in the order of dimensions
	    	for (int dimension = 0; dimension < h1.getPosition().length; dimension++) {
	    		if (h1.position[dimension] < h2.getPosition()[dimension]) {
	    			return -1; // Before
	    		} else if (h1.getPosition()[dimension] > h2.getPosition()[dimension]) {
	    			return 1; // After
	    		}
	    	}
	    	return 0; // The Hypercubes occupy the same space
		}
	};

	/**
	 * @param position Location coordinates in all dimensions
	 */
	public Hypercube(int[] position) throws NullPointerException {
		if (position == null) {
			throw new NullPointerException(); // Can't have a Hypercube without a position
		}
		this.position = position;
		this.dimensions = position.length;
	}
	
	/**
	 * @return the position
	 */
	public int[] getPosition() {
		return this.position;
	}

	/**
	 * @return dimension count
	 */
	public int getDimensions() {
		return this.dimensions;			
	}

    /* Must be equivalent to the result of the equals() method, to avoid funny business when used in Collections.
     * 
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Hypercube hypercube) throws ArrayIndexOutOfBoundsException {
    	// Avoid throwing this later
    	if (this.position.length != hypercube.getPosition().length) {
    		throw new ArrayIndexOutOfBoundsException("Cannot compare Hypercubes of different dimensionality.");
    	}
    	// Look in the order of dimensions
    	for (int dimension = 0; dimension < this.position.length; dimension++) {
    		if (this.position[dimension] < hypercube.getPosition()[dimension]) {
    			return -1; // Before
    		} else if (this.position[dimension] > hypercube.getPosition()[dimension]) {
    			return 1; // After
    		}
    	}
    	return 0; // The Hypercubes occupy the same space
    }
    
	/**
	 * @param potentialNeighbor
	 * @return Whether all except (at least) one of the dimensional positions are equal,
	 *         the unequal one(s) being only one distance unit away (that is, one
	 *         of their surfaces touch). Uses the "long" datatype to avoid integer overflow.
	 * @throws DimensionException
	 */
	public boolean isNeighbor(Hypercube potentialNeighbor) throws ArrayIndexOutOfBoundsException {
		// Sanity check - This can be made more correct (but slower) if you use max() in deciding the upper
		// limit of the dimension. That will force an exception, but only after at least one iteration.
		if (this.getDimensions() != potentialNeighbor.getDimensions()) {
			throw new ArrayIndexOutOfBoundsException("Cannot compare hypercubes of different dimensionality.");
		}
		int[] neighborPosition = potentialNeighbor.getPosition();
		boolean foundNeighbor = false;
		for (int dimension = 0; dimension < this.getDimensions(); dimension++) {
			if (Math.abs((long)neighborPosition[dimension] - (long)this.position[dimension]) > 1) {
				// Too distant
				return false;
			}
			if (Math.abs((long)neighborPosition[dimension] - (long)this.position[dimension]) == 1) { 
				// Neighbor
				if (foundNeighbor) {
					// 1 distant in two dimensions; not neighbor (e.g., upper left tile)
					return false; 
				} else {
					// Neighbor in one dimension
					foundNeighbor = true;
				}
			}
		}
		return foundNeighbor;
	}
}
