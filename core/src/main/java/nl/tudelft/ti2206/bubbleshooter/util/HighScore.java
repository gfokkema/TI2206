package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.Serializable;

/**
 * A class which creates a object for the HighScoresFile.
 * @author group-15
 *
 */
public class HighScore implements Comparable<HighScore>, Serializable {
	private static final long serialVersionUID = 2603273380851092688L;
	private int highscore;
	private String name;
	
	/**
	 * A method to create a HighScore object.
	 * @param name		The name given by the player
	 * @param highscore	The score of the player
	 */
	public HighScore(String name, int highscore) {
		this.highscore = highscore;
		this.name = name;
	}
	
	/**
	 * This method returns the score associated with this {@link HighScore}.
	 * @return the score
	 */
	public int getHighScore() {
		return highscore;
	}
	
	/**
	 * This method returns the name associated with this {@link HighScore}.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Override method compareTo of the implemented comparable interface
	 * @return the rank value after it compares it with {@Highscore o} 
	 */
	@Override
	public int compareTo(HighScore o) {
		return Integer.compare(this.highscore, o.highscore);
	}
}
