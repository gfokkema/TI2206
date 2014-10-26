package nl.tudelft.ti2206.bubbleshooter.logger;

import java.io.PrintStream;

/**
 * Screen logger.
 * Logs to the screen/console.
 * @author group-15
 *
 */
public class ScreenLogger implements LogStrategy {
	private PrintStream out;
	
	/**
	 * Creates a {@link ScreenLogger} that logs to the specified stream.
	 */
	public ScreenLogger(PrintStream o) {
		out = o;
	}
	
	/**
	 * Creates a {@link ScreenLogger} that logs to stdout.
	 */
	public ScreenLogger() {
		this(System.out);
	}
	
	/**
	 * Simply use a Java System println.
	 */
	@Override
	public void write(String msg) {
		out.println(msg);	
	}
}
