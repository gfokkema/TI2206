package nl.tudelft.ti2206.bubbleshooter.util;

/**
 * Screen logger.
 * Logs to the screen/console.
 * @author group-15
 *
 */
public class ScreenLogger implements LogStrategy {
	
	/**
	 * Simply use a Java System println.
	 */
	@Override
	public void write(String msg) {
		System.out.println(msg);	
	}
}
