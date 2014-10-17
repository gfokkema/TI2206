package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A class to read or write to the highscores file.
 * 
 * @author group-15
 *
 */
public class FileHighscore {
	private SortedSet<HighScore> scores;
	private String filename = "HighScores.txt";
	
	public FileHighscore() {
		scores = new TreeSet<HighScore>();
	}
	


	/**
	 * A method to add a HighScore object
	 * 
	 * @param name
	 *            The name given by the player
	 * @param score
	 *            The score of the player
	 */
	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new HighScore(name, score));
		updateScoreFile();
	}

	public void createNewFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(getFileName(), "UTF-8");
		} catch (Exception e) {

			e.printStackTrace();
		}
		writer.close();
	}

	public String getFileName() {

		return filename;
	}

	public void setFileName(String filename) {		
		StringBuilder sb = new StringBuilder();
		sb.append(filename+".txt");
		this.filename = sb.toString();
		
		
	}

	/**
	 * A method to load the highscore file in, which closes the inputreader
	 * after the file is read.
	 */
	@SuppressWarnings("unchecked")
	public SortedSet<HighScore> loadScoreFile() {
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(
					getFileName()));

			scores = (SortedSet<HighScore>) inputStream.readObject();
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
	 * outputreader after.
	 */
	private void updateScoreFile() {
		ObjectOutputStream outputStream = null;
		try {

			outputStream = new ObjectOutputStream(new FileOutputStream(
					getFileName()));
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
}