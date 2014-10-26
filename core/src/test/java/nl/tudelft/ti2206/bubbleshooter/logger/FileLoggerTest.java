package nl.tudelft.ti2206.bubbleshooter.logger;

import static org.mockito.Mockito.times;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;

import java.io.BufferedWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileLoggerTest {
	private FileLogger logger;
	
	@Mock BufferedWriter bw;

	@Before
	public void setUp() {
		logger = new FileLogger(bw);
	}
	
	@Test
	public void testWrite() throws IOException {
		logger.write("test");
		Mockito.verify(bw, times(1)).write("test\n");
	}
	
	@Test
	public void testWriteInvalid() throws IOException {
		Mockito.doThrow(new IOException()).when(bw).write(anyString());
		logger.write("test");
		Mockito.verify(bw, times(1)).write("test\n");
		Mockito.verify(bw, never()).flush();
	}
}
