package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;

/**
 * The Single-Player {@link EndingCondition}.
 * @author group-15
 *
 */
public class BelowLineCondition extends EndingDecorator {
	private static final long serialVersionUID = -4235795766520890630L;

	public BelowLineCondition(EndingCondition end) {
		super(end);
	}

	/**
	 * The actual {@link EndingCondition} for the Single-Player mode.
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
