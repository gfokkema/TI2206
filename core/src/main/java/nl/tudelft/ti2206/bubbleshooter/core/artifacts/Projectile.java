package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import nl.tudelft.ti2206.bubbleshooter.Bubble;

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
		bounds.x += direction.x * velocity;
		bounds.y += direction.y * velocity;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Gets the position of the bubble.
	 * @return vector2 with c and y coordinate.
	 */
	public Vector2 getPosition() {
		return new Vector2(bounds.x, bounds.y);
	}
	
	/**
	 * Sets the position of the bubble.
	 * radius stays the same.
	 * @param position vector with x and y coordinate.
	 */
	public void setPosition(Vector2 position) {
		bounds.set(position, bounds.radius);
	}
	
	/**
	 * Sets the direction of the bubble.
	 * @param direction the direction for a 2D plane.
	 */
	public void setDirection(Vector2 direction) {
		this.direction = new Vector2(direction);
	}
	
	/**
	 * Gets the direction of the bubble.
	 * @return direction the direction of the bubble in  a 2D plane.
	 */
	public Vector2 getDirection() {
		return direction;
	}
}
