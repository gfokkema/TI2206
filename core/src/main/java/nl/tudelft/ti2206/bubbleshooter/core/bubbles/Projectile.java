package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class represent a {@link Bubble} that has been shot. Since this
 * {@link Bubble} has been shot, it has a velocity and a direction. It will also
 * move across the board, untill it collides with a normal {@link Bubble}
 * 
 * @author group-15
 *
 */
public class Projectile extends ColourBubble {
	private static final long serialVersionUID = -4014441150246221939L;
	private Vector2 direction;
	private int velocity;

	/**
	 * Create a {@link Projectile} with the specified {@link Circle} bounding
	 * area, the {@link Vector2} direction and with a velocity.
	 * 
	 * @param bounds
	 *            the {@link Circle} representing the location and bounding area
	 * @param direction
	 *            the {@link Vector2} representing the direction this bubble
	 *            moves in
	 * @param velocity
	 *            the speed of this {@link Bubble}
	 */
	public Projectile(Circle bounds, Vector2 direction, int velocity) {
		//only temporarily
		super(5);
		
		this.setBounds(bounds);
		this.direction = direction;
		this.velocity = velocity;
	}
	
	public Projectile(ArrayList<Color> colour, Circle bounds, Vector2 direction, int velocity) {
		//only temporarily
		super(colour);
		
		this.setBounds(bounds);
		this.direction = direction;
		this.velocity = velocity;
	}
	
	/**
	 * Moves the {@link Projectile} along it's trajectory specified by
	 * direction.
	 */
	public void move() {
		Vector2 dir = direction.nor().scl(velocity);
		this.getBounds().x += dir.x;
		this.getBounds().y += dir.y;
		
		setChanged();
		notifyObservers("New position: " + this.getBounds().x + "," + this.getBounds().y);
	}

	/**
	 * Returns an integer representing the current velocity of this
	 * {@link Projectile}.
	 * 
	 * @return an integer representing this {@link Projectile}'s velocity
	 */
	public int getVelocity() {
		return this.velocity;
	}

	/**
	 * Sets the current velocity of this {@link Projectile}
	 * 
	 * @param velocity
	 *            an integer representing this {@link Projectile}'s velocity
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	/**
	 * Sets the direction of the bubble.
	 * 
	 * @param direction
	 *            the direction for a 2D plane.
	 */
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	/**
	 * Gets the direction of the bubble.
	 * 
	 * @return direction the direction of the bubble in a 2D plane.
	 */
	public Vector2 getDirection() {
		return direction;
	}
}
