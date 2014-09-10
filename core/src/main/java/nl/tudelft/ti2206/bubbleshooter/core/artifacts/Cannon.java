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
	
	Texture bubbleImage;
	Sprite bubbleSprite;
	
	private final float LEFT_BOUNDARY = 40;
	private final float RIGHT_BOUNDARY = -40;
	private final int sensitivity = 100;
	
	
	/**
	 * Cannon constructor
	 * @param x
	 * @param y
	 */
	public Cannon(int x, int y) {
		pointer = new Pointer(new Vector2(x, y));
				
		// texture for cannon + angle
		image = new Texture("cannon.png");
		angle = 0;
		
		// sprite settings
		sprite = new Sprite(image);
		sprite.setOrigin(sprite.getWidth()/2, 25);
		sprite.setPosition(x - image.getWidth()/2, y);
		
		// add bubble
		Vector2 temp = pointer.getOrigin().add(pointer.getDirection().scl(100));
				
		bubbleImage = new Texture("Bubble-Blue.png");
		bubbleSprite = new Sprite(bubbleImage);
		bubbleSprite.setOrigin(bubbleImage.getWidth()/2, bubbleImage.getHeight()/2);
		bubbleSprite.setPosition(temp.x, temp.y);
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
		if(degrees > LEFT_BOUNDARY) {
			angle = LEFT_BOUNDARY;
		}
		
		if(degrees < RIGHT_BOUNDARY) {
			angle = RIGHT_BOUNDARY;
		}
		
		// rotate the actual rotation difference.
		sprite.rotate(sprite.getRotation() - angle);
		sprite.setRotation(angle);
		pointer.setAngle(angle);
		
		// debugging...
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
	 * Per frame checks if angle of the cannon changed,
	 * at the end draws the actual cannon onto screen.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// check for left/right key presses
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			angle += sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			
			// debugging...
			Gdx.app.log("Angle is", "" + angle); 
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			angle -= sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			
			// debugging...
			Gdx.app.log("Angle is", "" + angle); 
		}
		
		Vector2 temp = pointer.getOrigin().add(pointer.getDirection().scl(100));
		
		Gdx.app.log("temp is",temp.toString()); 
		
		bubbleSprite.setPosition(temp.x, temp.y);
		bubbleSprite.draw(batch);
		
		// draw the cannon
		sprite.draw(batch);
	}
}
