package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

/**
 * The EndingCondition of the various game modes.
 * It contains a check on whether a certain game should end.
 * Additionally it can add an observer for the statistics of the game.
 * @author group-15
 *
 */
public interface EndingCondition {

	/**
	 * Check whether the game should end
	 * @return true if the game should end.
	 */
	public int check(BSMode mode);

	/**
	 * Add the {@link StatsObserver}.
	 * @param o the {@link StatsObserver}.
	 */
	public void addStatsObserver(StatsObserver o);
}
