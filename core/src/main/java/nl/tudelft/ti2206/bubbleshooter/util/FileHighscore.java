package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * A class to read or write to the highscores file.
 * @author group-15
 */
public class FileHighscore {
	private NavigableSet<HighScore> scores;
	private String filename = "HighScores.txt";
	
	/**
	 * Constructor for creating a new FileHighscore instance
	 */
	public FileHighscore() {
		scores = new TreeSet<HighScore>();
		loadScoreFile();
	}

	/**
	 * A method to add a HighScore object
	 * 
	 * @param name
	 *            The name given by the player
	 * @param score
	 *            The score of the player
	 */
	public void addScore(HighScore score) {
		scores.add(score);
		updateScoreFile();
	}

	/**
	 * This method returns the filename of the used high score file
	 * @return	String with the filename
	 */
	public String getFileName() {
		return filename;
	}

	/**
	 * This method sets the filename of the used high score file
	 * @param filename	String with the filename
	 */
	public void setFileName(String filename) {
		this.filename = filename + ".txt";
	}

	/**
	 * A method to load the highscore file in, which closes the inputreader
	 * after the file is read
	 * @return	{@link NavigableSet} of {@link HighScore} objects
	 */
	@SuppressWarnings("unchecked")
	public NavigableSet<HighScore> loadScoreFile() {
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(getFileName()));
			scores = (NavigableSet<HighScore>) inputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return scores;
	}

	/**
	 * A method which writes back to the highscores file, and closes the
	 * outputreader after
	 */
	private void updateScoreFile() {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(getFileName()));
			outputStream.writeObject(scores);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.reset();
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method evaluates whether a score should be added to the highscores
	 * @param score	the score that has to be checked against the list
	 * @return		boolean indicating whether this is a highscore
	 */
	public boolean isHighScore(Score score) {
		return scores.size() < 20 || score.getScore() > scores.first().getScore();
	}
}