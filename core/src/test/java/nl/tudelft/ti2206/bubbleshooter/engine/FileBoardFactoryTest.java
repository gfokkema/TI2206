package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Color;

public class FileBoardFactoryTest {
	private ArcadeBoardFactory factory;
	private ArcadeBoardFactory spy;
	private String testInput;
	private InputStream in;
	
	
	/**
	 * Setup.
	 * @throws IOException	when parsing went wrong
	 */
	@Before
	public void setUp() throws IOException {
		factory = new ArcadeBoardFactory();
		spy = spy(factory);
		testInput = "--- BEGIN --- .* ---\n5x5\nC00\n--- END --- .* ---";
		in = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
	}
	
	/**
	 * Test parsing a file.
	 * @throws IOException	when parsing went wrong
	 */
	@Test
	public void testParseFileInputStream() throws IOException {
		spy.parseFile(in);
		
		// called the parseFile with a bufferedreader
		Mockito.verify(spy).parseFile(any(InputStream.class));
	}
	
	/**
	 * Test ParseFile.
	 * Tests parsing the file.
	 * @throws IOException	when parsing went wrong
	 */
	@Test(expected = IOException.class)
	public void testParseBufferedReaderFileException() throws IOException {
		testInput = "throwAnIOException";
		in = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
		factory.parseFile(in);
	}
	
	@Test
	public void testParseLevel() throws IOException {
		// bogus simulated "file".
		spy.parseLevel("Test", "5x5\nC00");
		Mockito.verify(spy).parseType(anyString());
	}
	
	/**
	 * Test ParseType.
	 * Tests the various codes representing bubbles in the levels.
	 */
	@Test
	public void testParseType() {
		assertEquals(Color.RED, factory.parseType("C00").getColor());
		assertEquals(Color.GREEN, factory.parseType("C01").getColor());
		assertEquals(Color.BLUE, factory.parseType("C02").getColor());
		assertEquals(Color.PURPLE, factory.parseType("C03").getColor());
		assertEquals(Color.YELLOW, factory.parseType("C04").getColor());
		assertNotNull(factory.parseType("B00)"));
		assertNotNull(factory.parseType("S00)"));
		assertNotNull(factory.parseType("M00)"));
		assertNull(factory.parseType("A1337"));
		assertNull(factory.parseType("---"));
	}
}
