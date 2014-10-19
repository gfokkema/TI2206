package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.MichaelBayBehaviour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MichaelBayBehaviourTest {

	/**
	 * Setup mocks
	 *
	 */
	private MichaelBayBehaviour MBBehaviour;
	private MichaelBayBehaviour spy;
	private GridCell gc;
	
	/**
	 * Setup intialization and stubbing.
	 */
	@Before
	public void setUp() {
		MBBehaviour = new MichaelBayBehaviour();
		spy = spy(MBBehaviour);
		gc = mock(GridCell.class);
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
