package nl.tudelft.ti2206.bubbleshooter.util;

/**
 * Interface used for the strategy pattern for Logging.
 * It has a write, determining to what the logging should happen.
 * @author group-15
 *
 */
public interface LogStrategy {
	
	/**
	 * Write to something.
	 * @param msg the message to be written
	 */
	public void write(String msg);
}
