package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.math.Vector2;


public class Pointer {
	Vector2 cursor;
	Vector2 normal;

	public Pointer(Vector2 vector) {
		setCursor(vector);
	}
	
	public void setCursor(Vector2 vector) {
		cursor = vector;
		normalize(cursor);
	}
	
	public Vector2 getCursor() {
		return cursor;
	}
	
	public Vector2 getNormal() {
		return normal;
	}
	
	public float getAngle() {
		return normal.angle();
	}
	
	public void setAngle(float degrees) {
		normal.setAngle(degrees);
	}
	
	public void normalize(Vector2 vector) {
		normal = vector.nor();
	}
	
	public String toString() {
		return "Pointer{Cursor:" + cursor + " ,Normal:" + normal + "}";
	}
}
