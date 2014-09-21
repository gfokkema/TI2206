package nl.tudelft.ti2206.bubbleshooter.util;

import java.time.Duration;

public interface StatsObserver {
	/**
	 * Draws the timer in timed game modes.
	 * @param duration - The time that's left.
	 */
	public void drawTimer(Duration duration);
	public void drawScore(int score);
}
