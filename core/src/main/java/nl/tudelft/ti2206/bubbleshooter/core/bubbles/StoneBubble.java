package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

/**
 * The {@link StoneBubble} is a special {@link Bubble} that is indestructable.
 * It contains its own {@link BubbleBehaviour} and {@link TextureID}.
 * @author group-15
 *
 */
public class StoneBubble extends Bubble{
	private static final long serialVersionUID = 6207069265433663286L;
	
	/**
	 * StoneBubble Constructor.
	 */
	public StoneBubble() {
		super(new StoneBehaviour());
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
