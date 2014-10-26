package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;

/**
 * The {@link EmptyGridCondition} checks whether a {@link Grid} is empty
 * and notifies it's {@link EndingObserver} when the {@link Grid} is won or lost.
 */
public class EmptyGridCondition extends EndingDecorator {
	private static final long serialVersionUID = 6007271279230285256L;

	/**
	 * Creates an {@link EmptyGridCondition} that decorates the specified {@link EndingCondition}.
	 * @param end	the specified {@link EndingCondition}
	 */
	public EmptyGridCondition(EndingCondition end) {
		super(end);
	}
	
	/**
	 * Check if the {@link Grid} is empty.
	 */
	@Override
	public void check(Grid grid) {
		if (grid.isEmpty()) this.won();
		super.check(grid);
	}
}
