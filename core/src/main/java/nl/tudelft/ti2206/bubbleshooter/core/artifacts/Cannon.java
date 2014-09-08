package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

/**
 * Create a cannon to shoot bubbles
 * @author Nando
 *
 */
public class Cannon {

	Pointer pointer;
	Vector2 cursor;
	Texture image;
	
	public Cannon(Vector2 vector) {
		cursor = vector;
		pointer = new Pointer(vector);
	}
	
	public void SetAngle() {
		
	}
	
	public void setPosition() {
		
	}
	
	public Pointer getPointer() {
		return pointer;
	}
	
	public void shoot() {
		
	}
	
	public void spawnBubble() {
		
	}
	
}
