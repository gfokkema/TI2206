package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

/**
 * The {@link BomBubble} is a special {@link Bubble} that acts like bomb.
 * It contains its own {@link BubbleBehaviour} and {@link TextureID}.
 */
public class BomBubble extends Bubble{
	private static final long serialVersionUID = -684963823755680038L;

	/**
	 * BomBubble Constructor
	 */
	public BomBubble() {
		super(new BomBehaviour());
	}
	
	/**
	 * Returns the texture of this {@link Bubble}.
	 * @return {@link TextureID} of this {@link Bubble}.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.BOMBUBBLE;
	}
}
