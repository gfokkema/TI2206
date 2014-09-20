package nl.tudelft.ti2206.bubbleshooter.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import nl.tudelft.ti2206.bubbleshooter.Launch;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

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
	
	public void draw(Launch game) {
		Vector2 position = getPosition();
		Vector2 origin = getOrigin();
		
		game.batch.setColor(getColor());
		game.batch.draw(new TextureRegion(game.assets.get(getTexture())),
						position.x, position.y,
						origin.x, origin.y,
						getWidth(), getHeight(),
						1, 1,
						getRotation());
	}
}
