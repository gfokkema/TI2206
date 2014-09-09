package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Create a cannon which can shoot bubbles!
 * @author Nando &amp; Owen
 *
 */
public class Cannon {

	/**
	 * Create a pointer and the texture for the cannon.
	 */
	Pointer pointer;
	Texture image;
	Sprite sprite;
	float angle;
	private final float LEFT_BOUNDARY = 40;
	private final float RIGHT_BOUNDARY = -40;
	
	
	/**
	 * Cannon constructor
	 * @param x
	 * @param y
	 */
	public Cannon(int x, int y, String img) {
		pointer = new Pointer(new Vector2(x, y));
		image = new Texture(img);
		sprite = new Sprite(image);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		angle = 0;
		setSpritePosition();
	}
	
	/**
	 * Set the angle of the cannon (in other words the direction of which the cannon shoots in).
	 * @param degrees the angle in amount of degrees
	 */
	public void setAngle(float degrees) {
		/*
		 *  Compute difference between previous and current rotation,
		 *  since rotate from the Sprite class rotates relatively from the current rotation.
		 */
		if(degrees > LEFT_BOUNDARY) angle = LEFT_BOUNDARY;
		if(degrees < RIGHT_BOUNDARY) angle = RIGHT_BOUNDARY;
		sprite.rotate(sprite.getRotation() - angle);
		sprite.setRotation(angle);
		pointer.setAngle(angle);
		Gdx.app.log("Degrees is", "" + angle);
	}
	
	public void setSpritePosition() {
		sprite.setPosition(pointer.getCoordinateDirection().x, pointer.getCoordinateDirection().y);
	}
	
	/**
	 * Get the associated pointer with the cannon.
	 * @return pointer
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
	 * Additionally check if angle of the cannon changed.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		update();
		sprite.draw(batch);
	}
	
	/**
	 * Get the cannon angle
	 * @return angle
	 */
	public float getAngle() {
		return angle;
	}
	
	/**
	 * Updates the angle every time left or right arrow keys are pressed.
	 */
	public void update() {
		// check for left/right key presses
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			angle += 300*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			Gdx.app.log("Angle is", "" + angle); 
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			angle -= 300*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			Gdx.app.log("Angle is", "" + angle); 
		}
	}
	
}
