package nl.tudelft.ti2206.bubbleshooter;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Bubble {
	Vector2 direction;
	int velocity;
	SoundEffect SFX;
	private final String SFXname = "BubbleSFX.wav";
	
	public Projectile(Circle bounds, Vector2 direction, int velocity) {
		this.bounds = bounds;
		this.direction = direction;
		this.velocity = velocity;
		SFX = new SoundEffect(SFXname);
		SFX.setVolume(0.5f);
	}
	
	public void move() {
		Vector2 dir = direction.nor().scl(velocity);
		bounds.x += dir.x;
		bounds.y += dir.y;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Sets the direction of the bubble.
	 * @param direction the direction for a 2D plane.
	 */
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	
	/**
	 * Gets the direction of the bubble.
	 * @return direction the direction of the bubble in  a 2D plane.
	 */
	public Vector2 getDirection() {
		return direction;
	}
	
	/**
	 * Get the SFX of the projectile.
	 * @return
	 */
	public SoundEffect getSFX() {
		return SFX;
	}
}
