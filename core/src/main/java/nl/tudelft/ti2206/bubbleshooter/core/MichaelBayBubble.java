package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;

public class MichaelBayBubble extends Bubble {

	
	public MichaelBayBubble() {
		super.color = Color.rgba8888(Color.MAGENTA);
		super.behaviour = new MichaelBayBehaviour(this);
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
