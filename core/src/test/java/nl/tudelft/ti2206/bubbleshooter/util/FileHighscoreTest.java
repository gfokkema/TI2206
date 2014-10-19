package nl.tudelft.ti2206.bubbleshooter.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.NavigableSet;

import org.junit.Before;
import org.junit.Test;

public class FileHighscoreTest {
	FileHighscore fhs = new FileHighscore();
	HighScore score = new HighScore("jan_piet", 80);
	NavigableSet<HighScore> scores;

	@Before
	public void initialize() {
		fhs.setFileName("test");
	}

	@Test
	public void testAddScore() {

		fhs.checkHighScoreFile();
		fhs.addScore("jan_piet", 80);

		scores = fhs.loadScoreFile();
		score = scores.first();

		int points = score.getHighScore();
		String name = score.getName();

		assertTrue(name.equals("jan_piet"));
		assertFalse(name.equals("jan_pietje"));

		assertTrue(points == 80);
		assertFalse(points != 80);

	}

	@Test
	public void testCreateNewFile() {
		File f = new File(fhs.getFileName());
		f.delete();
		assertFalse(f.exists());

		fhs.addScore(null, 0);
		assertTrue(f.exists());
	}

	@Test
	public void testGetFileName() {
		assertTrue(fhs.getFileName().equals("test.txt"));
	}

	@Test
	public void testSetFileName() {
		assertTrue(fhs.getFileName().equals("test.txt"));

		fhs.setFileName("test2");
		assertTrue(fhs.getFileName().equals("test2.txt"));
		assertFalse(fhs.getFileName().equals("test.txt"));

		fhs.setFileName("test");
		assertTrue(fhs.getFileName().equals("test.txt"));
		assertFalse(fhs.getFileName().equals("test2.txt"));

	}

	@Test
	public void testLoadScoreFile() {
		File f = new File(fhs.getFileName());
		f.delete();
		System.out.println("ok");
		fhs.loadScoreFile();
		fhs.checkHighScoreFile();
		fhs.addScore("jan_liet", 60);

		scores = fhs.loadScoreFile();
		score = scores.first();

		int points = score.getHighScore();
		String name = score.getName();

		assertTrue(name.equals("jan_liet"));
		assertFalse(name.equals("jan_lietje"));

		assertTrue(points == 60);
		assertFalse(points != 60);

	}

	@Test
	public void testIsHighScore() {
		fhs.addScore("jan_piet", 40);
		fhs.addScore("jan_doet", 21);
		assertTrue(fhs.isHighScore(20));

		for (int i = 20; i < 60; i++) {
			fhs.addScore("jan_piet", i);
			fhs.addScore("jan_doet" + i, i + 1);
		}
		assertFalse(fhs.isHighScore(8));

	}

}
