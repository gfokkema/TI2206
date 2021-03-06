package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import java.io.Serializable;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

/**
 * The EndingCondition of the various game modes.
 * It contains a check on whether a certain game should end.
 * Additionally it can add an observer for the statistics of the game.
 */
public abstract class EndingCondition implements Serializable {
	private static final long serialVersionUID = -6763514295341944686L;
	transient StatsObserver statsObs;
	transient EndingObserver endingObs;

	/**
	 * Check whether the game should end
	 * @param grid	the grid that should be checked
	 */
	public abstract void check(Grid grid);

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

	/**
	 * Add the {@link EndingObserver}.
	 * @param o	the {@link EndingObserver}
	 */
	public void addEndingObserver(EndingObserver o) {
		this.endingObs = o;
	}
}
