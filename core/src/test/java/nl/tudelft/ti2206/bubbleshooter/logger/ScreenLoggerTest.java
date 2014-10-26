package nl.tudelft.ti2206.bubbleshooter.logger;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScreenLoggerTest {
	private ScreenLogger logger;
	
	@Mock PrintStream out;

	@Before
	public void setUp() {
		logger = new ScreenLogger(out);
	}
	
	@Test
	public void testWrite() {
		logger.write("test");
		Mockito.verify(out, times(1)).println("test");
	}
}
