package nl.tudelft.ti2206.bubbleshooter.util;

/**
 * Classes implementing this interface are notified when the whole game is won or lost.
 */
public interface GameObserver {
	/**
	 * Called when the whole game (ie. all boards) was lost.
	 */
	public void switchToLostScreen();
	
	/**
	 * Called when the whole game (ie. all boards) was won.
	 */
	public void switchToWonScreen();
}
