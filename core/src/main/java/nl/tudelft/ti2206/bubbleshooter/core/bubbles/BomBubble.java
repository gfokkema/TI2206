package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

public class BomBubble extends Bubble{
	private static final long serialVersionUID = -684963823755680038L;

	public BomBubble() {
		super(BubbleType.BOMBUBBLE, new BomBehaviour());
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
