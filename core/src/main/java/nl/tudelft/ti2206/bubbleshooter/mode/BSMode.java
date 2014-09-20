package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

public interface BSMode {
	public void update(float deltaTime);
	public Collection<BSDrawable> getDrawables();
}
