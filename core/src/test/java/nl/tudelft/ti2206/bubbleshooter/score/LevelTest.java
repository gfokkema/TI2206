package nl.tudelft.ti2206.bubbleshooter.score;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the level class, which is used to store information together with a {@link HighScore}.
 * @author gerlof
 *
 */
public class LevelTest {
	private Level level;
	
	/**
	 * Set up a commonly used instance of level.
	 */
	@Before
	public void setUp() {
		this.level = new Level(1, "test");
	}

	/**
	 * Test the setters and getters.
	 */
	@Test
	public void test() {
		assertEquals(1, level.getLevel());
		assertEquals("test", level.getName());
	}
}
