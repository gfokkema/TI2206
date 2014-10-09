package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;

public class BomBubble extends Bubble{
	private static final long serialVersionUID = -684963823755680038L;

	public BomBubble() {
		super.color = Color.rgba8888(Color.CYAN);
		super.behaviour = new BomBehaviour(this);
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
