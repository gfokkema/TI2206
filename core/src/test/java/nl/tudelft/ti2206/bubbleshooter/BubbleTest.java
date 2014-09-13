package nl.tudelft.ti2206.bubbleshooter;

import static org.junit.Assert.*;
import nl.tudelft.ti2206.bubbleshooter.Bubble.Orientation;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;

public class BubbleTest {
	private Bubble bubble;
	@Before
	public void setUp() {
		this.bubble = new Bubble();
	}

	@Test
	public void testBubble() {
		bubble = new Bubble(Color.RED);
		assertEquals(Color.RED, bubble.getColor());
		
		bubble = new Bubble(Color.BLUE);
		assertEquals(Color.BLUE, bubble.getColor());
		
		bubble = new Bubble(Color.ORANGE);
		assertEquals(Color.ORANGE, bubble.getColor());
	}
	
	@Test
	public void testBounds() {
		Circle c = new Circle(0, 0, 10);
		
		bubble.setBounds(c);
		assertEquals(c, bubble.getBounds());
	}
	/**
	 * Check whether with the given bounds, overlaps gives the right output.
	 */
	@Test
	public void testCollide() {
		Circle c1 = new Circle(0, 0, 5);
		Circle c2 = new Circle(0, 5, 5);
		Circle c3 = new Circle(0, 10, 5);
		Bubble b = new Bubble();
		b.setBounds(c1);
		
		bubble.setBounds(c1);
		assertTrue(bubble.bounds.overlaps(b.bounds));
		
		bubble.setBounds(c2);
		assertTrue(bubble.bounds.overlaps(b.bounds));
		
		bubble.setBounds(c3);
		assertFalse(bubble.bounds.overlaps(b.bounds));
		
		b.setBounds(c2);
		assertTrue(bubble.bounds.overlaps(b.bounds));
	}
	
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
