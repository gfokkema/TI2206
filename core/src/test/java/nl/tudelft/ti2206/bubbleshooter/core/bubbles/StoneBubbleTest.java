package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.StoneBubble;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

public class StoneBubbleTest {

	private StoneBubble StoneBubble;
	/**
	 * A new bubble is initiated, to be used through the class.
	 *
	 */
	@Before
	public void setUp() {
		this.StoneBubble = new StoneBubble();
	}
	
	/**
	 * Test if the MichaelBay Bubble is correctly instantiated
	 *
	 */
	@Test
	public void testStoneBubble() {
		assertEquals(Color.WHITE, StoneBubble.getColor());
	}
	
	/**
	 * Test whether the defaults of a bubble are instantiated correctly.
	 *
	 */
	@Test
	public void testDefaults() {
		assertEquals(32, StoneBubble.getWidth());
		assertEquals(32, StoneBubble.getHeight());
		assertEquals(TextureID.STONEBUBBLE, StoneBubble.getTexture());
	}
}
