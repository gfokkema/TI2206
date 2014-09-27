package nl.tudelft.ti2206.bubbleshooter.util;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The logger class.
 * This is the {@link Observer} in the Observer pattern.
 * It contains the {@link #update} method which will be called upon 
 * notification of the {@link Observer} by the {@link Observable}.
 * @author group-15
 *
 */
public class Logger implements Observer {
	private static Logger logger = null;
	private ArrayList<LogStrategy> logstrats;
	
	/**
	 * Constructor for Logger.
	 */
	private Logger() {
		logstrats = new ArrayList<>();
	}
	
	/**
	 * Singleton pattern for a global logger.
	 * @return	the unique instance of {@link Logger}
	 */
	public static Logger getLogger() {
		if (logger == null) logger = new Logger();
		
		return logger;
	}
	
	/**
	 * Add the {@link #LogStrategy} to the list.
	 * @param strat the {@link #LogStrategy}
	 */
	public void addLog(LogStrategy strat) {
		logstrats.add(strat);
	}

	/**
	 * Update method.
	 * Is called when it is notified by its {@link Observable}.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			logstrats.forEach((LogStrategy strat) ->
				strat.write(o.getClass().getSimpleName() + ": " + (String) arg)
			);
		}
	}
}