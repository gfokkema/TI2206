package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ZenBoardFactoryTest {
	private ZenBoardFactory factory;
	private Iterator<Board> boards;
	private Board board;
	
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
}
