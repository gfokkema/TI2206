package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BomBehaviourTest {

	/**
	 * Setup mocks
	 */
	@Mock Grid grid;
	BomBehaviour BBehaviour;
	
	/**
	 * Setup intialization and stubbing.
	 */
	@Before
	public void setUp(){
		BBehaviour = new BomBehaviour();
	}
	
	/**
	 * Test remove.
	 */
	@Test
	public void testRemoveAdjacent() {
		// stub specifically for adjacency
		Mockito.when(grid.adjacent(anyInt(), anyInt())).thenReturn(true);
		
		BBehaviour.remove(null);
		Mockito.verify(grid, atLeastOnce()).adjacent(anyInt(), anyInt());
	}
	
	@Test
	public void testRemoveNotAdjacent() {
		// stub again for the other route
		Mockito.when(grid.adjacent(anyInt(), anyInt())).thenReturn(false);
		assertEquals(0, BBehaviour.remove(null));
	}

}
