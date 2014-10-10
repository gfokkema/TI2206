package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

/**
 * Basic ending condition which ends the game once the {@link Board} is empty.
 * @author group-15
 *
 */
public class BasicCondition extends EndingCondition {
	private static final long serialVersionUID = 1260210603147506145L;

	/**
	 * Check if the {@link Board} is empty.
	 */
	@Override
	public void check(Board board) {
		if (board.isEmpty()) this.won();
	}

	@Override
	public void lost() {
		endingObs.lost();
	}

	@Override
	public void won() {
		endingObs.won();
	}

}
