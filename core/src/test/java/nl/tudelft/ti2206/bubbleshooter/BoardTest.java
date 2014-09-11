package nl.tudelft.ti2206.bubbleshooter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

/**
 * In this class we will test the functionality of our Board class.
 * The Board represents the playing field that keeps track of all bubbles in the game.
 */
public class BoardTest {
	private Board board;

	/**
	 * This method sets up a 6x15 board for use in all test cases.
	 * 
	 * This is what a 6x15 board looks like (for reference):
	 *  0   1   2   3   4   5
	 *    6   7   8   9  10
	 * 11  12  13  14  15  16
	 *   17  18  19  20  21
	 * 22  23  24  25  26  27
	 *   28  29  30  31  32
	 */
	@Before
	public void setUp() {
		board = new Board(6, 15);
	}

	/**
	 * Test retrieving indices for the first row (even row)
	 */
	@Test
	public void testToIdxFirstRow() {
		assertEquals(0, board.toIdx(0, 0));
		assertEquals(4, board.toIdx(4, 0));
		assertEquals(5, board.toIdx(5, 0));
	}

	/**
	 * Test retrieving indices for the second row (odd row)
	 */
	@Test
	public void testToIdxSecondRow() {
		assertEquals(6, board.toIdx(0, 1));
		assertEquals(7, board.toIdx(1, 1));
		assertEquals(10, board.toIdx(4, 1));
	}

	/**
	 * Test some other boundary cases
	 */
	@Test
	public void testToIdxOtherRows() {
		assertEquals(11, board.toIdx(0, 2));
		assertEquals(17, board.toIdx(0, 3));
		assertEquals(30, board.toIdx(2, 5));
	}

	/**
	 * Test x out of bounds, even row
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_1() {
		board.toIdx(6, 0);
	}

	/**
	 * Test x out of bounds, odd row
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_2() {
		board.toIdx(5, 1);
	}

	/**
	 * Test x out of bounds, negative
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_3() {
		board.toIdx(-1, 1);
	}

	/**
	 * Test y out of bounds, positive
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_4() {
		board.toIdx(0, 15);
	}

	/**
	 * Test y out of bounds, negative
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_5() {
		board.toIdx(0, -1);
	}

	@Test
	public void toXY() {
		Vector2 xy;
		xy = new Vector2(4, 0);
		assertEquals(xy, board.toXY(4));
		xy = new Vector2(5, 0);
		assertEquals(xy, board.toXY(5));
		xy = new Vector2(0, 1);
		assertEquals(xy, board.toXY(6));
		xy = new Vector2(1, 1);
		assertEquals(xy, board.toXY(7));
		xy = new Vector2(2, 1);
		assertEquals(xy, board.toXY(8));
		xy = new Vector2(0, 2);
		assertEquals(xy, board.toXY(11));
		xy = new Vector2(0, 3);
		assertEquals(xy, board.toXY(17));
	}

	@Test
	public void adjacent() {
		assertTrue(board.adjacent(4, 5));
		assertFalse(board.adjacent(5, 6));
		assertTrue(board.adjacent(6, 7));
		assertTrue(board.adjacent(6, 12));
		assertFalse(board.adjacent(11, 5));
		assertTrue(board.adjacent(14, 13));
		assertTrue(board.adjacent(3, 4));
	}
}
