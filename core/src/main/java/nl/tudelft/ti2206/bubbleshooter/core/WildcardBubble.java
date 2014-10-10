package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;

public class WildcardBubble extends Bubble{
	private static final long serialVersionUID = -2817453609032842103L;
	
	public WildcardBubble() {
		super(Color.WHITE);
		// super(Color.WHITE, new WildCardBehaviour(this);
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
