package nl.tudelft.ti2206.bubbleshooter.logger;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class tests the logging class and it's interactions with logstrategies.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoggerTest {
	private Bubble b = new ColourBubble();
	private Logger logger = Logger.getLogger();
	
	@Mock FileLogger filelog;
	@Mock ScreenLogger screenlog;
	
	/**
	 * Test logging without registered logstrategies.
	 */
	@Test
	public void testNoneLogStrategy() {
		logger.update(b, "test");
		Mockito.verify(filelog, never()).write(anyString());
		Mockito.verify(screenlog, never()).write(anyString());
	}
	
	/**
	 * Test logging with a registered file log strategy.
	 */
	@Test
	public void testFileLogStrategy() {
		logger.addLog(filelog);
		logger.update(b, "test");
		Mockito.verify(filelog, times(1)).write("ColourBubble: test");
		Mockito.verify(screenlog, never()).write("ColourBubble: test");
	}

	/**
	 * Test logging with a registered screen log strategy.
	 */
	@Test
	public void testScreenLogStrategy() {
		logger.addLog(screenlog);
		logger.update(b, "test");
		Mockito.verify(filelog, never()).write(anyString());
		Mockito.verify(screenlog, times(1)).write("ColourBubble: test");
	}

	/**
	 * Test logging with multiple registered logstrategies.
	 */
	@Test
	public void testMultipleLogStrategy() {
		logger.addLog(filelog);
		logger.addLog(screenlog);
		logger.update(b, "test");
		Mockito.verify(filelog, times(1)).write(anyString());
		Mockito.verify(screenlog, times(1)).write("ColourBubble: test");
	}
}
