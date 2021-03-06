package nl.tudelft.ti2206.bubbleshooter.score;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.NavigableSet;

import nl.tudelft.ti2206.bubbleshooter.score.FileHighscore;
import nl.tudelft.ti2206.bubbleshooter.score.HighScore;
import nl.tudelft.ti2206.bubbleshooter.score.Score;

import org.junit.Before;
import org.junit.Test;

public class FileHighscoreTest {
	FileHighscore fhs = new FileHighscore();
	Score score = new Score(80, new Level(1, "Test"));
	HighScore highScore = new HighScore(score, "jan piet");
	NavigableSet<HighScore> scores;

	@Before
	public void initialize() {
		new File("test").delete();
		fhs.setFileName("test");
	}

	@Test
	public void testAddScore() {
		fhs.addScore(highScore);
		highScore = fhs.loadScoreFile().first();
		System.out.println(highScore.getScore());
		
		assertEquals("jan piet", highScore.getName());
		assertNotEquals("jan_pietje", highScore.getName());
		assertEquals(80, highScore.getScore());
	}

	@Test
	public void testCreateNewFile() {
		File f = new File(fhs.getFileName());
		f.delete();
		assertFalse(f.exists());

		fhs.addScore(highScore);
		assertTrue(f.exists());
	}

	@Test
	public void testGetFileName() {
		assertEquals("test.txt", fhs.getFileName());
	}

	@Test
	public void testSetFileName() {
		assertEquals("test.txt", fhs.getFileName());

		fhs.setFileName("test2");
		assertEquals("test2.txt", fhs.getFileName());

		fhs.setFileName("test");
		assertEquals("test.txt", fhs.getFileName());
	}

	@Test
	public void testLoadScoreFile() {
		File f = new File(fhs.getFileName());
		f.delete();
		fhs.loadScoreFile();
		fhs.addScore(new HighScore(score, "jan_liet"));

		scores = fhs.loadScoreFile();
		highScore = scores.first();

		assertEquals("jan_liet", highScore.getName());
		assertEquals(80, highScore.getScore());
	}
	
	@Test
	public void testIsHighScore() {
		fhs.loadScoreFile();
		
		Score score = new Score(100, new Level(1, "test"));
		assertTrue(fhs.isHighScore(score));
		
		for (int i = 0; i < 20; i++) {
			score.add(10);
			fhs.addScore(new HighScore(score, "test"));
		}
		
		score = new Score(50, new Level(1, "test"));
		assertFalse(fhs.isHighScore(score));
		
		score = new Score(200, new Level(1, "test"));
		assertTrue(fhs.isHighScore(score));
		
		score = new Score(50, new Level(2, "test"));
		assertTrue(fhs.isHighScore(score));
	}
}
