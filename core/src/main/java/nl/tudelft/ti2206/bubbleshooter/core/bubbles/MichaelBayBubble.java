package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

/**
 * The {@link MichaelBayBubble} is a special {@link Bubble} that nukes the whole level.
 * It contains its own {@link BubbleBehaviour} and {@link TextureID}.
 */
public class MichaelBayBubble extends Bubble {
	private static final long serialVersionUID = -7975696762542618100L;

	/**
	 * MichaelBayBubble constructor.
	 */
	public MichaelBayBubble() {
		super(new MichaelBayBehaviour());
	}
	
	/**
	 * Returns the texture of this {@link Bubble}.
	 * @return {@link TextureID} of this {@link Bubble}.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.MICHAELBAYBUBBLE;
	}

}
