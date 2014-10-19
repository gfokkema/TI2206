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
	 * Test the default values of a {@link Board}.
	 */
	@Test
	public void testCreate() {
		assertEquals(320, grid.getWidth());
		assertEquals(480, grid.getHeight());
		assertEquals(TextureID.BORDER, grid.getTexture());
		assertTrue(grid.isEmpty());
		assertEquals(new Vector2(0, 0), grid.getPosition());
		assertEquals(new Vector2(0, 0), grid.getOrigin());
		assertEquals(Color.WHITE, grid.getColor());
		assertEquals(0, grid.getRotation(), .001);

	}

	/**
	 * Test adding a ColourBubble to the {@link Board}, using a given {@link Board} index.
	 */
	@Test
	public void testAddIndex() {
		assertTrue(grid.add(new ColourBubble(), 0));
		assertFalse(grid.add(new ColourBubble(), 0));
		assertFalse(grid.isEmpty());
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
		assertEquals(0, grid.add(p));
		p.setBounds(c2);
		assertEquals(0, grid.add(p));
		assertEquals(-1, grid.add(p));
		p.setBounds(c3);
		assertEquals(3, grid.add(p));
	}

	/**
	 * Test whether all the correct drawables are returned by this {@link Board}.
	 */
	@Test
	public void testGetDrawables() {
		Collection<BSDrawable> drawables;

		drawables = grid.getDrawables();
		assertTrue(drawables.contains(grid));
		assertEquals(1, drawables.size());

		ArrayList<Bubble> testbubbles = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			testbubbles.add(new ColourBubble());
			grid.add(testbubbles.get(i), i);
		}

		drawables = grid.getDrawables();
		assertEquals(11, drawables.size());
		for (Bubble b : testbubbles) assertTrue(drawables.contains(b));
	}

	/**
	 * Test whether the collision method works, with the collision in possible circumstances.
	 */
	@Test
	public void testCollision() {
		for (int i = 0; i < 20; i++) {
			grid.add(new ColourBubble(), i);
		}
		Circle c1 = new Circle(32 * 3, 480 - 32 * 1, 16);
		Circle c2 = new Circle(32 * 3, 480 - 32 * 3, 16);
		Circle c3 = new Circle(32 * 3, 480 - 32 * 5, 16);
		Projectile bubble = new Projectile(c1, new Vector2(0, 0), 0);

		bubble.setBounds(c1);
		assertTrue(grid.collides(bubble));

		bubble.setBounds(c2);
		assertTrue(grid.collides(bubble));

		bubble.setBounds(c3);
		assertFalse(grid.collides(bubble));
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
	public void testGetDisconnectedGroup() {
		grid.add(new ColourBubble(Color.BLUE), 1, 0);
		grid.add(new ColourBubble(Color.BLUE), 2, 0);
		grid.add(new ColourBubble(Color.BLUE), 3, 0);
		grid.add(new ColourBubble(Color.RED), 4, 2);
		grid.add(new ColourBubble(Color.RED), 5, 2);

		grid.removeDisconnected();
		Collection<GridCell> filled = grid.cells.values().stream()
														 .filter(gc -> gc.isOccupied())
														 .collect(Collectors.toList());
		assertEquals(3, filled.size());
		filled.forEach(
				(GridCell gc) -> assertEquals(Color.BLUE, gc.getBubble().getColor())
		);
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


	/**
	 * Test whether an empty Collection is returned when all ColourBubbles are connected to the ceiling.
	 */
	@Test
	public void testGetDisconnectedGroupEmpty() {
		// Initialize four ColourBubbles
		for (int i = 0; i < 4; i++) {
			grid.add(new ColourBubble(), i);
		}
		Collection<Bubble> empty = grid.getDisconnectedGroup();
		assertTrue(empty.isEmpty());
	}

	/**
	 * Test whether the grid detects it when a ColourBubble is below the losing line.
	 */
	@Test
	public void testBubbleBelowLine() {
		grid.add(new ColourBubble(),0,1);
		assertFalse(grid.bubbleBelowLine(14));
		//Test just before the id which are checked.
		grid.add(new ColourBubble(),4,13);
		assertFalse(grid.bubbleBelowLine(14));
		//Test the last ColourBubble that's checked.
		grid.add(new ColourBubble(),5,14);
		assertTrue(grid.bubbleBelowLine(14));
		//Test the first ColourBubble that's checked.
		grid.add(new ColourBubble(),0,14);
		assertTrue(grid.bubbleBelowLine(14));
	}
}
