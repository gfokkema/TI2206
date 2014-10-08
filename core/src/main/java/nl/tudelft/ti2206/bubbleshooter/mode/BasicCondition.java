package nl.tudelft.ti2206.bubbleshooter.mode;

/**
 * Basic ending condition which ends the game once the {@link Board} is empty.
 * @author group-15
 *
 */
public class BasicCondition extends EndingCondition {

	/**
	 * Check if the {@link Board} is empty.
	 */
	@Override
	public void check(BSMode mode) {
		if (mode.board.isEmpty()) this.won();
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
