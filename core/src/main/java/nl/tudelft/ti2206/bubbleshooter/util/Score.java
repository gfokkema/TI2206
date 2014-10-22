package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.Serializable;

import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;

/**
 * A class which creates a object for the scoresFile.
 * @author group-15
 *
 */
public class Score implements Comparable<Score>, Serializable {
	private static final long serialVersionUID = 2603273380851092688L;
	private int score;
	private String level;
	private StatsObserver scoreObs;
	
	/**
	 * A method to create a score object.
	 * @param name		The name given by the player
	 * @param score	The score of the player
	 */
	public Score(int score, String level) {
		this.score = score;
		this.level = level;
	}
	
	/**
	 * A method to create a score object.
	 * @param name		The name given by the player
	 * @param score	The score of the player
	 */
	public Score(Score score) {
		this.score = score.score;
		this.level = score.level;
	}
	
	public void add(int points) {
		this.score += points;
		scoreObs.updateScore(this);
	}
	
	/**
	 * This method returns the score associated with this {@link Score}.
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * This method returns the level associated with this {@link Score}.
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Override method compareTo of the implemented comparable interface
	 * @return the rank value after it compares it with {@score o} 
	 */
	@Override
	public int compareTo(Score o) {
		return Integer.compare(this.score, o.score);
	}
	
	/**
	 * Add the {@link StatsObserver} to the {@link EndingCondition}.
	 * @param obs the statsobserver.
	 */
	public void addStatsObserver(StatsObserver obs) {
		this.scoreObs = obs;
	}
}
