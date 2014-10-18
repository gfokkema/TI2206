package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;

public interface Collidable {
	public boolean collides(Projectile p);
}
