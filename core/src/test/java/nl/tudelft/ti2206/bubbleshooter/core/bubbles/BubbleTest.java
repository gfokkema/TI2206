package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import nl.tudelft.ti2206.bubbleshooter.core.Orientation;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * In this class we will test the functionality of our Bubble class.
 */
public class BubbleTest {
	
	private Bubble bubble;

	/**
	 * A new bubble is initiated, to be used through the class.
	 *
	 */
	@Before
	public void setUp() {
		this.bubble = new ColourBubble();
	}
	/**
	 * Checked whether the bubbles are initiated with the given color.
	 *
	 */
	@Test
	public void testBubble() {
		bubble = new ColourBubble(Color.RED);
		assertEquals(Color.RED, bubble.getColor());

		bubble = new ColourBubble(Color.BLUE);
		assertEquals(Color.BLUE, bubble.getColor());

		bubble = new ColourBubble(Color.ORANGE);
		assertEquals(Color.ORANGE, bubble.getColor());
	}

	/**
	 * Test whether the defaults of a bubble are instantiated correctly.
	 *
	 */
	@Test
	public void testDefaults() {
		assertEquals(32, bubble.getWidth());
		assertEquals(32, bubble.getHeight());
		assertEquals(TextureID.BUBBLE, bubble.getTexture());
	}

	/**
	 * Checked whether with the given bounds, overlaps gives the right output.
	 *
	 *
	@Test
	public void testCollide() {
		Circle c1 = new Circle(0, 0, 5);
		Circle c2 = new Circle(0, 5, 5);
		Circle c3 = new Circle(0, 10, 5);
		Bubble b = new ColourBubble();
		b.setBounds(c1);

		bubble.setBounds(c1);
		assertTrue(b.collides(bubble));

		bubble.setBounds(c2);
		assertTrue(b.collides(bubble));

		bubble.setBounds(c3);
		assertFalse(b.collides(bubble));

		b.setBounds(c2);
		assertTrue(b.collides(bubble));
	}
	/**
	 * Checks if the new the position, from given index with given orientation is.
	 *
	 */
	@Test
	public void testFromIndex() {
		Orientation ori;
		ori = Orientation.SOUTH_WEST;
		assertEquals(4, ori.fromIndex(1, 4));
		assertEquals(8, ori.fromIndex(4, 5));
		assertEquals(14, ori.fromIndex(10, 5));
		assertEquals(15, ori.fromIndex(10, 6));

		ori = Orientation.NORTH_EAST;
		assertEquals(1, ori.fromIndex(4, 4));
		assertEquals(4, ori.fromIndex(8, 5));
		assertEquals(10, ori.fromIndex(14, 5));
		assertEquals(10, ori.fromIndex(15, 6));
	}
	/**
	 * Checks whether the opposite orientation is correct
	 *
	 */
	@Test
	public void testOrientation() {
		Orientation ori;
		ori = Orientation.SOUTH_EAST;
		assertEquals(Orientation.NORTH_WEST, ori.getOpposite());
		ori = Orientation.EAST;
		assertEquals(Orientation.WEST, ori.getOpposite());
		ori = Orientation.NORTH_EAST;
		assertEquals(Orientation.SOUTH_WEST, ori.getOpposite());
	}
	
}
