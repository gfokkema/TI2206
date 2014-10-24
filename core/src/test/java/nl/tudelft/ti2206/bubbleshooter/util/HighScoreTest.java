package nl.tudelft.ti2206.bubbleshooter.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class HighScoreTest {
	Score score = new Score(400, "Test");
	HighScore highscore = new HighScore(score, "tom_jones");

	@Test
	public void getNameTest() {
		assertTrue("tom_jones".equals(highscore.getName()));
		assertFalse("tom_cajones".equals(highscore.getName()));
	}

	@Test
	public void getScoreTest() {
		assertTrue(highscore.getScore() == 400);
		assertFalse(highscore.getScore() != 400);
		assertFalse(highscore.getScore() == 399);
	}
}
