package nl.tudelft.ti2206.bubbleshooter.mode;

/**
 * Zen {@link EndingCondition} is a special ending condition where there is no pressure on the user.
 * There is no timer and no score.
 * This is comparable to an endless-mode.
 * @author group-15
 *
 */
public class BasicCondition extends EndingCondition {

	/**
	 * Check if the {@link Board} is empty.
	 * If so, the {@link Board} should be refilled (not yet done).
	 */
	@Override
	public int check(BSMode mode) {
		return mode.board.isEmpty() ? 1 : 0;
	}

}
