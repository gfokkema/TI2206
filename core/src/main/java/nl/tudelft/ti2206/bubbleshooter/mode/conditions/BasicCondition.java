package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;

/**
 * Basic ending condition which does nothing.
 * Decorators can use this {@link EndingCondition} as a starting point.
 */
public class BasicCondition extends EndingCondition {
	private static final long serialVersionUID = 1260210603147506145L;

	/**
	 * Empty check method.
	 */
	@Override
	public void check(Grid grid) {}

	/**
	 * Notify the {@link EndingObserver} when the game is lost.
	 */
	@Override
	public void lost() {
		endingObs.lost();
	}

	/**
	 * Notify the {@link EndingObserver} when the game is won.
	 */
	@Override
	public void won() {
		endingObs.won();
	}

}
