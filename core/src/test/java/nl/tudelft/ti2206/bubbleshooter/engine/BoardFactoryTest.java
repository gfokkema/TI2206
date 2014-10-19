package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardFactoryTest {
	private ZenBoardFactory factory;
	
	@Mock Board board;
	@Mock Grid grid;
	@Mock Bubble bubble;
	
	@Before
	public void setUp() {
		factory = new ZenBoardFactory();
		Mockito.when(board.getGrid()).thenReturn(grid);
	}
	
	@Test
	public void testAdd() {
		factory.add(board, new ColourBubble(), 0);
		Mockito.verify(board.getGrid()).add(anyObject(), eq(0));
		
		factory.add(board, new ColourBubble(), 0);
		Mockito.verify(board.getGrid(), times(2)).add(anyObject(), eq(0));
		
		factory.add(board, new ColourBubble(), 1);
		Mockito.verify(board.getGrid()).add(anyObject(), eq(1));
		
		factory.add(board, new ColourBubble(), 2);
		Mockito.verify(board.getGrid()).add(anyObject(), eq(2));
		
		factory.add(board, new ColourBubble(), 3);
		Mockito.verify(board.getGrid()).add(anyObject(), eq(3));
		
		Mockito.verify(board.getGrid(), times(5)).add(anyObject(), anyInt());
	}
	
	/**
	 * 
	 */
	@Test
	public void testAddBubble() {
		factory.add(board, bubble, 0, 0);
		Mockito.verify(board.getGrid()).add(bubble, 0, 0);
		
		factory.add(board, bubble, 0, 0);
		Mockito.verify(board.getGrid(), times(2)).add(bubble, 0, 0);
		
		factory.add(board, bubble, 0, 1);
		Mockito.verify(board.getGrid()).add(bubble, 0, 1);
		
		factory.add(board, bubble, 0, 2);
		Mockito.verify(board.getGrid()).add(bubble, 0, 2);
		
		factory.add(board, bubble, 0, 3);
		Mockito.verify(board.getGrid()).add(bubble, 0, 3);
		
		Mockito.verify(board.getGrid(), times(5)).add(anyObject(), anyInt(), anyInt());
	}
}
