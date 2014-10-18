package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.BomBehaviour;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;

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
	@Mock Board board;
	@Mock Grid grid;
	BomBehaviour BBehaviour;
	
	/**
	 * Setup intialization and stubbing.
	 */
	@Before
	public void setUp(){
		BBehaviour = new BomBehaviour();
		Mockito.when(board.getGrid()).thenReturn(grid);
	}
	
	/**
	 * Test remove.
	 */
	@Test
	public void testRemoveAdjacent() {
		// stub specifically for adjacency
		Mockito.when(grid.adjacent(anyInt(), anyInt())).thenReturn(true);
		
		BBehaviour.remove(board, 2, 3);
		Mockito.verify(board,atLeastOnce()).getGrid();
		Mockito.verify(grid, atLeastOnce()).adjacent(anyInt(), anyInt());
	}
	
	@Test
	public void testRemoveNotAdjacent() {
		// stub again for the other route
		Mockito.when(grid.adjacent(anyInt(), anyInt())).thenReturn(false);
		assertEquals(0, BBehaviour.remove(board, 2, 3));
	}

}