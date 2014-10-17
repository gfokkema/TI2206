package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;

import com.badlogic.gdx.Gdx;

/**
 * A class to read or write to the highscores file.
 * @author group-15
 *
 */
public class FileHighscore {
	private SortedSet<HighScore> scores;
	
	public FileHighscore() {
		scores = new TreeSet<HighScore>();
	}

	/**
	 * A method to add a HighScore object
	 * @param name  The name given by the player
	 * @param score The score of the player
	 */
	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new HighScore(name, score));
		updateScoreFile();
	}

	/**
	 * A method to load the highscore file in, which closes the inputreader after the file is read.
	 */
	@SuppressWarnings("unchecked")
	public SortedSet<HighScore> loadScoreFile() {
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(Gdx.files.internal("HighScores.txt").read());
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
		return scores;
	}
	/**
	 *  A method which writes back to the highscores file, and closes the outputreader after.
	 */
	private void updateScoreFile() {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(Gdx.files.internal("HighScores.txt").write(false));
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