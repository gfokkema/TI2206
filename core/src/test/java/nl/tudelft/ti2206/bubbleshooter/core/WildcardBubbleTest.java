package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.*;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

public class WildcardBubbleTest {

	private WildcardBubble WCbubble;
	/**
	 * A new bubble is initiated, to be used through the class.
	 */
	@Before
	public void setUp() {
		this.WCbubble = new WildcardBubble();
	}
	
	/**
	 * Test if the MichaelBay Bubble is correctly instantiated
	 */
	@Test
	public void testMichaelBayBubble() {
		assertEquals(Color.NAVY, WCbubble.getColor());
	}
	
	/**
	 * Test whether the defaults of a bubble are instantiated correctly.
	 */
	@Test
	public void testDefaults() {
		assertEquals(32, WCbubble.getWidth());
		assertEquals(32, WCbubble.getHeight());
		assertEquals(TextureID.STONEBUBBLE, WCbubble.getTexture());
	}


}
