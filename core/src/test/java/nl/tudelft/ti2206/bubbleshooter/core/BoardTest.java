package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

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
	 * Test the default values of a {@link Board}.
	 */
	@Test
	public void testCreate() {
		assertEquals(320, board.getWidth());
		assertEquals(480, board.getHeight());
		assertEquals(TextureID.BORDER, board.getTexture());
		assertTrue(board.isEmpty());
		assertEquals(new Vector2(0, 0), board.getPosition());
		assertEquals(new Vector2(0, 0), board.getOrigin());
		assertEquals(Color.WHITE, board.getColor());
		assertEquals(0, board.getRotation(), .001);

	}

	/**
	 * Test adding a ColourBubble to the {@link Board}, using a given {@link Board} index.
	 */
	@Test
	public void testAddIndex() {
		assertTrue(board.add(new ColourBubble(), 0));
		assertFalse(board.add(new ColourBubble(), 0));
		assertFalse(board.isEmpty());
	}

	/**
	 * Test adding a ColourBubble to the {@link Board}, using only it's current position.
	 */
	@Test
	public void testAdd() {
		Circle c1 = new Circle(32 * 3 + 16, 480 - 32 * 0 - 16, 16);
		Circle c2 = new Circle(32 * 3 + 32, 480 - 32 * 1 - 16, 16);
		Circle c3 = new Circle(32 * 3 + 16, 480 - 32 * 2 - 16, 16);
		
		Projectile p = new Projectile(c1, new Vector2(), 0);
		p.setBounds(c1);
		assertEquals(0, board.add(p));
		p.setBounds(c2);
		assertEquals(0, board.add(p));
		assertEquals(-1, board.add(p));
		p.setBounds(c3);
		assertEquals(3, board.add(p));
	}

	/**
	 * Test whether all the correct drawables are returned by this {@link Board}.
	 */
	@Test
	public void testGetDrawables() {
		Collection<BSDrawable> drawables;

		drawables = board.getDrawables();
		assertTrue(drawables.contains(board));
		assertEquals(1, drawables.size());

		ArrayList<Bubble> testbubbles = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			testbubbles.add(new ColourBubble());
			board.add(testbubbles.get(i), i);
		}

		drawables = board.getDrawables();
		assertEquals(11, drawables.size());
		for (Bubble b : testbubbles) assertTrue(drawables.contains(b));
	}

	/**
	 * Test whether the collision method works, with the collision in possible circumstances.
	 */
	@Test
	public void testCollision() {
		for (int i = 0; i < 20; i++) {
			board.add(new ColourBubble(), i);
		}
		Circle c1 = new Circle(32 * 3, 480 - 32 * 1, 16);
		Circle c2 = new Circle(32 * 3, 480 - 32 * 3, 16);
		Circle c3 = new Circle(32 * 3, 480 - 32 * 5, 16);
		Projectile bubble = new Projectile(c1, new Vector2(0, 0), 0);

		bubble.setBounds(c1);
		assertTrue(board.collides(bubble));

		bubble.setBounds(c2);
		assertTrue(board.collides(bubble));

		bubble.setBounds(c3);
		assertFalse(board.collides(bubble));
	}

	/**
	 * Test collisions with the border of the board.
	 */
	@Test
	public void testCollisionBorder() {
		Circle c1 = new Circle(32 * 3, 480 - 32 * 0, 16);
		Circle c2 = new Circle(32 * 7, 480 - 32 * 5, 16);
		Circle c3 = new Circle(32 * 0, 480 - 32 * 5, 16);
		Projectile bubble = new Projectile(c1, new Vector2(0, 0), 0);

		// ColourBubble should hit the top border
		bubble.setBounds(c1);
		assertTrue(board.collides(bubble));

		// ColourBubble should hit the rightmost border
		bubble.setDirection(new Vector2(1, 0));
		bubble.setBounds(c2);
		assertFalse(board.collides(bubble));
		assertEquals(new Vector2(-1, 0), bubble.getDirection());

		bubble.setDirection(new Vector2(-1, 0));
		bubble.setBounds(c3);
		assertFalse(board.collides(bubble));
		assertEquals(new Vector2(1, 0), bubble.getDirection());
	}

	/**
	 * Test that only adjacent ColourBubbles of the same colors
	 * are returned.
	 */
	@Test
	public void testgetGroup() {
		//Add 3 ColourBubbles in a row.
		board.add(new ColourBubble(Color.BLUE), 0);
		board.add(new ColourBubble(Color.BLUE), 1);
		board.add(new ColourBubble(Color.BLUE), 2);
		//Add one ColourBubble of a different color.
		board.add(new ColourBubble(Color.RED), 3);
		//Add one ColourBubble of the same color, but not adjacent.
		board.add(new ColourBubble(Color.BLUE), 1, 2);

		Collection<Bubble> colorGroup = board.getGroup(0);
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
	public void testGetDisconnectedGroup() {
		board.add(new ColourBubble(Color.BLUE), 1, 0);
		board.add(new ColourBubble(Color.BLUE), 2, 0);
		board.add(new ColourBubble(Color.BLUE), 3, 0);
		board.add(new ColourBubble(Color.RED), 4, 2);
		board.add(new ColourBubble(Color.RED), 5, 2);

		Collection<Bubble> disconnectedGroup = board.getDisconnectedGroup();
		assertEquals(2, disconnectedGroup.size());
		disconnectedGroup.forEach(
				(Bubble b) -> assertEquals(Color.RED, b.getColor())
				);
	}

	/**
	 * Checks whether (adjacent on the board) add ColourBubbles, of the same color,
	 *  all get add.
	 */
	@Test
	public void testAddColorGroup(){

		for (int i = 0; i < 4; i++) {
			board.add(new ColourBubble(Color.RED), i);
		}

		Collection<Bubble> colorGroupRed = board.getGroup(0);
		colorGroupRed.forEach(
				(Bubble b) -> assertEquals(Color.RED, b.getColor())
				);
	}	

	/**
	 * Checks whether (adjacent on the board) add ColourBubbles, of the same color,
	 *  all get add.
	 */
	@Test
	public void testAddColorGroupWrongColorAddedAdjacent(){

		for (int i = 0; i < 4; i++) {
			board.add(new ColourBubble(Color.RED), i);
		}

		Collection<Bubble> colorGroupRed = board.getGroup(0);
		assertFalse(colorGroupRed.contains(Color.BLUE));

	}	


	/**
	 * Checks whether (not adjacent on the board) add ColourBubbles, of the same color, 
	 * all get add.
	 */
	@Test
	public void testAddColorGroupNotAdjacent(){

		board.add(new ColourBubble(Color.BLUE),4);
		board.add(new ColourBubble(Color.BLUE),5);
		board.add(new ColourBubble(Color.RED),7);	
		board.add(new ColourBubble(Color.BLUE), 10);

		Collection<Bubble> colorGroupBlue = board.getGroup(5);
		colorGroupBlue.forEach(
				(Bubble c) -> assertEquals(Color.BLUE, c.getColor())
				);

	}

	/**
	 * Checks whether (not adjacent on the board) add ColourBubbles, of the same color, 
	 * all get add and the other colored ColourBubbles do not.
	 */
	@Test
	public void testAddColorGroupWrongColorAdded(){

		board.add(new ColourBubble(Color.BLUE),4);
		board.add(new ColourBubble(Color.BLUE),5);
		board.add(new ColourBubble(Color.RED),7);	
		board.add(new ColourBubble(Color.BLUE), 10);

		Collection<Bubble> colorGroupBlue = board.getGroup(5);

		assertFalse(colorGroupBlue.contains(Color.RED));
	}


	/**
	 * Test whether an empty Collection is returned when all ColourBubbles are connected to the ceiling.
	 */
	@Test
	public void testGetDisconnectedGroupEmpty() {
		// Initialize four ColourBubbles
		for (int i = 0; i < 4; i++) {
			board.add(new ColourBubble(), i);
		}
		Collection<Bubble> empty = board.getDisconnectedGroup();
		assertTrue(empty.isEmpty());
	}

	/**
	 * Test whether the board detects it when a ColourBubble is below the losing line.
	 */
	@Test
	public void testBubbleBelowLine() {
		board.add(new ColourBubble(),0,1);
		assertFalse(board.bubbleBelowLine(14));
		//Test just before the id which are checked.
		board.add(new ColourBubble(),4,13);
		assertFalse(board.bubbleBelowLine(14));
		//Test the last ColourBubble that's checked.
		board.add(new ColourBubble(),5,14);
		assertTrue(board.bubbleBelowLine(14));
		//Test the first ColourBubble that's checked.
		board.add(new ColourBubble(),0,14);
		assertTrue(board.bubbleBelowLine(14));
	}
}
