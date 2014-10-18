package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.MichaelBayBehaviour;

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
		assertEquals(0, MBBehaviour.remove(board, 2, 3));
	}


}