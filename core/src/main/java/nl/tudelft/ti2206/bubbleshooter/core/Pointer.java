package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

/**
 * The Pointer class contains a vector of the place where the cursor of the user was at the time.
 * Furthermore contains the normalized vector of the cursor.
 * @author group-15
 *
 */
public class Pointer implements Serializable {
	private static final long serialVersionUID = -6490893625035999503L;
	/**
	 * Vector intialization.
	 */
	Vector2 direction;
	Vector2 origin;

	/**
	 * Sets the origin and a given standard direction (up).
	 * @param origin	{@link Vector2} representing this {@link Pointer}'s origin
	 */
	public Pointer(Vector2 origin) {
		setOrigin(origin);
		setDirection(new Vector2(0, 1));
	}
	
	/**
	 * Instantiate a new vector with the cursor coordinates.
	 * @param coordinate	{@link Vector2} with cursor coordinates
	 */
	public void setOrigin(Vector2 coordinate) {
		origin = coordinate;
	}
	
	/**
	 * Set the direction of the vector.
	 * @param vector	{@link Vector2} representing a direction
	 */
	public void setDirection(Vector2 vector) {
		this.direction = vector.nor();
	}
	
	/**
	 * Retrieve the direction.
	 * @return direction
	 */
	public Vector2 getDirection() {
		return direction;
	}
	
	/**
	 * Retrieve the origin.
	 * @return origin
	 */
	public Vector2 getOrigin() {
		return origin;
	}
		
	/**
	 * Get the angle of the normal compared to the x-axis.
	 * @return angle of normal.
	 */
	public float getAngle() {
		return direction.angle() - 90;
	}
	
	/**
	 * Set the angle of the normal.
	 * A {@link Pointer} with angle 0 points up.
	 * @param degrees	angle in degrees
	 */
	public void setAngle(float degrees) {
		direction.setAngle(degrees + 90);
	}
		
	/**
	 * Simple string representation of a Pointer.
	 * Used for debugging purposes.
	 */
	public String toString() {
		return "Pointer(Direction: " + direction + ", Origin:" + origin + ")";
	}
}
