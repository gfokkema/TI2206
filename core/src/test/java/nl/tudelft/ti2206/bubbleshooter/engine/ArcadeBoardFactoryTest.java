package nl.tudelft.ti2206.bubbleshooter.engine;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArcadeBoardFactoryTest {

	private ArcadeBoardFactory factory;
	private ArcadeBoardFactory spy;
	@Mock List<Grid> ls;
	@Mock Iterator<Grid> it;
	
	@Before
	public void setUp() throws IOException {
		factory = new ArcadeBoardFactory();
		spy = spy(factory);
		when(ls.iterator()).thenReturn(it);
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
