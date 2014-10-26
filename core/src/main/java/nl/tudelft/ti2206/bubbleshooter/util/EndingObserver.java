package nl.tudelft.ti2206.bubbleshooter.util;

/**
 * Observes the EndingCondition, and gets called depending on the ending of a grid.
 */
public interface EndingObserver {
	/**
	 * Gets called when a grid was lost.
	 */
	public void lost();

	/**
	 * Gets called when a grid was won.
	 */
	public void won();

}
