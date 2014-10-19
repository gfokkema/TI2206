package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
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
	
	@Mock Grid grid;
	@Mock Bubble bubble;
	
	@Before
	public void setUp() {
		factory = new ZenBoardFactory();
	}
	
	@Test
	public void testAdd() {
		factory.add(grid, new ColourBubble(), 0);
		Mockito.verify(grid).add(anyObject(), eq(0));
		
		factory.add(grid, new ColourBubble(), 0);
		Mockito.verify(grid, times(2)).add(anyObject(), eq(0));
		
		factory.add(grid, new ColourBubble(), 1);
		Mockito.verify(grid).add(anyObject(), eq(1));
		
		factory.add(grid, new ColourBubble(), 2);
		Mockito.verify(grid).add(anyObject(), eq(2));
		
		factory.add(grid, new ColourBubble(), 3);
		Mockito.verify(grid).add(anyObject(), eq(3));
		
		Mockito.verify(grid, times(5)).add(anyObject(), anyInt());
	}
	
	/**
	 * 
	 */
	@Test
	public void testAddBubble() {
		factory.add(grid, bubble, 0, 0);
		Mockito.verify(grid).add(bubble, 0, 0);
		
		factory.add(grid, bubble, 0, 0);
		Mockito.verify(grid, times(2)).add(bubble, 0, 0);
		
		factory.add(grid, bubble, 0, 1);
		Mockito.verify(grid).add(bubble, 0, 1);
		
		factory.add(grid, bubble, 0, 2);
		Mockito.verify(grid).add(bubble, 0, 2);
		
		factory.add(grid, bubble, 0, 3);
		Mockito.verify(grid).add(bubble, 0, 3);
		
		Mockito.verify(grid, times(5)).add(anyObject(), anyInt(), anyInt());
	}
}
