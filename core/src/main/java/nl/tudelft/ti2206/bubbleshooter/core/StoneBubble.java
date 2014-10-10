package nl.tudelft.ti2206.bubbleshooter.core;


import com.badlogic.gdx.graphics.Color;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

public class StoneBubble extends Bubble{
	private static final long serialVersionUID = 6207069265433663286L;

	
	public StoneBubble() {
		super.color = Color.rgba8888(BubbleColors.GRAY.getColor());
		super.behaviour = null;
	}
	
	/**
	 * Returns the texture of this {@link Bubble}.
	 * @return {@link #Texture} of this {@link Bubble}.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.STONEBUBBLE;
	}
}
