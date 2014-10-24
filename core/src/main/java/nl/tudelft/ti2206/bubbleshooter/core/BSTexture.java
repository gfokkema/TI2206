package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;

public abstract class BSTexture extends Loggable {
	/**
	 * This method returns the {@link Color} of this {@link BSDrawable}.
	 * @return	{@link Color} of this {@link BSDrawable}
	 */
	public Color getColor() {
		return Color.WHITE;
	}
	
	/**
	 * This method returns the {@link TextureID} of the concrete object it represents.
	 * @return	{@link TextureID} representing a texture
	 */
	public abstract TextureID getTexture();
}
