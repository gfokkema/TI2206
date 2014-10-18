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

	@Test
	public void setHighScoreTest() {
		highscore.setHighScore(250);
		assertTrue(highscore.getHighScore() == 250);
		assertFalse(highscore.getHighScore() == 251);

		highscore.setHighScore(450);
		assertTrue(highscore.getHighScore() == 450);
		assertFalse(highscore.getHighScore() == 201);
	}

	@Test
	public void setNameTest() {
		highscore.setName("Shaggy");
		assertTrue(highscore.getName().equals("Shaggy"));
		assertFalse(highscore.getName().equals("tom_jones"));

		highscore.setName("tom_jones");
		assertFalse(highscore.getName().equals("Shaggy"));
		assertTrue(highscore.getName().equals("tom_jones"));
	}

}
