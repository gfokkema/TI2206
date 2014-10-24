package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BomBehaviourTest {

	private BomBehaviour BBehaviour;
	private BomBehaviour spy;
	private GridCell gc;
	
	@Before
	public void setUp() {
		BBehaviour = new BomBehaviour();
		spy = spy(BBehaviour);
		gc = mock(GridCell.class);
		doNothing().when(gc).forEachNeighbor(anyObject());
		doNothing().when(gc).removeBubble();
	}
	
	@Test
	public void testChain() {
		spy.chain(gc);
		Mockito.verify(spy).trigger(gc);
	}
	
	@Test
	public void testTrigger() {
		spy.trigger(gc);
		Mockito.verify(spy).remove(gc);
	}
	
	@Test
	public void testRemove() {
		assertEquals(0, spy.remove(gc));
	}
}
