package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StoneBubbleBehaviourTest {

	private StoneBehaviour StoneB;
	private StoneBehaviour spy;
	private GridCell gc;
	
	@Before
	public void setUp() {
		StoneB = new StoneBehaviour();
		spy = spy(StoneB);
		gc = mock(GridCell.class);
	}
	
	@Test
	public void testChain() {
		assertEquals(1, spy.chain(gc));
		Mockito.verify(gc).removeBubble();
	}
	
	@Test
	public void testTrigger() {
		assertEquals(0, spy.trigger(gc));
	}
	
	@Test
	public void testRemove() {
		assertEquals(0, spy.remove(gc));
	}

}
