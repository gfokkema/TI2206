package nl.tudelft.ti2206.bubbleshooter.util;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.score.Score;

/**
 * Observes the statistics of the game, such as score and timer.
 */
public interface StatsObserver {
	
	/**
	 * Draws the timer in timed game modes.
	 * @param duration - The time that's left.
	 */
	public void updateTimer(Duration duration);
	
	/**
	 * Draws the score in all score-based game modes.
	 * @param score - the score.
	 */
	public void updateScore(Score score);
}
