package nl.tudelft.ti2206.bubbleshooter.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class HighScoreTest {
	HighScore highscore = new HighScore("tom_jones", 400);

	@Test
	public void getNameTest() {
		assertTrue("tom_jones".equals(highscore.getName()));
		assertFalse("tom_cajones".equals(highscore.getName()));
	}

	@Test
	public void getScoreTest() {
		assertTrue(highscore.getHighScore() == 400);
		assertFalse(highscore.getHighScore() != 400);
		assertFalse(highscore.getHighScore() == 399);
	}
}
