package nl.tudelft.ti2206.bubbleshooter.engine;
import java.io.IOException;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;

import org.junit.Before;
import org.junit.Test;

public class ArcadeBoardFactoryTest {

	private ArcadeBoardFactory factory;
	private ArcadeBoardFactory spy;
	private List<Grid> ls;
	
	@Before
	public void setUp() throws IOException {
		factory = new ArcadeBoardFactory();
	}
	
	/**
	 * Test makeLevels.
	 * This parses all the arcade levels.
	 * @throws IOException
	 */
	@Test
	public void testMakeLevels() throws IOException {
	}
}
