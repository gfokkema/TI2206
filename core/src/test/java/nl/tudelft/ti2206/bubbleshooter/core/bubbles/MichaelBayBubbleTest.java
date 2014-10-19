package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.MichaelBayBubble;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

public class MichaelBayBubbleTest {
	
	private MichaelBayBubble MBBubble;
	/**
	 * A new bubble is initiated, to be used through the class.
	 *
	 */
	@Before
	public void setUp() {
		this.MBBubble = new MichaelBayBubble();
	}
	
	/**
	 * Test if the MichaelBay Bubble is correctly instantiated
	 *
	 */
	@Test
	public void testMichaelBayBubble() {
		assertEquals(Color.WHITE, MBBubble.getColor());
	}
	
	/**
	 * Test whether the defaults of a bubble are instantiated correctly.
	 *
	 */
	@Test
	public void testDefaults() {
		assertEquals(32, MBBubble.getWidth());
		assertEquals(32, MBBubble.getHeight());
		assertEquals(TextureID.MICHAELBAYBUBBLE, MBBubble.getTexture());
	}
	
}
