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
		assertEquals(Color.RED, factory.parseType("C00").getColor());
		assertEquals(Color.GREEN, factory.parseType("C01").getColor());
		assertEquals(Color.BLUE, factory.parseType("C02").getColor());
		assertEquals(Color.PURPLE, factory.parseType("C03").getColor());
		assertEquals(Color.YELLOW, factory.parseType("C04").getColor());
		assertNull(factory.parseType("---"));
	}
}
