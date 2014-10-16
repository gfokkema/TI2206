package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedSet;

public class FileHighscore {
	private SortedSet<HighScore> scores;

	public void addScore(String naam, int score) {
		loadScoreFile();
		scores.add(new HighScore(naam, score));
		updateScoreFile();
	}

	@SuppressWarnings("unchecked")
	public void loadScoreFile() {
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(
					"HighScores.txt"));
			scores = (SortedSet<HighScore>) inputStream.readObject();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private void updateScoreFile() {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(
					"HighScores.txt"));
			outputStream.writeObject(scores);

		} catch (Exception e) {
			System.out.println("erorroeorero");
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
}