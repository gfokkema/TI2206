package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.*;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Color;

public class ColourBehaviourTest {

	GridCell gc;
	ColourBehaviour BBehaviour;
	ColourBehaviour spy;
	
	/**
	 * Setup intialization and stubbing.
	 *
	 */
	@Before
	public void setUp(){
		gc = mock(GridCell.class, RETURNS_MOCKS);
		BBehaviour = new ColourBehaviour();
		spy = spy(BBehaviour);
	}
	
	/**
	 * Test remove.
	 *
	 */
	@Test
	public void testRemove() {
		spy.remove(gc);
		Mockito.verify(spy).remove(gc);
	}
	
	/**
	 * Test Trigger.
	 *
	 */
	@Test
	public void testTrigger() {
		assertEquals(0, spy.trigger(gc));
	}
	
	/**
	 * Test Trigger.
	 *
	 */
	@Test
	public void testCompareColours() {
		assertTrue(spy.compareColors(new ColourBubble(Color.RED), new ColourBubble(Color.RED)));
		assertFalse(spy.compareColors(new ColourBubble(Color.RED), new ColourBubble(Color.BLUE)));
	}
	
	/**
	 * Test Chain.
	 *
	 */
	@Test
	public void testChain() {
		assertEquals(1, spy.chain(gc));
		Mockito.verify(gc).removeBubble();
	
	}
}
