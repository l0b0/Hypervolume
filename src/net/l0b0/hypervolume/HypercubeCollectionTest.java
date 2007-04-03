/**
 * 
 */
package net.l0b0.hypervolume;


import junit.framework.TestCase;
import java.util.*;

import org.junit.After;
import org.junit.Before;

/**
 * @author Victor Engmark
 *
 */
public class HypercubeCollectionTest extends TestCase {

	// Variables
	private HypercubeCollection nullCollection = new HypercubeCollection();
	private HypercubeCollection corridorA = new HypercubeCollection();
	private HypercubeCollection corridorB = new HypercubeCollection();
	private HypercubeCollection corridorC = new HypercubeCollection();
	private HypercubeCollection duplicateCollection = new HypercubeCollection();
	
	// Rooms (X, Y coordinates)
	private HypercubeCollection roomA = new HypercubeCollection();
	private HypercubeCollection roomB = new HypercubeCollection();
	private HypercubeCollection roomC = new HypercubeCollection();
	private HypercubeCollection roomD = new HypercubeCollection();
	private HypercubeCollection roomE = new HypercubeCollection();
	private HypercubeCollection roomF = new HypercubeCollection();
	private HypercubeCollection[] house = new HypercubeCollection[]{roomA, roomB, roomC, roomD, roomE, roomF};
	
	// 4-dimensional cubes
	private HypercubeCollection room4d = new HypercubeCollection();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		assertTrue("It should be possible to add this Hypercube.",
				corridorA.getHypercubes().add(new Hypercube(new int[]{Integer.MIN_VALUE})));
		assertTrue("It should be possible to add this Hypercube.",
				corridorA.getHypercubes().add(new Hypercube(new int[]{1})));
		assertTrue("It should be possible to add this Hypercube.",
				corridorA.getHypercubes().add(new Hypercube(new int[]{2})));
		assertTrue("It should be possible to add this Hypercube.",
				corridorA.getHypercubes().add(new Hypercube(new int[]{Integer.MAX_VALUE})));

		assertTrue("It should be possible to add this Hypercube.",
				corridorB.getHypercubes().add(new Hypercube(new int[]{0})));
		assertTrue("It should be possible to add this Hypercube.",
				corridorB.getHypercubes().add(new Hypercube(new int[]{1})));

		assertTrue("It should be possible to add this Hypercube.",
				corridorC.getHypercubes().add(new Hypercube(new int[]{3})));
		assertTrue("It should be possible to add this Hypercube.",
				corridorC.getHypercubes().add(new Hypercube(new int[]{4})));
		assertTrue("It should be possible to add this Hypercube.",
				corridorC.getHypercubes().add(new Hypercube(new int[]{5})));
		assertTrue("It should be possible to add this Hypercube.",
				corridorC.getHypercubes().add(new Hypercube(new int[]{6})));

