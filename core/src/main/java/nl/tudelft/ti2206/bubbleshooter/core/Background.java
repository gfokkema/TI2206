package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;;

/**
 * This class holds the properties of the background to use for the game.
 */
public class Background extends BSDrawable {
	
	/**
	 * Returns the Texture of the background.
	 * @return the {@link #Texture} of the background.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.BACKGROUND;
	}

	/**
	 * Returns the width of the background.
	 * @return the width of the background.
	 */
	@Override
	public int getWidth() {
		return 640;
	}

	/**
	 * Returns the height of the background.
	 * @return the height of the background.
	 */
	@Override
	public int getHeight() {
		return 480;
	}
}
