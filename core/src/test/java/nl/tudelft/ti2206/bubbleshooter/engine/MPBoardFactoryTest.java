package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

import org.junit.Before;
import org.junit.Test;

public class MPBoardFactoryTest {

	private MPBoardFactory factory;
	private MPBoardFactory spy;
	private List<Board> ls;
	
	@Before
	public void setUp() throws IOException {
		factory = new MPBoardFactory();
		spy = spy(factory);
		ls = new ArrayList<Board>();
	}
	
	/**
	 * Test makeLevels.
	 * This parses all the arcade levels.
	 * @throws IOException
	 */
	@Test
	public void testMakeLevels() throws IOException {
		doReturn(ls).when(spy).parseFile(anyString());
		assertEquals(ls, spy.makeLevels());
	}

}
