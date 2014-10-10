package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

public class FileBoardFactoryTest {
	private ArcadeBoardFactory factory;
	
	@Before
	public void setUp() {
		factory = new ArcadeBoardFactory();
	}
	
	@Test
	public void testParse() {
		assertEquals(Color.RED, factory.parse("00").getColor());
		assertEquals(Color.GREEN, factory.parse("01").getColor());
		assertEquals(Color.BLUE, factory.parse("02").getColor());
		assertEquals(Color.PURPLE, factory.parse("03").getColor());
		assertEquals(Color.YELLOW, factory.parse("04").getColor());
		assertNull(factory.parse("--"));
	}
}