		// Rooms (X, Y coordinates)
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{0, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{0, 1})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{0, 2})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{0, 3})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{1, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{1, 1})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{1, 2})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{1, 3})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{1, 4})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{2, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{2, 1})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{3, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{3, 1})));
		assertTrue("It should be possible to add this Hypercube.",
				roomA.getHypercubes().add(new Hypercube(new int[]{3, 2})));

		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{0, 4})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{0, 5})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{0, 6})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{1, 5})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{2, 2})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{2, 3})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{2, 4})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{2, 5})));
		assertTrue("It should be possible to add this Hypercube.",
				roomB.getHypercubes().add(new Hypercube(new int[]{3, 3})));

		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{0, 7})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{0, 8})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{0, 9})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{1, 6})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{1, 7})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{1, 8})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{2, 6})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{3, 4})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{3, 5})));
		assertTrue("It should be possible to add this Hypercube.",
				roomC.getHypercubes().add(new Hypercube(new int[]{3, 6})));

		assertTrue("It should be possible to add this Hypercube.",
				roomD.getHypercubes().add(new Hypercube(new int[]{1, 9})));
		assertTrue("It should be possible to add this Hypercube.",
				roomD.getHypercubes().add(new Hypercube(new int[]{2, 7})));
		assertTrue("It should be possible to add this Hypercube.",
				roomD.getHypercubes().add(new Hypercube(new int[]{2, 8})));
		assertTrue("It should be possible to add this Hypercube.",
				roomD.getHypercubes().add(new Hypercube(new int[]{2, 9})));
		assertTrue("It should be possible to add this Hypercube.",
				roomD.getHypercubes().add(new Hypercube(new int[]{3, 7})));

		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{3, 8})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{3, 9})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 1})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 2})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 3})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 4})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 5})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 6})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 7})));
		assertTrue("It should be possible to add this Hypercube.",
				roomE.getHypercubes().add(new Hypercube(new int[]{4, 8})));

		assertTrue("It should be possible to add this Hypercube.",
				roomF.getHypercubes().add(new Hypercube(new int[]{4, 9})));

		assertTrue("It should be possible to add this Hypercube.",
				duplicateCollection.getHypercubes().add(new Hypercube(new int[]{Integer.MIN_VALUE, 0, 1, 2, 3, Integer.MAX_VALUE})));
		assertTrue("It should be possible to add this Hypercube.",
				duplicateCollection.getHypercubes().add(new Hypercube(new int[]{1, 0, 0, 0, 0, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				duplicateCollection.getHypercubes().add(new Hypercube(new int[]{2, 0, 0, 0, 0, 0})));
		assertFalse("Shouldn't be possible - This is a duplicate of one of the existing elements.", duplicateCollection.getHypercubes().add(new Hypercube(new int[]{Integer.MIN_VALUE, 0, 1, 2, 3, Integer.MAX_VALUE})));
		assertTrue("It should be possible to add this Hypercube.",
				duplicateCollection.getHypercubes().add(new Hypercube(new int[]{0, 0, 0, 0, 0, 0})));

		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{0, 0, 0, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{-1, 0, 0, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{1, 0, 0, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{0, -1, 0, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{0, 1, 0, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{0, 0, -1, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{0, 0, 1, 0})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{0, 0, 0, -1})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{0, 0, 0, 1})));
		assertTrue("It should be possible to add this Hypercube.",
				room4d.getHypercubes().add(new Hypercube(new int[]{-1, 0, 0, 1})));
	}
	
	/**
	 * 
	 */
	public void testGetHypercubes() {
		assertEquals("There should be no Hypercubes in this collection.",
				nullCollection.getHypercubes().size(),
				0);
		assertEquals("The first position is incorrect.",
				corridorA.getHypercubes().toArray(new Hypercube[0])[0].getPosition()[0],
				Integer.MIN_VALUE);
		assertEquals("The second position is incorrect: " + corridorA.getHypercubes().toArray(new Hypercube[0])[1].getPosition()[0] + ".",
				corridorA.getHypercubes().toArray(new Hypercube[0])[1].getPosition()[0],
				1);
	}

	/**
	 * Make sure sets and values are set properly by setHypercubes.
	 */
	public void testSetHypercubes() {
		// Old values
		Hypercube[] oldSet = new Hypercube[]{
				new Hypercube(new int[]{2, 3, 4, 5}),
				new Hypercube(new int[]{4, 5, 6, 7}),
				new Hypercube(new int[]{7, 8, 9, 10}),
				new Hypercube(new int[]{11, 12, -1, Integer.MAX_VALUE})
		};
		HypercubeCollection collection = new HypercubeCollection();
		for (Hypercube cube : oldSet) {
			assertTrue("Could not add hypercube: " + cube.getPosition().toString() + ".",
					collection.getHypercubes().add(cube));
		}
		for (Hypercube cube : oldSet) {
			assertTrue("The collection should contain the old values.",
					collection.getHypercubes().contains(cube));
		}
		assertEquals("The current values should correspond to the old set. Instead, found "
				+ collection.getHypercubes().toArray(new Hypercube[0])[0].getPosition().length,
				collection.getHypercubes().toArray(new Hypercube[0])[0].getPosition().length,
				4);
		assertEquals("The current values should correspond to the old set. Instead, found "
				+ collection.getHypercubes().toArray(new Hypercube[0])[2].getPosition()[1],
				collection.getHypercubes().toArray(new Hypercube[0])[2].getPosition()[1],
				8);
		
		// New values
		Hypercube[] newSet = new Hypercube[]{
				new Hypercube(new int[]{1, 2})
				};
		collection.getHypercubes().clear();
		for (Hypercube cube : newSet) {
			assertTrue("Could not add hypercube: " + cube.getPosition().toString(),
					collection.getHypercubes().add(cube));
		}
		for (Hypercube cube : newSet) {
			assertTrue("The collection should contain the new values.",
					collection.getHypercubes().contains(cube));
		}
		assertEquals("The current values should correspond to the new set. Instead, found "
				+ collection.getHypercubes().toArray(new Hypercube[0])[0].getPosition()[0],
				collection.getHypercubes().toArray(new Hypercube[0])[0].getPosition()[0],
				1);
		assertEquals("The current values should correspond to the new set. Instead, found "
				+ collection.getHypercubes().toArray(new Hypercube[0])[0].getPosition()[1],
				collection.getHypercubes().toArray(new Hypercube[0])[0].getPosition()[1],
				2);
		
		//Null set
		collection.getHypercubes().clear();
		assertEquals("The current set should be empty",
				collection.getHypercubes().size(),
				0);
	}
	
	/**
	 * 
	 */
	public void testGetDimensions() {
		assertEquals("The null collection should be 0-dimensional.", nullCollection.getDimensions(), 0);
		assertEquals("The corridor should be 1-dimensional", corridorA.getDimensions(), 1);
		assertEquals("The rooms should be 2-dimensional", roomB.getDimensions(), 2);
	}
	
	/**
	 * Make sure that the method for detecting neighbors works correctly in 0 dimensions (null set).
	 */
	public void testIsNeighbor0d() {
		try {
			assertFalse("These should not be neighbors.", nullCollection.isNeighbor(nullCollection));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("It should be possible to compare the null set for neighborness.");
		}
		
		try {
			assertFalse("These sets are of different dimensions, and cannot be neighbors.",
					nullCollection.isNeighbor(roomC));
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	
	/**
	 * Make sure that the method for detecting neighbors works correctly in 1 dimension.
	 */
	public void testIsNeighbor1d() {
		try {
			assertFalse("These should not be neighbors.", corridorA.getHypercubes().toArray(new Hypercube[0])[0].isNeighbor(corridorA.getHypercubes().toArray(new Hypercube[0])[1]));
			assertTrue("These should be neighbors.", corridorA.getHypercubes().toArray(new Hypercube[0])[1].isNeighbor(corridorA.getHypercubes().toArray(new Hypercube[0])[2]));
			assertFalse("These should not be neighbors.", corridorA.getHypercubes().toArray(new Hypercube[0])[2].isNeighbor(corridorA.getHypercubes().toArray(new Hypercube[0])[3]));
			assertFalse("These should not be neighbors.", corridorA.getHypercubes().toArray(new Hypercube[0])[3].isNeighbor(corridorA.getHypercubes().toArray(new Hypercube[0])[0]));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("It should be possible to check these Hypercubes for neighborness.");
		}
	}
	
	/**
	 * Make sure that the method for detecting neighbors works correctly in 2 dimensions.
	 */
	public void testIsNeighbor2d() {
		try {
			assertTrue("These should be neighbors.", roomA.isNeighbor(roomB));
			assertTrue("These should be neighbors.", roomB.isNeighbor(roomE));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("It should be possible to check these Hypercubes for neighborness.");
		}
		
		try {
			assertFalse("These sets are of different dimensions, and cannot be neighbors.",
					corridorA.isNeighbor(roomC));
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
			assertFalse("This should not be its own neighbor.", roomB.isNeighbor(roomB));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("It should be possible to check these Hypercubes for neighborness.");
		}
	}
	
	/**
	 * Make sure that the overlap function works in 1 dimension.
	 */
	public void testOverlap1d() {
		try {
			assertTrue("These should overlap.", corridorA.overlaps(corridorA));
			assertTrue("These should overlap.", corridorA.overlaps(corridorB));
			assertTrue("These should overlap.", corridorB.overlaps(corridorA));
			assertFalse("These should not overlap.", corridorB.overlaps(corridorC));
			assertFalse("These should not overlap.", corridorC.overlaps(corridorA));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("It should be possible to check these Hypercubes for overlap.");
		}
	}
	
	/**
	 * Make sure that the overlap function works in 2 dimensions.
	 */
	public void testOverlap2d() {
		try {
			for (HypercubeCollection room : house) {
				for (HypercubeCollection otherRoom : house) {
					/* Either the rooms are equal, and they are mutually overlapping,
					 * or they are not equal, and not overlapping.
					 */
					if (room.equals(otherRoom)) {
						assertTrue("The results of compareTo() of equal rooms don't agree for rooms positioned at ("
								+ room.getHypercubes().first().getPosition()[0] + ", " + room.getHypercubes().first().getPosition()[1] + ") and ("
								+ otherRoom.getHypercubes().first().getPosition()[0] + ", " + otherRoom.getHypercubes().first().getPosition()[1] + ")",
								room.overlaps(otherRoom));
						assertTrue("The results of compareTo() of equal rooms don't agree for rooms positioned at ("
								+ room.getHypercubes().first().getPosition()[0] + ", " + room.getHypercubes().first().getPosition()[1] + ") and ("
								+ otherRoom.getHypercubes().first().getPosition()[0] + ", " + otherRoom.getHypercubes().first().getPosition()[1] + ")",
								otherRoom.overlaps(room));
					} else {
						assertFalse("The results of compareTo() of un-equal rooms don't agree for rooms positioned at ("
								+ room.getHypercubes().first().getPosition()[0] + ", " + room.getHypercubes().first().getPosition()[1] + ") and ("
								+ otherRoom.getHypercubes().first().getPosition()[0] + ", " + otherRoom.getHypercubes().first().getPosition()[1] + ")",
								room.overlaps(otherRoom));
						assertFalse("The results of compareTo() of un-equal rooms don't agree for rooms positioned at ("
								+ room.getHypercubes().first().getPosition()[0] + ", " + room.getHypercubes().first().getPosition()[1] + ") and ("
								+ otherRoom.getHypercubes().first().getPosition()[0] + ", " + otherRoom.getHypercubes().first().getPosition()[1] + ")",
								otherRoom.overlaps(room));
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("It should be possible to check these Hypercubes for overlap.");
		}
	}
	
	public void testGetNeighbors() {
		assertEquals("This Hypercube shouldn't have any neighbors.",
				corridorA.getNeighbors(corridorA.getHypercubes().first()).getHypercubes().size(),
				0);
		assertEquals("This Hypercube should have only one neighbor.",
				corridorA.getNeighbors(corridorA.getHypercubes().toArray(new Hypercube[0])[1]).getHypercubes().size(),
				1);
	}
	
	public void testGetIslands() {
		for (HypercubeCollection room : house) {
			assertEquals("corridorA should have three islands. Instead, found " + corridorA.getIslands().size(),
					corridorA.getIslands().size(),
					3);
			assertEquals("The previous test should not have manipulated anything, so the result should be the same.",
					corridorA.getIslands().size(),
					3);
			assertEquals("duplicateCollection should have two islands. Instead, found " + duplicateCollection.getIslands().size(),
					duplicateCollection.getIslands().size(),
					2);
			assertEquals("The room at ("
					+ room.getHypercubes().first().getPosition()[0] + ", "
					+ room.getHypercubes().first().getPosition()[1]
					+ ") should have a single island (found " + room.getIslands().size() + ").",
					room.getIslands().size(),
					1);
			assertEquals("Wrong number of islands.",
					room4d.getIslands().size(),
					1);
		}
	}

	public void testGetBorder() {
		assertEquals("Wrong number of border Hypercubes.", nullCollection.getBorder().getHypercubes().size(), 0);
		assertEquals("Wrong number of border Hypercubes.", corridorA.getBorder().getHypercubes().size(), 4);
		assertEquals("Wrong number of border Hypercubes.", roomA.getBorder().getHypercubes().size(), 13);
		assertEquals("Wrong number of border Hypercubes.", roomB.getBorder().getHypercubes().size(), 9);
		assertEquals("Wrong number of border Hypercubes.", room4d.getBorder().getHypercubes().size(), 9);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
}
