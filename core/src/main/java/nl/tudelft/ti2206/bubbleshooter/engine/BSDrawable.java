package nl.tudelft.ti2206.bubbleshooter.engine;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public abstract class BSDrawable {
	public abstract TextureID getTexture();
	
	public Vector2 getPosition() {
		return new Vector2(0, 0);
	}
	
	public Vector2 getOrigin() {
		return new Vector2(0, 0);
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	
	public Color getColor() {
		return Color.WHITE;
	}
	
	public float getRotation() {
		return 0;
	}
}
