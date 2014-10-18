package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Color;

public class FileBoardFactoryTest {
	private ArcadeBoardFactory factory;
	private ArcadeBoardFactory spy;
	private BufferedReader br;
	
	/**
	 * Setup.
	 * @throws IOException 
	 */
	@Before
	public void setUp() throws IOException {
		factory = new ArcadeBoardFactory();
		spy = spy(factory);
		br = mock(BufferedReader.class);
	}
	
	@Test
	public void testParseFileString() {
		// TODO use an actual demo file.
	}
	
	/**
	 * Test ParseFile.
	 * Tests parsing the file.
	 * @throws IOException 
	 */
	@Test(expected = IOException.class)
	public void testParseBufferedReaderFileException() throws IOException {
		Mockito.when(br.readLine()).thenReturn("something");
		factory.parseFile(br);
	}
	
	
	/**
	 * Test ParseFile.
	 * Tests parsing the file.
	 * @throws IOException 
	 */
	@Test
	public void testParseFileBufferedReader() throws IOException {
		Mockito.when(br.readLine()).thenReturn(null);
		assertNotNull(factory.parseFile(br));
	}
	
	@Test
	public void testParseLevel() throws IOException {
		// bogus simulated "file".
		spy.parseLevel("5x5\nC00");
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
