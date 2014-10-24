package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;

public class EmptyGridCondition extends EndingDecorator {
	private static final long serialVersionUID = 6007271279230285256L;

	public EmptyGridCondition(EndingCondition end) {
		super(end);
	}
	
	/**
	 * Check if the {@link Board} is empty.
	 */
	@Override
	public void check(Grid grid) {
		if (grid.isEmpty()) this.won();
		super.check(grid);
	}
}
