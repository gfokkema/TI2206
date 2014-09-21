package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public interface EndingCondition {

	/**
	 * Check whether the game should end
	 * @return true if the game should end.
	 */
	public int check(BSMode mode);

	public void addStatsObserver(StatsObserver o);
}
