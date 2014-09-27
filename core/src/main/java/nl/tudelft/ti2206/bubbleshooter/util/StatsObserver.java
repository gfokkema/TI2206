package nl.tudelft.ti2206.bubbleshooter.util;

import java.time.Duration;

/**
 * Observes the statistics of the game, such as score and timer.
 * @author group-15
 *
 */
public interface StatsObserver {
	
	/**
	 * Draws the timer in timed game modes.
	 * @param duration - The time that's left.
	 */
	public void drawTimer(Duration duration);
	
	/**
	 * Draws the score in all score-based game modes.
	 * @param score - the score.
	 */
	public void drawScore(int score);
}
