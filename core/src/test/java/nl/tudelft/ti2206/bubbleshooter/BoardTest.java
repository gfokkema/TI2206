package nl.tudelft.ti2206.bubbleshooter;

import static org.junit.Assert.*;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.Board;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
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
	 * Test retrieving indices for the first row (even row).
	 */
	@Test
	public void testToIdxFirstRow() {
		assertEquals(0, board.toIdx(0, 0));
		assertEquals(4, board.toIdx(4, 0));
		assertEquals(5, board.toIdx(5, 0));
	}

	/**
	 * Test retrieving indices for the second row (odd row).
	 */
	@Test
	public void testToIdxSecondRow() {
		assertEquals(6, board.toIdx(0, 1));
		assertEquals(7, board.toIdx(1, 1));
		assertEquals(10, board.toIdx(4, 1));
	}

	/**
	 * Test retrieving indices for some other boundary cases.
	 */
	@Test
	public void testToIdxOtherRows() {
		assertEquals(11, board.toIdx(0, 2));
		assertEquals(17, board.toIdx(0, 3));
		assertEquals(30, board.toIdx(2, 5));
	}

	/**
	 * Test retrieving indices for x out of bounds, even row.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_1() {
		board.toIdx(6, 0);
	}

	/**
	 * Test retrieving indices for x out of bounds, odd row.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_2() {
		board.toIdx(5, 1);
	}

	/**
	 * Test retrieving indices for x out of bounds, negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_3() {
		board.toIdx(-1, 1);
	}

	/**
	 * Test retrieving indices for y out of bounds, positive.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_4() {
		board.toIdx(0, 15);
	}

	/**
	 * Test retrieving indices for y out of bounds, negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToIdxOutOfBounds_5() {
		board.toIdx(0, -1);
	}

	/**
	 * Test retrieving xy coords for the first row (even row).
	 */
	@Test
	public void testToXYFirstRow() {
		Vector2 xy;
		xy = new Vector2(4, 0);
		assertEquals(xy, board.toXY(4));
		xy = new Vector2(5, 0);
		assertEquals(xy, board.toXY(5));
	}
	
	/**
	 * Test retrieving xy coords for the second row (odd row).
	 */
	@Test
	public void testToXYSecondRow() {
		Vector2 xy;
		xy = new Vector2(0, 1);
		assertEquals(xy, board.toXY(6));
		xy = new Vector2(1, 1);
		assertEquals(xy, board.toXY(7));
		xy = new Vector2(2, 1);
		assertEquals(xy, board.toXY(8));
	}
	
	/**
	 * Test retrieving xy coords for some other boundary cases.
	 */
	@Test
	public void testToXYOtherRows() {
		Vector2 xy;
		xy = new Vector2(0, 2);
		assertEquals(xy, board.toXY(11));
		xy = new Vector2(0, 3);
		assertEquals(xy, board.toXY(17));
		xy = new Vector2(2, 5);
		assertEquals(xy, board.toXY(30));
		xy = new Vector2(5, 14);
		assertEquals(xy, board.toXY(82));
	}
	
	/**
	 * Test retrieving indices for idx out of bounds, negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToXYOutOfBounds_1() {
		board.toXY(-1);
	}
	
	/**
	 * Test retrieving indices for idx out of bounds, positive.
	 */
	@Test
	public void testToXYOutOfBounds_2() {
		// Value inside of bounds
		Vector2 xy = new Vector2(5, 14);
		assertEquals(xy, board.toXY(82));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testToXYOutOfBounds_3() {
		// Value outside of bounds
		board.toXY(83);
	}

	/**
	 * Test adjacency for cells in the same row
	 */
	@Test
	public void testAdjacentRow() {
		assertTrue(board.adjacent(4, 5));
		assertFalse(board.adjacent(5, 6));
		assertTrue(board.adjacent(6, 7));
		assertTrue(board.adjacent(8, 7));
	}
	
	/**
	 * Test adjacency for cells in NE and NW direction
	 */
	@Test
	public void testAdjacentNorth() {
		// North East
		assertTrue(board.adjacent(6, 12));
		assertFalse(board.adjacent(11, 5));
		assertTrue(board.adjacent(19, 13));
		// North West
		assertTrue(board.adjacent(18, 13));
		assertFalse(board.adjacent(16, 11));
		assertTrue(board.adjacent(20, 15));
	}
	
	/**
	 * Test adjacency for cells in NE and NW direction
	 */
	@Test
	public void testAdjacentSouth() {
		// South East
		assertTrue(board.adjacent(12, 17));
		assertFalse(board.adjacent(22, 27));
		assertTrue(board.adjacent(18, 23));
		// South West
		assertTrue(board.adjacent(18, 24));
		assertFalse(board.adjacent(27, 33));
		assertTrue(board.adjacent(0, 6));
	}
	
	/**
	 * Test non-adjacent cells in random directions
	 */
	@Test
	public void testNonAdjacent() {
		assertFalse(board.adjacent(15, 3));
		assertFalse(board.adjacent(15, 4));
		assertFalse(board.adjacent(15, 5));
		assertFalse(board.adjacent(15, 25));
		assertFalse(board.adjacent(15, 26));
		assertFalse(board.adjacent(15, 27));
	}
	
	/**
	 * Test adjacent out of bound cells, positive
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdjacentOutOfBounds_3() {
		board.adjacent(77, 83);
	}
	
	/**
	 * Test adjacent out of bound cells, positive
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdjacentOutOfBounds_4() {
		board.adjacent(83, 84);
	}
	
	/**
	 * Test getters for width and height:
	 *	getWidth()
	 *	getHeight()
	 */
	@Test
	public void testGetWidthHeight() {
		assertEquals(6, board.getWidth());
		assertEquals(15, board.getHeight());
		
		board = new Board(20, 4);
		assertEquals(20, board.getWidth());
		assertEquals(4, board.getHeight());
	}
	
	@Test
	public void testAdd() {
		assertTrue(board.add(new Bubble(), 0));
		assertFalse(board.add(new Bubble(), 0));
	}
	
	/**
	 * Verify all bubbles in the grid have a bounding box
	 */
	@Test
	public void testBoundingBoxes() {
		board.getBubbles().forEach((Integer k, Bubble v) -> {
			assertEquals(board.getLoc(k).x, v.getBounds().x, .0001);
			assertEquals(board.getLoc(k).y, v.getBounds().y, .0001);
		});
	}
	
	/**
	 * Test whether the collision method works, with the collision in possible circumstances.
	 */
	@Test
	public void testCollision() {
		Bubble bubble = new Bubble();
		Circle c1 = new Circle(0, 0, 5);
		Circle c2 = new Circle(0, 5, 5);
		Circle c3 = new Circle(0, 10, 5);
		Bubble b = new Bubble();
		b.setBounds(c1);
		
		bubble.setBounds(c1);
		assertTrue(bubble.collides(b));
		
		bubble.setBounds(c2);
		assertTrue(bubble.collides(b));
		
		bubble.setBounds(c3);
		assertFalse(bubble.collides(b));
		
		b.setBounds(c2);
		assertTrue(bubble.collides(b));
	}

	/**
	 * Test that only adjacent Bubbles of the same colors
	 * are returned.
	 */
	@Test
	public void testGetColorGroup() {
		//Add 3 Bubbles in a row.
		board.add(new Bubble(Color.BLUE));
		board.add(new Bubble(Color.BLUE));
		board.add(new Bubble(Color.BLUE));
		//Add one Bubble of a different color.
		board.add(new Bubble(Color.RED));
		//Add one Bubble of the same color, but not adjacent.
		board.add(new Bubble(Color.BLUE), 1, 2);

		Collection<Bubble> colorGroup = board.getColorGroup(0);
		assertEquals(3, colorGroup.size());
		colorGroup.forEach(
				(Bubble b) -> assertEquals(Color.BLUE, b.getColor())
		);
	}

	/**
	 * Test to see if only Bubbles that are disconnected
	 * from the ceiling are returned.
	 */
	@Test
	public void testGetDisconnectedGroup() {
		board.add(new Bubble(Color.BLUE), 1, 0);
		board.add(new Bubble(Color.BLUE), 2, 0);
		board.add(new Bubble(Color.BLUE), 3, 0);
		board.add(new Bubble(Color.RED), 4, 2);
		board.add(new Bubble(Color.RED), 5, 2);

		Collection<Bubble> disconnectedGroup = board.getDisconnectedGroup();
		assertEquals(2, disconnectedGroup.size());
		disconnectedGroup.forEach(
				(Bubble b) -> assertEquals(Color.RED, b.getColor())
		);
	}
	
	/**
	 * Checks whether (adjacent on the board) add bubbles, of the same color,
	 *  all get add.
	 */
	@Test
	public void testAddColorGroup(){

		for (int i = 0; i < 4; i++) {
			board.add( new Bubble(Color.RED));
			}
		
		Collection<Bubble> colorGroupRed = board.getColorGroup(0);
		colorGroupRed.forEach(
				(Bubble b) -> assertEquals(Color.RED, b.color)
		);
	}	
	

/**
 * Checks whether (adjacent on the board) add bubbles, of the same color,
 *  all get add.
 */
	@Test
	public void testAddColorGroupWrongColorAddedAdjacent(){

		for (int i = 0; i < 4; i++) {
			board.add( new Bubble(Color.RED));
		}
	
		Collection<Bubble> colorGroupRed = board.getColorGroup(0);
		assertFalse(colorGroupRed.contains(Color.BLUE));

	}	

	
	/**
	 * Checks whether (not adjacent on the board) add bubbles, of the same color, 
	 * all get add.
	 * 
	 */
	@Test
	public void testAddColorGroupNotAdjacent(){
		
		board.add(new Bubble(Color.BLUE),4);
		board.add(new Bubble(Color.BLUE),5);
		board.add(new Bubble(Color.RED),7);	
		board.add(new Bubble(Color.BLUE), 10);
		
		Collection<Bubble> colorGroupBlue = board.getColorGroup(5);
		colorGroupBlue.forEach(
			(Bubble c) -> assertEquals(Color.BLUE, c.color)
		);
		
	}
	
	/**
	 * Checks whether (not adjacent on the board) add bubbles, of the same color, 
	 * all get add and the other colored bubbles do not.
	 * 
	 */
	@Test
	public void testAddColorGroupWrongColorAdded(){
		
		board.add(new Bubble(Color.BLUE),4);
		board.add(new Bubble(Color.BLUE),5);
		board.add(new Bubble(Color.RED),7);	
		board.add(new Bubble(Color.BLUE), 10);
		
		Collection<Bubble> colorGroupBlue = board.getColorGroup(5);
		
		assertFalse(colorGroupBlue.contains(Color.RED));
	}
}
