package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
 * This class tests all behaviour of the {@link Grid}, which is a container for {@link GridCell}s.
 */
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
		grid = new Grid("Test", 6, 15);
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
		assertEquals("Test", grid.getName());

	}

	/**
	 * Test adding a ColourBubble to the {@link Grid}, using a given {@link Grid} index.
	 */
	@Test
	public void testAddIndex() {
		assertNotNull(grid.add(new ColourBubble(), 0));
		assertNull(grid.add(new ColourBubble(), 0));
		assertFalse(grid.isEmpty());
	}

	/**
	 * Test adding a Projectile to the {@link Grid}, using only it's current position.
	 */
	@Test
	public void testAdd() {
		Circle c1 = new Circle(32 * 3 + 16, 480 - 32 * 0 - 16, 16);
		Circle c2 = new Circle(32 * 3 + 32, 480 - 32 * 1 - 16, 16);
		Circle c3 = new Circle(32 * 3 + 16, 480 - 32 * 2 - 16, 16);
		
		Projectile p = new Projectile(c1, new Vector2(), 0);
		p.setBounds(c1);
		assertNotNull(grid.add(p));
		p.setBounds(c2);
		assertNotNull(grid.add(p));
		assertNull(grid.add(p));
		p.setBounds(c3);
		assertNotNull(grid.add(p));
	}

	/**
	 * Test whether all the correct drawables are returned by this {@link Grid}.
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
		for (GridCell g : grid.getFilledGridCells()) assertTrue(drawables.contains(g));
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
	 * Test whether the grid detects it when a ColourBubble is below the losing line.
	 */
	@Test
	public void testBubbleBelowLine() {
		grid.add(new ColourBubble(),0,1);
		assertFalse(grid.bubbleBelowLine());
		assertFalse(grid.bubbleBelowLine(14));
		//Test just before the id which are checked.
		grid.add(new ColourBubble(),4,13);
		assertTrue(grid.bubbleBelowLine());
		assertFalse(grid.bubbleBelowLine(14));
		//Test the last ColourBubble that's checked.
		grid.add(new ColourBubble(),5,14);
		assertTrue(grid.bubbleBelowLine());
		assertTrue(grid.bubbleBelowLine(14));
		//Test the first ColourBubble that's checked.
		grid.add(new ColourBubble(),0,14);
		assertTrue(grid.bubbleBelowLine());
		assertTrue(grid.bubbleBelowLine(14));
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
	 * This tests the infamous IndexOutOfBoundsException, which crashes the game (or used to).
	 */
	@Test
	public void testAlmostOutOfBoundsLeft() {
		grid.add(new ColourBubble(), 0);
		
		Vector2 position = grid.cells.get(0).getPosition();
		position.y -= 16;
		position.x += 15;
		Projectile justBelow = new Projectile(new Circle(position, 16), new Vector2(1, 0), 0);
		assertFalse(grid.collides(justBelow));
		
		grid.add(justBelow);
		assertEquals(new Vector2(-1,0),justBelow.getDirection());

		// Move the projectile up, so that it collides
		position.y += 1;
		// Move it to the right, so that it's not out of bounds anymore.
		position.x += 1;

		// The bubble above now collides with the projectile.
		assertTrue(grid.collides(justBelow));

		// Add the bubble, without throwing an exception.
		grid.add(justBelow);
		assertEquals(justBelow, grid.cells.get(6).getBubble());
	}

	/**
	 * This tests the infamous IndexOutOfBoundsException, which crashes the game (or used to).
	 */
	@Test
	public void testAlmostOutOfBoundsRight() {
		grid.add(new ColourBubble(), 5);
		Vector2 position = grid.cells.get(5).getPosition();
		position.x += 16;
		position.y -= 16;
		Projectile justBelow = new Projectile(new Circle(position, 16), new Vector2(1, 0), 0);
		assertFalse(grid.collides(justBelow));
		assertEquals(new Vector2(-1,0),justBelow.getDirection());

		// Move the projectile up, so that it collides
		position.y += 1;
		// Move it to the left, so that it's not out of bounds anymore.
		position.x -= 1;
		justBelow = new Projectile(new Circle(position, 16), new Vector2(1, 0), 0);

		// The bubble above now collides with the projectile.
		assertTrue(grid.collides(justBelow));

		// Add the bubble, without throwing an exception.
		grid.add(justBelow);
		assertEquals(justBelow, grid.cells.get(10).getBubble());
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
	@Test
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

		grid = new Grid("Test", 20, 4);
		assertEquals(20, grid.getGridWidth());
		assertEquals(4, grid.getGridHeight());
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

		int disconnected_size = grid.removeDisconnected();
		Collection<GridCell> filled = grid.getFilledGridCells();
		assertEquals(3, filled.size());
		assertEquals(2, disconnected_size);
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
		int disconnected_size = grid.removeDisconnected();
		assertEquals(4, grid.size());
		assertEquals(0, disconnected_size);
	}
	
	/**
	 * Test whether the correct list of colors available is returned.
	 */
	@Test
	public void testColoursAvailable() {
		assertEquals(new ArrayList<Color>(), grid.getColoursAvailable());
		
		Color[] colors1 = {Color.BLUE};
		grid.add(new ColourBubble(Color.BLUE), 0);
		assertEquals(new ArrayList<Color>(Arrays.asList(colors1)), grid.getColoursAvailable());
		
		Color[] colors2 = {Color.BLUE, Color.RED};
		grid.add(new ColourBubble(Color.RED), 1);
		assertEquals(new ArrayList<Color>(Arrays.asList(colors2)), grid.getColoursAvailable());
		
		Color[] colors3 = {Color.BLUE, Color.RED, Color.GREEN};
		grid.add(new ColourBubble(Color.GREEN), 2);
		assertEquals(new ArrayList<Color>(Arrays.asList(colors3)), grid.getColoursAvailable());
	}
}
