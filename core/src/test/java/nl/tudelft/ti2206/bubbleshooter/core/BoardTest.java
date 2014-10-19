package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
 
/**
 * In this class we will test the functionality of our Board class.
 * The Board represents the playing field that keeps track of all ColourBubbles in the game.
 */
public class BoardTest {
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
	 * Test collisions with the border of the grid.
	 */
	@Test
	public void testCollisionBorder() {
		Circle c1 = new Circle(32 * 3, 480 - 32 * 0, 16);
		Circle c2 = new Circle(32 * 7, 480 - 32 * 5, 16);
		Circle c3 = new Circle(32 * 0, 480 - 32 * 5, 16);
		Projectile bubble = new Projectile(c1, new Vector2(0, 0), 0);

		// ColourBubble should hit the top border
		bubble.setBounds(c1);
		assertTrue(grid.collides(bubble));

		// ColourBubble should hit the rightmost border
		bubble.setDirection(new Vector2(1, 0));
		bubble.setBounds(c2);
		assertFalse(grid.collides(bubble));
		assertEquals(new Vector2(-1, 0), bubble.getDirection());

		bubble.setDirection(new Vector2(-1, 0));
		bubble.setBounds(c3);
		assertFalse(grid.collides(bubble));
		assertEquals(new Vector2(1, 0), bubble.getDirection());
	}

	/**
	 * Test that only adjacent ColourBubbles of the same colors
	 * are returned.
	 */
	@Test
	public void testgetGroup() {
		//Add 3 ColourBubbles in a row.
		grid.add(new ColourBubble(Color.BLUE), 0);
		grid.add(new ColourBubble(Color.BLUE), 1);
		grid.add(new ColourBubble(Color.BLUE), 2);
		//Add one ColourBubble of a different color.
		grid.add(new ColourBubble(Color.RED), 3);
		//Add one ColourBubble of the same color, but not adjacent.
		grid.add(new ColourBubble(Color.BLUE), 1, 2);

		Collection<Bubble> colorGroup = grid.getGroup(0);
		assertEquals(3, colorGroup.size());
		colorGroup.forEach(
				(Bubble b) -> assertEquals(Color.BLUE, b.getColor())
		);
	}

	/**
	 * Test to see if only ColourBubbles that are disconnected
	 * from the ceiling are returned.
	 */
	@Test
	public void testRemoveDisconnected() {
		grid.add(new ColourBubble(Color.BLUE), 1, 0);
		grid.add(new ColourBubble(Color.BLUE), 2, 0);
		grid.add(new ColourBubble(Color.BLUE), 3, 0);
		grid.add(new ColourBubble(Color.RED), 4, 2);
		grid.add(new ColourBubble(Color.RED), 5, 2);

		grid.removeDisconnected();
		Collection<GridCell> filled = grid.getFilledGridCells();
		assertEquals(3, filled.size());
		filled.forEach(
				(GridCell gc) -> assertEquals(Color.BLUE, gc.getBubble().getColor())
		);
	}

	/**
	 * Test whether an empty Collection is returned when all ColourBubbles are connected to the ceiling.
	 */
	@Test
	public void testRemoveDisconnectedEmpty() {
		// Initialize four ColourBubbles
		for (int i = 0; i < 4; i++) {
			grid.add(new ColourBubble(), i);
		}
		grid.removeDisconnected();
		assertEquals(4, grid.size());
	}

	/**
	 * Checks whether (adjacent on the grid) add ColourBubbles, of the same color,
	 *  all get add.
	 */
	@Test
	public void testAddColorGroup(){

		for (int i = 0; i < 4; i++) {
			grid.add(new ColourBubble(Color.RED), i);
		}

		Collection<Bubble> colorGroupRed = grid.getGroup(0);
		colorGroupRed.forEach(
				(Bubble b) -> assertEquals(Color.RED, b.getColor())
				);
	}	

	/**
	 * Checks whether (adjacent on the grid) add ColourBubbles, of the same color,
	 *  all get add.
	 */
	@Test
	public void testAddColorGroupWrongColorAddedAdjacent(){

		for (int i = 0; i < 4; i++) {
			grid.add(new ColourBubble(Color.RED), i);
		}

		Collection<Bubble> colorGroupRed = grid.getGroup(0);
		assertFalse(colorGroupRed.contains(Color.BLUE));

	}	


	/**
	 * Checks whether (not adjacent on the grid) add ColourBubbles, of the same color, 
	 * all get add.
	 */
	@Test
	public void testAddColorGroupNotAdjacent(){

		grid.add(new ColourBubble(Color.BLUE),4);
		grid.add(new ColourBubble(Color.BLUE),5);
		grid.add(new ColourBubble(Color.RED),7);	
		grid.add(new ColourBubble(Color.BLUE), 10);

		Collection<Bubble> colorGroupBlue = grid.getGroup(5);
		colorGroupBlue.forEach(
				(Bubble c) -> assertEquals(Color.BLUE, c.getColor())
				);

	}

	/**
	 * Checks whether (not adjacent on the grid) add ColourBubbles, of the same color, 
	 * all get add and the other colored ColourBubbles do not.
	 */
	@Test
	public void testAddColorGroupWrongColorAdded(){

		grid.add(new ColourBubble(Color.BLUE),4);
		grid.add(new ColourBubble(Color.BLUE),5);
		grid.add(new ColourBubble(Color.RED),7);	
		grid.add(new ColourBubble(Color.BLUE), 10);

		Collection<Bubble> colorGroupBlue = grid.getGroup(5);

		assertFalse(colorGroupBlue.contains(Color.RED));
	}
}
