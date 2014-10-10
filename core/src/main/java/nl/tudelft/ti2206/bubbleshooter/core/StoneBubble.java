package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

public class StoneBubble extends Bubble{
	private static final long serialVersionUID = 6207069265433663286L;
	
	public StoneBubble() {
		super(BubbleColors.GRAY.getColor());
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
