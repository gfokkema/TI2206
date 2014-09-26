package nl.tudelft.ti2206.bubbleshooter.util;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Logger implements Observer {
	private static Logger logger = null;
	private ArrayList<LogStrategy> logstrats;
	
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
	
	public void addLog(LogStrategy strat) {
		logstrats.add(strat);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			logstrats.forEach((LogStrategy strat) ->
				strat.write(o.getClass().getSimpleName() + ": " + (String) arg)
			);
		}
	}
}