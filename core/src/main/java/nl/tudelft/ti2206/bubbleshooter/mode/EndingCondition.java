package nl.tudelft.ti2206.bubbleshooter.mode;

public interface EndingCondition {

	/**
	 * Check whether the game should end
	 * @return true if the game should end.
	 */
	public boolean check(BSMode mode);
}
