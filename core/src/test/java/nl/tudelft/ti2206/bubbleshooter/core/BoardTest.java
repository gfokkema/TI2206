package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.*;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;

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


	
	@Test
	public void testAdd() {
		assertTrue(board.add(new Bubble(), 0));
		assertFalse(board.add(new Bubble(), 0));
	}
	
/*	/**
	 * Verify all bubbles in the grid have a bounding box
	 *//*
	@Test
	public void testBoundingBoxes() {
		board.getBubbles().forEach((Integer k, Bubble v) -> {
			assertEquals(board.getLoc(k).x, v.getBounds().x, .0001);
			assertEquals(board.getLoc(k).y, v.getBounds().y, .0001);
		});
	}*/
	
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
		board.add(new Bubble(Color.BLUE), 0);
		board.add(new Bubble(Color.BLUE), 1);
		board.add(new Bubble(Color.BLUE), 2);
		//Add one Bubble of a different color.
		board.add(new Bubble(Color.RED), 3);
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
			board.add(new Bubble(Color.RED), i);
		}
		
		Collection<Bubble> colorGroupRed = board.getColorGroup(0);
		colorGroupRed.forEach(
			(Bubble b) -> assertEquals(Color.RED, b.getColor())
		);
	}	
	

/**
 * Checks whether (adjacent on the board) add bubbles, of the same color,
 *  all get add.
 */
	@Test
	public void testAddColorGroupWrongColorAddedAdjacent(){

		for (int i = 0; i < 4; i++) {
			board.add(new Bubble(Color.RED), i);
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
			(Bubble c) -> assertEquals(Color.BLUE, c.getColor())
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

	/**
	 * Test whether an empty Collection is returned when all Bubbles are connected to the ceiling.
	 */
	@Test
	public void testGetDisconnectedGroupEmpty() {
		// Initialize four bubbles
		for (int i = 0; i < 4; i++) {
			board.add(new Bubble(), i);
		}
		Collection<Bubble> empty = board.getDisconnectedGroup();
		assertTrue(empty.isEmpty());
	}
}
