package nl.tudelft.ti2206.bubbleshooter.core;


import com.badlogic.gdx.graphics.Color;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

public class StoneBubble extends Bubble{
	
	private int color;
	
	public StoneBubble() {
		this.color = Color.rgba8888(Color.WHITE);
	}
	
	/**
	 * Returns the texture of this {@link Bubble}.
	 * @return {@link #Texture} of this {@link Bubble}.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.STONEBUBBLE;
	}
	
	/**
	 * Return the color of this {@link Bubble}.
	 * @return color of this {@link Bubble}.
	 */
	@Override
	public Color getColor() {
		return new Color(color);
	}
}
