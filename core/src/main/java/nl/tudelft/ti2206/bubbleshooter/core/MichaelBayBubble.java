package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

public class MichaelBayBubble extends Bubble {
	private static final long serialVersionUID = -7975696762542618100L;

	public MichaelBayBubble() {
		super(BubbleColors.MAGENTA.getColor(), new MichaelBayBehaviour());
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
