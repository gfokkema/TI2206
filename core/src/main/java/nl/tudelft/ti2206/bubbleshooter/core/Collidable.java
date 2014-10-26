package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;

/**
 * Objects implementing this interface can collide with a moving {@link Projectile}.
 */
public interface Collidable {
	/**
	 * Checks whether the implementing object collides with the specified {@link Projectile}.
	 * @param p	the specified {@link Projectile}
	 * @return	true if colliding, false otherwise
	 */
	public boolean collides(Projectile p);
}
