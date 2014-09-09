package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.math.Vector2;

/**
 * The Pointer class contains a vector of the place where the cursor of the user was at the time.
 * Furthermore contains the normalized vector of the cursor.
 * @author Owen
 *
 */
public class Pointer {
	
	/**
	 * Vector intialization.
	 */
	Vector2 direction;
	Vector2 origin;

	/**
	 * Constructor for pointer
	 */
	public Pointer(Vector2 origin) {
		setOrigin(origin);
		setDirection(new Vector2(0,1));
	}
	
	/**
	 * Instantiate a new vector with the cursor coordinates.
	 * @param x
	 * @param y
	 */
	public void setOrigin(Vector2 coordinate) {
		origin = coordinate;
	}
	
	public void setDirection(Vector2 vector) {
		this.direction = vector.nor();
	}
	
	public void setPointDirection(Vector2 coordinate) {
		Vector2 direction = coordinate.sub(origin);
		setDirection(direction);
	}
	
	public Vector2 getCoordinateDirection() {
		return origin.add(direction);
	}
	
	public Vector2 getDirection() {
		return direction;
	}
	
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
	 * @param degrees
	 */
	public void setAngle(float degrees) {
		direction.setAngle(degrees);
	}
		
	/**
	 * Simple string representation of a Pointer.
	 */
	public String toString() {
		return "Pointer{Direction:" + direction + " ,Origin:" + origin + "}";
	}
}
