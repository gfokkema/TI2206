package nl.tudelft.ti2206.bubbleshooter.score;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

/**
 * A class which creates a object for the scoresFile.
 */
public class Score implements Comparable<Score>, Serializable {
	private static final long serialVersionUID = 2603273380851092688L;
	private int score;
	private Level level;
	private transient List<StatsObserver> statsObservers;
	
	/**
	 * A method to create a score object.
	 * @param score		the startingscore of the player
	 * @param level		the level associated with this score
	 */
	public Score(int score, Level level) {
		this.score = score;
		this.level = level;
		this.statsObservers = new ArrayList<StatsObserver>();
	}

	/**
	 * A method to crate a score object.
	 * @param score		the starting score of the player
	 */
	public Score(int score) {
		this(score, new Level(1, "No level name"));
	}
	
	/**
	 * A method to create a score object from another score object.
	 * @param score		another score object
	 */
	public Score(Score score) {
		this(score.score, score.level);
	}
	
	/**
	 * This method adds a number of points to this {@link Score}.
	 * @param points	the number of points
	 */
	public void add(int points) {
		this.score += points;
		updateObservers();
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
	public Level getLevel() {
		return level;
	}
	
	/**
	 * This method updates the level associated with this {@link Score}.
	 * @param score
	 */
	public void setLevel(Level level) {
		this.level = level;
		updateObservers();
	}
	
	/**
	 * This method updates this {@link Score} using another {@link Score}.
	 * @param score	the other {@link Score}
	 */
	public void update(Score score) {
		this.score = score.score;
		this.level = score.level;
		updateObservers();
	}
	
	public void updateObservers() {
		statsObservers.forEach(statsObs -> statsObs.updateScore(this));
	}
	/**
	 * Override method compareTo of the implemented comparable interface
	 * @return the rank value after it compares it with {@score o} 
	 */
	@Override
	public int compareTo(Score o) {
		return level.getLevel() == o.level.getLevel() ? 
					Integer.compare(this.score, o.score) :
					Integer.compare(level.getLevel(), o.level.getLevel());
	}
	
	/**
	 * Add the {@link StatsObserver} to the {@link EndingCondition}.
	 * @param obs the statsobserver.
	 */
	public void addStatsObserver(StatsObserver obs) {
		statsObservers.add(obs);
	}
}
