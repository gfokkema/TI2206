package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.Serializable;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

/**
 * The EndingCondition of the various game modes.
 * It contains a check on whether a certain game should end.
 * Additionally it can add an observer for the statistics of the game.
 * @author group-15
 *
 */
public abstract class EndingCondition implements Serializable {
	private static final long serialVersionUID = -6763514295341944686L;
	transient StatsObserver statsObs;
	transient EndingObserver endingObs;

	/**
	 * Check whether the game should end
	 * @param board TODO
	 */
	public abstract void check(Board board);

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
