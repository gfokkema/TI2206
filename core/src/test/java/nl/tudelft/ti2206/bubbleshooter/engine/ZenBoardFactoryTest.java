package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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
		assertEquals(8, board.getGrid().getGridWidth());
		assertEquals(15, board.getGrid().getGridHeight());
	}

	@Test
	public void testBoardSize() {
		int i = 0;
		for (GridCell c : board.getGrid().cells.values()) {
			if (c.isOccupied()) i++;
		}
		assertEquals(40, i);
	}
}
