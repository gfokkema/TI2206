package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class GridTest {
	private Grid grid;

	/**
	 * This method sets up a 6x15 grid for use in all test cases.
	 * 
	 * This is what a 6x15 grid looks like (for reference):
	 *  0   1   2   3   4   5
	 *    6   7   8   9  10
	 * 11  12  13  14  15  16
	 *   17  18  19  20  21
	 * 22  23  24  25  26  27
	 *   28  29  30  31  32
	 */
	@Before
	public void setUp() {
		grid = new Grid(6, 15);
	}

	/**
	 * Test retrieving indices for the first row (even row).
	 */
	@Test
	public void testToIdxFirstRow() {
		assertEquals(0, grid.toIdx(0, 0));
		assertEquals(4, grid.toIdx(4, 0));
		assertEquals(5, grid.toIdx(5, 0));
	}

	/**
	 * Test retrieving indices for the second row (odd row).
	 */
	@Test
	public void testToIdxSecondRow() {
		assertEquals(6, grid.toIdx(0, 1));
		assertEquals(7, grid.toIdx(1, 1));
		assertEquals(10, grid.toIdx(4, 1));
	}

	/**
	 * Test retrieving indices for some other boundary cases.
	 */
	@Test
	public void testToIdxOtherRows() {
		assertEquals(11, grid.toIdx(0, 2));
		assertEquals(17, grid.toIdx(0, 3));
		assertEquals(30, grid.toIdx(2, 5));
	}

	/**
	 * Test retrieving indices for x out of bounds, even row.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_1() {
		grid.toIdx(6, 0);
	}

	/**
	 * Test retrieving indices for x out of bounds, odd row.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_2() {
		grid.toIdx(5, 1);
	}

	/**
	 * Test retrieving indices for x out of bounds, negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_3() {
		grid.toIdx(-1, 1);
	}

	/**
	 * Test retrieving indices for y out of bounds, positive.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_4() {
		grid.toIdx(0, 15);
	}

	/**
	 * Test retrieving indices for y out of bounds, negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_5() {
		grid.toIdx(0, -1);
	}

	/**
	 * Test retrieving xy coords for the first row (even row).
	 */
	@Test
	public void testToXYFirstRow() {
		Vector2 xy;
		xy = new Vector2(4, 0);
		assertEquals(xy, grid.toXY(4));
		xy = new Vector2(5, 0);
		assertEquals(xy, grid.toXY(5));
	}

	/**
	 * Test retrieving xy coords for the second row (odd row).
	 */
	@Test
	public void testToXYSecondRow() {
		Vector2 xy;
		xy = new Vector2(0, 1);
		assertEquals(xy, grid.toXY(6));
		xy = new Vector2(1, 1);
		assertEquals(xy, grid.toXY(7));
		xy = new Vector2(2, 1);
		assertEquals(xy, grid.toXY(8));
	}

	/**
	 * Test retrieving xy coords for some other boundary cases.
	 */
	@Test
	public void testToXYOtherRows() {
		Vector2 xy;
		xy = new Vector2(0, 2);
		assertEquals(xy, grid.toXY(11));
		xy = new Vector2(0, 3);
		assertEquals(xy, grid.toXY(17));
		xy = new Vector2(2, 5);
		assertEquals(xy, grid.toXY(30));
		xy = new Vector2(5, 14);
		assertEquals(xy, grid.toXY(82));
	}

	/**
	 * Test retrieving indices for idx out of bounds, negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToXYOutOfBounds_1() {
		grid.toXY(-1);
	}

	/**
	 * Test retrieving indices for idx out of bounds, positive.
	 */
	@Test
	public void testToXYOutOfBounds_2() {
		// Value inside of bounds
		Vector2 xy = new Vector2(5, 14);
		assertEquals(xy, grid.toXY(82));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testToXYOutOfBounds_3() {
		// Value outside of bounds
		grid.toXY(83);
	}
	
	/**
	 * Test adjacency for cells in the same row
	 */
	@Test
	public void testGetAdjacent() {
		Integer[] expected1 = {1, 6};
		assertEquals(Arrays.asList(expected1), grid.getAdjacent(0));
		
		Integer[] expected2 = {3, 4, 8, 10, 14, 15};
		ArrayList<Integer> actual = grid.getAdjacent(9);
		Collections.sort(actual);
		assertEquals(Arrays.asList(expected2), actual);
	}

	/**
	 * Test adjacency for cells in the same row
	 */
	@Test
	public void testAdjacentRow() {
		assertTrue(grid.adjacent(4, 5));
		assertFalse(grid.adjacent(5, 6));
		assertTrue(grid.adjacent(6, 7));
		assertTrue(grid.adjacent(8, 7));
		assertTrue(grid.adjacent(9, 10));
	}

	/**
	 * Test adjacency for cells in NE and NW direction
	 */
	@Test
	public void testAdjacentNorth() {
		// North East
		assertTrue(grid.adjacent(6, 12));
		assertFalse(grid.adjacent(11, 5));
		assertTrue(grid.adjacent(19, 13));
		// North West
		assertTrue(grid.adjacent(18, 13));
		assertFalse(grid.adjacent(16, 11));
		assertTrue(grid.adjacent(20, 15));
	}

	/**
	 * Test adjacency for cells in NE and NW direction
	 */
	@Test
	public void testAdjacentSouth() {
		// South East
		assertTrue(grid.adjacent(12, 17));
		assertFalse(grid.adjacent(22, 27));
		assertTrue(grid.adjacent(18, 23));
		// South West
		assertTrue(grid.adjacent(18, 24));
		assertFalse(grid.adjacent(27, 33));
		assertTrue(grid.adjacent(0, 6));
	}

	/**
	 * Test non-adjacent cells in random directions
	 */
	@Test
	public void testNonAdjacent() {
		assertFalse(grid.adjacent(15, 3));
		assertFalse(grid.adjacent(15, 4));
		assertFalse(grid.adjacent(15, 5));
		assertFalse(grid.adjacent(15, 25));
		assertFalse(grid.adjacent(15, 26));
		assertFalse(grid.adjacent(15, 27));
	}

	/**
	 * Test adjacent out of bound cells, positive
	 */
	public void testAdjacentOutOfBounds_3() {
		assertFalse(grid.adjacent(77, 83));
		assertFalse(grid.adjacent(83, 84));
	}

	/**
	 * Test getters for width and height:
	 *	getWidth()
	 *	getHeight()
	 */
	@Test
	public void testGetWidthHeight() {
		assertEquals(6, grid.getGridWidth());
		assertEquals(15, grid.getGridHeight());

		grid = new Grid(20, 4);
		assertEquals(20, grid.getGridWidth());
		assertEquals(4, grid.getGridHeight());
	}
}
