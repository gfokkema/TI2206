package nl.tudelft.ti2206.bubbleshooter.util;

import com.badlogic.gdx.math.Vector2;

public interface BSModeObserver {
	
	/**
	 * Score notifier
	 * @param score
	 */
	public void notifyScore(int score);

	/**
	 * Cannon angle notifier
	 * @param angle
	 */
	public void notifyCannonAngle(float angle);
	
	/**
	 * Projectile position notifier
	 * @param position
	 */
	public void notifyProjectilePosition(Vector2 position);

}
