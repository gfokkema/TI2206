package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
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
	int x;
	int y;
	
	/**
	 * Cannon constructor
	 * @param x
	 * @param y
	 */
	public Cannon(int x, int y, String img) {
		setPosition(x, y);
		image = new Texture(Gdx.files.internal(img));
	}
	
	/**
	 * Set the angle of the cannon (in other words the direction of which the cannon shoots in).
	 * @param degrees
	 */
	public void setAngle(float degrees) {
		pointer.setAngle(degrees);
	}
	
	/**
	 * Set position of the cannon itself on the screen.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
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
		batch.draw(image, this.x, this.y);
	}
	
}
