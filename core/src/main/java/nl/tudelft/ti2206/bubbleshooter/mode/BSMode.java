package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface BSMode {
	public void update(float deltaTime);
	public Collection<BSDrawable> getDrawables();
}
