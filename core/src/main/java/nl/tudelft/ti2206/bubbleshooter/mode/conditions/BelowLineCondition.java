package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;

/**
 * The Single-Player {@link EndingCondition}.
 */
public class BelowLineCondition extends EndingDecorator {
	private static final long serialVersionUID = -4235795766520890630L;

	/**
	 * Creates a new {@link BelowLineCondition} that decorates the specified {@link EndingCondition}.
	 * @param end	the specified {@link EndingCondition}
	 */
	public BelowLineCondition(EndingCondition end) {
		super(end);
	}

	/**
	 * Checks whether there is a {@link Bubble} in a {@link GridCell} below the {@link Grid} losing line.
	 */
	@Override
	public void check(Grid grid) {
		if(grid.bubbleBelowLine()) {
			this.lost();
			return;
		}
		super.check(grid);
	}
}
