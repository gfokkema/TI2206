package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.Date;
import java.util.Observable;

public abstract class Loggable extends Observable {
	private long lastlog;

	public long lastLog() {
		return lastlog;
	}
	
	public void notifyObservers(String msg) {
		if (hasChanged() && new Date().getTime() > lastlog + 100) {
			super.notifyObservers(msg);
			lastlog = new Date().getTime();
		} else {
			super.notifyObservers();
		}
	}
}
