package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.Date;
import java.util.Observable;

/**
 * Loggable class.
 * Anything that extends this class will be made {@link Observable}.
 * Which means it will be "loggable".
 * @author group-15
 *
 */
public abstract class Loggable extends Observable {
	private long lastlog;

	/**
	 * The last log.
	 * @return the {@link #lastlog}
	 */
	public long lastLog() {
		return lastlog;
	}
	
	/**
	 * Notifies the {@link #Observer}.
	 * @param msg the message it will give to the {@link #Observer}.
	 */
	public void notifyObservers(String msg) {
		if (hasChanged() && new Date().getTime() > lastlog + 100) {
			super.notifyObservers(msg);
			lastlog = new Date().getTime();
		} else {
			super.notifyObservers();
		}
	}
}
