package nl.tudelft.ti2206.bubbleshooter;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Bubble {
	Vector2 direction;
	int velocity;
	
	public Projectile(Circle bounds, Vector2 direction, int velocity) {
		this.bounds = bounds;
		this.direction = direction;
		this.velocity = velocity;
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
}
