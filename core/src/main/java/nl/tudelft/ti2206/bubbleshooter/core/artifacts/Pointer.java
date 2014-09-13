package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.math.Vector2;

/**
 * The Pointer class contains a vector of the place where the cursor of the user was at the time.
 * Furthermore contains the normalized vector of the cursor.
 * @author group-15
 *
 */
public class Pointer {
	
	/**
	 * Vector intialization.
	 */
	Vector2 direction;
	Vector2 origin;

	/**
	 * Constructor for pointer.
	 * Sets the origin and a given standard direction (up).
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
	 * @param vector
	 */
	public void setDirection(Vector2 vector) {
		this.direction = vector.nor();
	}
	
	/**
	 * Set the direction of a vector (a point)
	 * Calls {@link #setDirection}.
	 * @param coordinate
	 */
	public void setPointDirection(Vector2 coordinate) {
		Vector2 direction = coordinate.sub(origin);
		setDirection(direction);
	}
	
	/**
	 * Get the original coordinate from the direction.
	 * @return original point
	 */
	public Vector2 getCoordinateDirection() {
		return getOrigin().add(getDirection());
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
		return direction.angle();
	}
	
	/**
	 * Set the angle of the normal.
	 * pointer with angle 0 points up
	 * @param degrees
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
