package net.l0b0.hypervolume;

import junit.framework.TestCase;

/**
 * @author Victor Engmark
 *
 */
public class HypercubeTest extends TestCase {
	
	private final Hypercube nullCube = new Hypercube(new int[]{});

	private final Hypercube neighbor1d = new Hypercube(new int[]{1}); // Neighbor to center
	private final Hypercube[] hypercubes1d = new Hypercube[]{
			new Hypercube(new int[]{Integer.MIN_VALUE}),
			new Hypercube(new int[]{0}),
			new Hypercube(new int[]{Integer.MAX_VALUE})
			};

	private final Hypercube neighbor2d = new Hypercube(new int[]{0, 1}); // Neighbor to center
	private final Hypercube[] hypercubes2d = new Hypercube[]{
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, 0}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{0,                 Integer.MIN_VALUE}),
			new Hypercube(new int[]{0,                 0}),
			new Hypercube(new int[]{0,                 Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, 0}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE})
			};

	private final Hypercube neighbor3d = new Hypercube(new int[]{0, 0, 1}); // Neighbor to center
	private final Hypercube[] hypercubes3d = new Hypercube[]{
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, 0}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, 0,                 Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, 0,                 0}),
			new Hypercube(new int[]{Integer.MIN_VALUE, 0,                 Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{0,                 Integer.MIN_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{0,                 Integer.MIN_VALUE, 0}),
			new Hypercube(new int[]{0,                 Integer.MIN_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{0,                 0,                 Integer.MIN_VALUE}),
			new Hypercube(new int[]{0,                 0,                 0}),
			new Hypercube(new int[]{0,                 0,                 Integer.MAX_VALUE}),
			new Hypercube(new int[]{0,                 Integer.MAX_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{0,                 Integer.MAX_VALUE, 0}),
			new Hypercube(new int[]{0,                 Integer.MAX_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, 0,                 Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, 0,                 0}),
			new Hypercube(new int[]{Integer.MAX_VALUE, 0,                 Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, 0}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE})
			};
	
	private final Hypercube neighbor7d = new Hypercube(new int[]{0, 0, 1, 0, 0, 0, 0}); // Neighbor to center
	private final Hypercube[] hypercubes7d = new Hypercube[]{
			new Hypercube(new int[]{0,                 0,                 0,                 0,                 0,                 0,                 0}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}),
			new Hypercube(new int[]{Integer.MIN_VALUE, 0,                 0,                 Integer.MIN_VALUE, 0,                 Integer.MIN_VALUE, 0                }),
			new Hypercube(new int[]{0,                 Integer.MAX_VALUE, 0,                 0,                 Integer.MAX_VALUE, 0,                 0                }),
			new Hypercube(new int[]{Integer.MIN_VALUE, 0,                 Integer.MIN_VALUE, Integer.MAX_VALUE, 0,                 Integer.MIN_VALUE, Integer.MAX_VALUE})
			};
	private final Hypercube random7d = new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -25,     0, Integer.MAX_VALUE,     100 });
	private final Hypercube[] hypercubes7dNeighbors = new Hypercube[]{ // All possible neighbors to random7d
			new Hypercube(new int[]{Integer.MIN_VALUE + 1, 0,  0,  -25, 0, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     1,  0,  -25, 0, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     -1, 0,  -25, 0, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  1,  -25, 0, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  -1, -25, 0, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -24, 0, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -26, 0, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -25, -1,Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -25, 1, Integer.MAX_VALUE,     100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -25, 0, Integer.MAX_VALUE - 1, 100}),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -25, 0, Integer.MAX_VALUE,     99 }),
			new Hypercube(new int[]{Integer.MIN_VALUE,     0,  0,  -25, 0, Integer.MAX_VALUE,     101})
			};
	// Rooms
	private final Hypercube[] roomA = new Hypercube[]{
			new Hypercube(new int[]{0, 0}),
			new Hypercube(new int[]{0, 1}),
			new Hypercube(new int[]{1, 1})
			};
	private final Hypercube[] roomB = new Hypercube[]{
			new Hypercube(new int[]{1, 0}),
			new Hypercube(new int[]{2, 0}),
			new Hypercube(new int[]{3, 0}),
			new Hypercube(new int[]{2, 1}),
			new Hypercube(new int[]{5, 1}),
			new Hypercube(new int[]{6, 1}),
			new Hypercube(new int[]{2, 2}),
			new Hypercube(new int[]{3, 2}),
			new Hypercube(new int[]{4, 2}),
			new Hypercube(new int[]{5, 2}),
			new Hypercube(new int[]{6, 2}),
			};
	private final Hypercube[] roomC = new Hypercube[]{
			new Hypercube(new int[]{4, 0}),
			new Hypercube(new int[]{5, 0}),
			new Hypercube(new int[]{6, 0}),
			new Hypercube(new int[]{3, 1}),
			new Hypercube(new int[]{4, 1}),
			};
	private final Hypercube[] roomD = new Hypercube[]{
			new Hypercube(new int[]{0, 2}),
			new Hypercube(new int[]{1, 2}),
			new Hypercube(new int[]{0, 3}),
			new Hypercube(new int[]{1, 3}),
			new Hypercube(new int[]{2, 3}),
			new Hypercube(new int[]{3, 3}),
			};
	private final Hypercube[] roomE = new Hypercube[]{
			new Hypercube(new int[]{4, 3}),
			new Hypercube(new int[]{5, 3}),
			new Hypercube(new int[]{6, 3}),
			};
	
	/**
	 * Make sure that Hypercubes have the right position.
	 */
	public void testGetPosition() {
		assertTrue("This Hypercube should have no position", nullCube.getPosition().length == 0);
		assertTrue("This Hypercube should be in position (1)", neighbor1d.getPosition()[0] == 1);
		assertTrue("This Hypercube should be in position (0, 1)", neighbor2d.getPosition()[0] == 0 && neighbor2d.getPosition()[1] == 1);
		assertTrue("This Hypercube should be in position (0, 0, 1)", neighbor3d.getPosition()[0] == 0 && neighbor3d.getPosition()[1] == 0 && neighbor3d.getPosition()[2] == 1);
	}
	
	/**
	 * Make sure that Hypercubes have the correct dimensionality.
	 */
	public void testGetDimensions() {
		assertTrue("This Hypercube should be 1-dimensional", hypercubes1d[0].getDimensions() == 1);
		assertTrue("This Hypercube should be 2-dimensional", hypercubes2d[0].getDimensions() == 2);
		assertTrue("This Hypercube should be 3-dimensional", hypercubes3d[0].getDimensions() == 3);
		assertTrue("This Hypercube should be 7-dimensional", hypercubes7d[0].getDimensions() == 7);
	}
	
	/**
	 * Make sure that the compareTo and equals functions work in 1 dimension.
	 */
	public void testCompareTo1d() {
		try {
			assertTrue("The results of equals() and compareTo() must agree.",
					neighbor1d.equals(neighbor1d) && neighbor1d.compareTo(neighbor1d) == 0);

			for (Hypercube cube : hypercubes1d) {
				for (Hypercube otherCube : hypercubes1d) {
					assertTrue("The results of equals() and compareTo() must agree.",
							(cube.equals(otherCube) && cube.compareTo(otherCube) == 0)
							|| (!cube.equals(otherCube) && cube.compareTo(otherCube) != 0)
							);
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("The Hypercubes are of different dimensions.");
		}
	}
	
	/**
	 * Make sure that the compareTo and equals functions work in 2 dimensions.
	 */
	public void testCompareTo2d() {
		try {
			assertTrue("The results of equals() and compareTo() must agree.",
					hypercubes2d[1].equals(hypercubes2d[1]) && hypercubes2d[1].compareTo(hypercubes2d[1]) == 0);

			for (Hypercube cube : hypercubes2d) {
				for (Hypercube otherCube : hypercubes2d) {
					assertTrue("The results of equals() and compareTo() must agree.",
							(cube.equals(otherCube) && cube.compareTo(otherCube) == 0)
							|| (!cube.equals(otherCube) && cube.compareTo(otherCube) != 0)
							);
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("The Hypercubes are of different dimensions.");
		}
	}
	
	/**
	 * Make sure that the compareTo and equals functions work in 3 dimensions.
	 */
	public void testCompareTo3d() {
		try {
			assertTrue("The results of equals() and compareTo() must agree.",
					hypercubes3d[3].equals(hypercubes3d[3]) && hypercubes3d[3].compareTo(hypercubes3d[3]) == 0);

			for (Hypercube cube : hypercubes3d) {
				for (Hypercube otherCube : hypercubes3d) {
					assertTrue("The results of equals() and compareTo() must agree.",
							(cube.equals(otherCube) && cube.compareTo(otherCube) == 0)
							|| (!cube.equals(otherCube) && cube.compareTo(otherCube) != 0)
							);
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("The Hypercubes are of different dimensions.");
		}
	}
	
	/**
	 * Make sure that the compareTo function works in 7 dimensions.
	 */
	public void testCompareTo7d() {
		try {
			assertTrue("A Hypercube should overlap itself.",
					hypercubes7d[3].equals(hypercubes7d[3]) && hypercubes7d[3].compareTo(hypercubes7d[3]) == 0);

			for (Hypercube cube : hypercubes7dNeighbors) {
				for (Hypercube otherCube : hypercubes7d) {
					if (cube.equals(otherCube)) {
						fail("The Hypercubes are from different sets.");
					} else {
						assertFalse("A Hypercube should not overlap another one - They are all in different positions.",
								cube.compareTo(otherCube) == 0);
					}
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("The Hypercubes are of different dimensions.");
		}
	}
	
	/**
	 * Make sure that the method for detecting neighbors works correctly in 1 dimension.
	 */
	public void testIsNeighbor1d() {
		try {
			for (Hypercube cube : hypercubes1d) { // None of these should overlap
				for (Hypercube otherCube : hypercubes1d) {
					if (cube.equals(otherCube)) { // Object comparison
						assertFalse("A Tile is not its own neighbor.",
								cube.isNeighbor(otherCube));
					} else {
						assertFalse("These should not be neighbors: (" + cube.getPosition()[0] + ") ("
								+ otherCube.getPosition()[0] + ").",
								cube.isNeighbor(otherCube));
					}
				}
			}
			assertTrue("These should be neighbors.", hypercubes1d[1].isNeighbor(neighbor1d));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("The Tiles are of different dimensions.");
		}
	}
	
	/**
	 * Make sure that the method for detecting neighbors works correctly in 2 dimensions.
	 */
	public void testIsNeighbor2d() {
		try {
			for (Hypercube cube : hypercubes2d) { // None of these should overlap
				for (Hypercube otherCube : hypercubes2d) {
					if (cube.equals(otherCube)) { // Object comparison
						assertFalse("A Tile is not its own neighbor.",
								cube.isNeighbor(otherCube));
					} else {
						assertFalse("These should not be neighbors: (" + cube.getPosition()[0] + "," + cube.getPosition()[1] + ") ("
								+ otherCube.getPosition()[0] + "," + otherCube.getPosition()[1] + ").",
								cube.isNeighbor(otherCube));
					}
				}
			}
			assertTrue("These should be neighbors.", hypercubes2d[4].isNeighbor(neighbor2d));
			
			// Room tests (all relationships inside rooms)
			// Room A
			assertTrue("These should be neighbors.", roomA[0].isNeighbor(roomA[1]));
			assertFalse("These should not be neighbors.", roomA[0].isNeighbor(roomA[2]));
			assertTrue("These should be neighbors.", roomA[1].isNeighbor(roomA[0]));
			assertTrue("These should be neighbors.", roomA[1].isNeighbor(roomA[2]));
			assertFalse("These should not be neighbors.", roomA[2].isNeighbor(roomA[0]));
			assertTrue("These should be neighbors.", roomA[2].isNeighbor(roomA[1]));
			// Rooms A/B
			assertTrue("These should be neighbors.", roomA[0].isNeighbor(roomB[0]));
			assertTrue("These should be neighbors.", roomB[0].isNeighbor(roomA[0]));
			assertFalse("These should not be neighbors.", roomA[0].isNeighbor(roomB[1]));
			assertFalse("These should not be neighbors.", roomB[1].isNeighbor(roomA[0]));
			assertTrue("These should be neighbors.", roomA[2].isNeighbor(roomB[3]));
			for (Hypercube cube : roomA) {
				for (Hypercube potentialNeighbor : roomE) {
					assertFalse("These should not be neighbors.", cube.isNeighbor(potentialNeighbor));
					assertFalse("These should not be neighbors.", potentialNeighbor.isNeighbor(cube));
				}
				for (Hypercube potentialNeighbor : roomC) {
					assertFalse("These should not be neighbors.", cube.isNeighbor(potentialNeighbor));
					assertFalse("These should not be neighbors.", potentialNeighbor.isNeighbor(cube));
				}
			}
			assertTrue("These should be neighbors.", roomB[3].isNeighbor(roomC[3]));
			assertFalse("These should not be neighbors.", roomB[3].isNeighbor(roomC[4]));
			assertTrue("These should be neighbors.", roomC[4].isNeighbor(roomB[4]));
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("The Tiles are of different dimensions.");
		}
	}
	
	/**
	 * Make sure that the method for detecting neighbors works correctly in 3 dimensions.
	 */
	public void testIsNeighbor3d() {
		try {
			for (Hypercube cube : hypercubes3d) { // None of these should overlap
				for (Hypercube otherCube : hypercubes3d) {
					if (cube.equals(otherCube)) { // Object comparison
						assertFalse("A Tile is not its own neighbor.",
								cube.isNeighbor(otherCube));
					} else {
						assertFalse("These should not be neighbors: (" + cube.getPosition()[0] + "," + cube.getPosition()[1] + "," + cube.getPosition()[2] + ") ("
								+ otherCube.getPosition()[0] + "," + otherCube.getPosition()[1] + "," + otherCube.getPosition()[2] + ").",
								cube.isNeighbor(otherCube));
					}
				}
			}
			assertTrue("These should be neighbors.", hypercubes3d[13].isNeighbor(neighbor3d));
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("The Tiles are of different dimensions.");
		}
	}
	
	/**
	 * Make sure that the method for detecting neighbors works correctly in 7 dimensions.
	 */
	public void testIsNeighbor7d() {
		try {
			for (Hypercube cube : hypercubes7d) { // None of these should overlap
				for (Hypercube otherCube : hypercubes7d) {
					if (cube.equals(otherCube)) { // Object comparison
						assertFalse("A Tile is not its own neighbor.",
								cube.isNeighbor(otherCube));
					} else {
						assertFalse("These should not be neighbors: (" + cube.getPosition()[0] + "," + cube.getPosition()[1] + "," + cube.getPosition()[2] + "," + cube.getPosition()[3] + "," + cube.getPosition()[4] + "," + cube.getPosition()[5] + "," + cube.getPosition()[6] + ") ("
								+ otherCube.getPosition()[0] + "," + otherCube.getPosition()[1] + "," + otherCube.getPosition()[2] + "," + otherCube.getPosition()[3] + "," + otherCube.getPosition()[4] + "," + otherCube.getPosition()[5] + "," + otherCube.getPosition()[6] + ").",
								cube.isNeighbor(otherCube));
					}
				}
			}
			assertTrue("These should be neighbors.", hypercubes7d[0].isNeighbor(neighbor7d));
			for (Hypercube neighbor : hypercubes7dNeighbors) {
				assertTrue("These should be neighbors.", random7d.isNeighbor(neighbor));
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("The Tiles are of different dimensions.");
		}
	}
	
	/**
	 * All these tests should fail - They are comparing Hypercubes which have different dimensionality.
	 */
	public void testIsNeighborNd() {
		/* Must test checks in both "directions", to see if ArrayIndexOutOfBoundsException is thrown.
		 * With the current (optimized) implementation, if the exception is not thrown, and the object set is smaller
		 * than the comparison set, no exception will result, even though a sane result cannot be achieved.
		 */ 
		try {
			assertTrue("This should've generated an exception - A tile cannot be compared to an object with more dimensions.",
					neighbor1d.isNeighbor(neighbor2d));
			fail("The Tiles are of different dimensions. This point should not be reached.");
		} catch(ArrayIndexOutOfBoundsException e) {
		}
		try {
			assertTrue("This should've generated an exception - A tile cannot be compared to an object with more dimensions.",
					neighbor2d.isNeighbor(neighbor1d));
			fail("The Tiles are of different dimensions. This point should not be reached.");
		} catch(ArrayIndexOutOfBoundsException e) {
		}
		try {
			assertTrue("This should've generated an exception - A tile cannot be compared to an object with more dimensions.",
					neighbor2d.isNeighbor(neighbor3d));
			fail("The Tiles are of different dimensions. This point should not be reached.");
		} catch(ArrayIndexOutOfBoundsException e) {
		}
		try {
			assertTrue("This should've generated an exception - A tile cannot be compared to an object with fewer dimensions.",
					neighbor7d.isNeighbor(neighbor3d));
			fail("The Tiles are of different dimensions. This point should not be reached.");
		} catch(ArrayIndexOutOfBoundsException e) {
		}
	}
}
