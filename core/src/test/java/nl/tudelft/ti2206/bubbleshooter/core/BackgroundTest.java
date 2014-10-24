package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Test;

/**
 * In this class we will test the functionality of our {@link Background} class.
 */
public class BackgroundTest {
	/**
	 * Test whether the defaults of a {@link Background} are instantiated correctly.
	 */
	@Test
	public void testDefaults() {
		Background bg = new Background();
		assertEquals(640, bg.getWidth());
		assertEquals(480, bg.getHeight());
		assertEquals(TextureID.GAMEBACKGROUND, bg.getTexture());
	}
}
