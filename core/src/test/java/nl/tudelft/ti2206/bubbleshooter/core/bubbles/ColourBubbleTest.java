package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.Color;

public class ColourBubbleTest {

	private ColourBubble colourbubble;
	private ColourBubble spy;
	private ArrayList<Color> colors;
	private Color color;
	
	@Before
	public void setUp() {
		colourbubble = new ColourBubble();
		spy = spy(colourbubble);
		color = mock(Color.class);
		colors = new ArrayList<Color>();
		colors.add(color);
		Mockito.when(spy.getRandomColor(1)).thenReturn(color);
		Mockito.when(spy.getRandomColor(colors)).thenReturn(color);
	}
	
	@Test
	public void testColourBubble() {
		assertNotNull(colourbubble.getBehaviour());
		assertNotNull(colourbubble.getColor());
		assertEquals(TextureID.BUBBLE, colourbubble.getTexture());
	}

	@Test
	public void testGetRandomColorInt() {
		assertEquals(color, spy.getRandomColor(1));
	}
	
	@Test
	public void testGetRandomColorArrayList() {
		assertEquals(color, spy.getRandomColor(colors));
	}
	
	@Test
	public void testColorBubbleColourLimit() {
		ColourBubble colorb = new ColourBubble(5);
		assertNotNull(colorb.getBehaviour());
		assertNotNull(colorb.getColor());
	}

	@Test
	public void testColorBubbleColourArrayList() {
		ColourBubble colorb = new ColourBubble(colors);
		assertNotNull(colorb.getBehaviour());
		assertNotNull(colorb.getColor());
	}
	
	@Test
	public void testColorBubbleColour() {
		ColourBubble colorb = new ColourBubble(color);
		assertNotNull(colorb.getBehaviour());
		assertNotNull(colorb.getColor());
	}
}
