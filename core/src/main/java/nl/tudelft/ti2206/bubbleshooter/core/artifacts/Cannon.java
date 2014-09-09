package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Create a cannon which can shoot bubbles!
 * @author Nando
 *
 */
public class Cannon {

	/**
	 * Create a pointer and the texture for the cannon.
	 */
	Pointer pointer;
	Texture image;
	Sprite sprite;
	
	/**
	 * Cannon constructor
	 * @param x
	 * @param y
	 */
	public Cannon(int x, int y, String img) {
		pointer = new Pointer(new Vector2(x,y));
		image = new Texture(img);
		sprite = new Sprite(image);
	}
	
	/**
	 * Set the angle of the cannon (in other words the direction of which the cannon shoots in).
	 * @param degrees
	 */
	public void setAngle(float degrees) {
		//TODO something to set the angle correct...
		pointer.setAngle(degrees);
		sprite.rotate(degrees);
	}
	
	public void setSpritePosition() {
		sprite.setPosition(pointer.getCoordinateDirection().x, pointer.getCoordinateDirection().y);
	}
	
	/**
	 * Get the associated pointer with the cannon.
	 * @return
	 */
	public Pointer getPointer() {
		return pointer;
	}
	
	/**
	 * Shoot the actual bubble: pew pew!
	 */
	public void shoot() {
		//TODO more pew pew!
	}
	
	/**
	 * Handle random bubble spawning (different bubble colors)?
	 */
	public void spawnBubble() {
		//TODO what?
	}
	
	/**
	 * Draw the actual cannon onto screen.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		setSpritePosition();
		sprite.draw(batch);
	}
	
	public float getAngle() {
		return pointer.getAngle();
	}
	
}
