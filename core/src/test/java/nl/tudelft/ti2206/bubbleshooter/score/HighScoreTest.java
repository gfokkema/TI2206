package nl.tudelft.ti2206.bubbleshooter.score;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.tudelft.ti2206.bubbleshooter.score.HighScore;
import nl.tudelft.ti2206.bubbleshooter.score.Score;

import org.junit.Test;

public class HighScoreTest {
	Score score = new Score(400, new Level(1, "Test"));
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
