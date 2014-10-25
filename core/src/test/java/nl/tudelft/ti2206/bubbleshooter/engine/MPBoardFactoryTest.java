package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MPBoardFactoryTest {

	private MPBoardFactory factory;
	private MPBoardFactory spy;
	@Mock List<Grid> ls;
	@Mock Iterator<Grid> it;
	
	@Before
	public void setUp() throws IOException {
		factory = new MPBoardFactory();
		spy = spy(factory);
		Mockito.when(ls.iterator()).thenReturn(it);
	}
	
	/**
	 * Test makeLevels.
	 * This parses all the arcade levels.
	 * @throws IOException
	 */
	@Test
	public void testMakeLevels() throws IOException {
		doReturn(ls).when(spy).parseFile(anyString());
		assertEquals(it, spy.makeLevels());
	}

}
