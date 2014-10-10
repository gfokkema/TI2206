package nl.tudelft.ti2206.bubbleshooter.util;

/**
 * Observes the EndingCondition, and gets called depending
 * on the ending of the game.
 * @author group-15
 *
 */
public interface EndingObserver {
	/**
	 * Gets called when a board is won.
	 */
	public void wonBoard();

	/**
	 * Gets called when the game was lost.
	 */
	public void lost();

	/**
	 * Gets called when the game was won.
	 */
	public void won();

}
