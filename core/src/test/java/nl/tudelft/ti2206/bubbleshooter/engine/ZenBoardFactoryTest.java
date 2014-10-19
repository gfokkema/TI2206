package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

import org.junit.Before;
import org.junit.Test;

public class ZenBoardFactoryTest {
	private ZenBoardFactory factory;
	private Iterator<Grid> grids;
	private Grid grid;
	
	@Before
	public void setUp() {
		factory = new ZenBoardFactory();
		grids = factory.makeLevels().iterator();
		grid = grids.next();
	}
	
	@Test
	public void testBoardDim() {
		assertEquals(8, grid.getGridWidth());
		assertEquals(15, grid.getGridHeight());
	}

	@Test
	public void testBoardSize() {
		int i = 0;
		for (GridCell c : grid.cells.values()) {
			if (c.isOccupied()) i++;
		}
		assertEquals(40, i);
	}
}
