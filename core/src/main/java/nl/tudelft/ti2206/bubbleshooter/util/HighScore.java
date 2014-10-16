package nl.tudelft.ti2206.bubbleshooter.util;
/**
 * A class which creates a object for the HighScoresFile.
 * @author group-15
 *
 */
public class HighScore implements Comparable<HighScore> {
	private int highscore;
	private String name;
	
	/**
	 * A method to create a HighScore object.
	 * @param name  The name given by the player
	 * @param score The score of the player
	 */
	public HighScore(String name, int highscore) {
		this.highscore = highscore;
		this.name = name;
	}
	/**
	 * 
	 * @returns the score
	 */
	public int getHighScore() {
		return highscore;
	}
	/**
	 * 
	 * @returns the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * A method to set the score.
	 * @param score
	 */
	public void setHighScore(int score){
		this.highscore = score;
	}
	/**
	 * A method to set the name.
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * A method to help to SortedSet to become sorted.
	 */
	@Override
	public int compareTo(HighScore o) {
		return Integer.compare(this.highscore, o.highscore);
	}
}
