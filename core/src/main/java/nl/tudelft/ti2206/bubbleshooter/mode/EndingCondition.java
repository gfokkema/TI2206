package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

/**
 * The EndingCondition of the various game modes.
 * It contains a check on whether a certain game should end.
 * Additionally it can add an observer for the statistics of the game.
 * @author group-15
 *
 */
public abstract class EndingCondition {
	StatsObserver statsObs;
	EndingObserver endingObs;

	/**
	 * Check whether the game should end
	 */
	public abstract void check(BSMode mode);

	/**
	 * Called when the level is lost.
	 */
	public abstract void lost();

	/**
	 * Called when the level is won.
	 */
	public abstract void won();

	/**
	 * Add the {@link StatsObserver}.
	 * @param o the {@link StatsObserver}.
	 */
	public void addStatsObserver(StatsObserver o) {
		this.statsObs = o;
	}

	public void addEndingObserver(EndingObserver o) {
		this.endingObs = o;
	}
}
