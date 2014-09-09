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
	public Cannon(int x, int y) {
		pointer = new Pointer(new Vector2(x, y));
		
		image = new Texture("testCannon.png");
		
		sprite = new Sprite(image);
		sprite.setOrigin(sprite.getWidth()/2, 0);
		sprite.setPosition(pointer.getOrigin().x, pointer.getOrigin().y);
		
		angle = 0;
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
		if(degrees > LEFT_BOUNDARY) 
			angle = LEFT_BOUNDARY;
		
		if(degrees < RIGHT_BOUNDARY) 
			angle = RIGHT_BOUNDARY;
		
		sprite.rotate(sprite.getRotation() - angle);
		sprite.setRotation(angle);
		pointer.setAngle(angle);
		
		Gdx.app.log("Degrees is", "" + angle);
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
	 * Get the cannon angle
	 * @return angle
	 */
	public float getAngle() {
		return angle;
	}
	
	/**
	 * per frame checks if angle of the cannon changed.
	 * at the end draws the actual cannon onto screen.
	 * @param batch
	 */
	public void update(SpriteBatch batch) {
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
		sprite.draw(batch);
	}
	
}
