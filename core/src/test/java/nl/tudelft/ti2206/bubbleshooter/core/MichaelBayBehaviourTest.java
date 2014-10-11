package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MichaelBayBehaviourTest {

	/**
	 * Setup mocks
	 */
	@Mock Board board;
	@Mock Grid grid;
	@Mock Collection<Bubble> bubbles;
	MichaelBayBehaviour MBBehaviour;
	
	/**
	 * Setup intialization and stubbing.
	 */
	@Before
	public void setUp(){
		MBBehaviour = new MichaelBayBehaviour();
		Mockito.when(board.getGrid()).thenReturn(grid);
	}
	
	/**
	 * Test remove.
	 */
	@Test
	public void testRemoveAdjacent() {
		// stub specifically for adjacency
		Mockito.when(grid.adjacent(anyInt(), anyInt())).thenReturn(true);
		
		MBBehaviour.remove(board, 2, 3);
		Mockito.verify(board,atLeastOnce()).getGrid();
		Mockito.verify(grid, atLeastOnce()).adjacent(anyInt(), anyInt());
	}
	
	@Test
	public void testRemoveNotAdjacent() {
		// stub again for the other route
		Mockito.when(grid.adjacent(anyInt(), anyInt())).thenReturn(false);
		assertEquals(new ArrayList<Bubble>(), MBBehaviour.remove(board, 2, 3));
	}


}
