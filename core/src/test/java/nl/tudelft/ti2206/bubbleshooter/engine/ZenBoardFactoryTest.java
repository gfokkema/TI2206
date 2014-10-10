package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ZenBoardFactoryTest {
	private ZenBoardFactory factory;
	private Iterator<Board> boards;
	private Board board;
	
	@Mock Board b;
	@Mock Bubble bubble;
	
	@Before
	public void setUp() {
		factory = new ZenBoardFactory();
		boards = factory.makeLevels().iterator();
		board = boards.next();
	}
	
	@Test
	public void testBoardDim() {
		assertEquals(8, board.getGrid().getWidth());
		assertEquals(15, board.getGrid().getHeight());
	}

	@Test
	public void testBoardSize() {
		assertEquals(40, board.getBubbles().size());
	}
	
	@Test
	public void testAdd() {
		factory.add(b, 0);
		Mockito.verify(b).add(anyObject(), eq(0));
		
		factory.add(b, 0);
		Mockito.verify(b, times(2)).add(anyObject(), eq(0));
		
		factory.add(b, 1);
		factory.add(b, 2);
		factory.add(b, 3);
		Mockito.verify(b, times(5)).add(anyObject(), anyInt());
	}
	
	@Test
	public void testAddParse() {
		
	}
}
