package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;;

/**
 * This class holds the properties of the background to use for the game.
 */
public class Background extends BSDrawable {
	@Override
	public TextureID getTexture() {
		return TextureID.BACKGROUND;
	}

	@Override
	public int getWidth() {
		return 640;
	}

	@Override
	public int getHeight() {
		return 480;
	}

}
