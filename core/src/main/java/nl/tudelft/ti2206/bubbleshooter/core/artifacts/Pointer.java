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
	Vector2 cursor;
	Vector2 normal;

	/**
	 * Constructor for pointer
	 * @param x
	 * @param y
	 */
	public Pointer(int x, int y) {
		setCursor(x, y);
	}
	
	/**
	 * Instantiate a new vector with the cursor coordinates.
	 * @param x
	 * @param y
	 */
	public void setCursor(int x, int y) {
		cursor = new Vector2(x, y);
		normalize(cursor);
	}
	
	/**
	 * Get cursor vector.
	 * @return cursor
	 */
	public Vector2 getCursor() {
		return cursor;
	}
	
	/**
	 * Get normal of the cursor vector.
	 * @return normal
	 */
	public Vector2 getNormal() {
		return normal;
	}
	
	/**
	 * Get the angle of the normal compared to the x-axis.
	 * @return angle of normal.
	 */
	public float getAngle() {
		return normal.angle();
	}
	
	/**
	 * Set the angle of the normal.
	 * @param degrees
	 */
	public void setAngle(float degrees) {
		normal.setAngle(degrees);
	}
	
	/**
	 * Normalize the vector
	 * @param vector
	 */
	public void normalize(Vector2 vector) {
		normal = vector.nor();
	}
	
	/**
	 * Simple string representation of a Pointer.
	 */
	public String toString() {
		return "Pointer{Cursor:" + cursor + " ,Normal:" + normal + "}";
	}
}
