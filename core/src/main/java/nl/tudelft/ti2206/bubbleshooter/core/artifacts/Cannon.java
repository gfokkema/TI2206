package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
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
	Vector2 BCPosition;
	
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
		BCPosition = new Vector2(0,0);
		bubbleImage = new Texture("Bubble-Blue.png");
//		bubbleSprite = new Sprite(bubbleImage);
//		bubbleSprite.setPosition(temp.x, temp.y);
		pointer.setOrigin(new Vector2(x-bubbleImage.getWidth()/4, y));
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
		//Gdx.app.log("Before", BCPosition.toString());
		//Gdx.app.log("Pointer dir", pointer.getDirection().toString());
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
			for(int i = 0; i < 20; i++){
				BCPosition.x += pointer.getDirection().x * Gdx.graphics.getDeltaTime() * 3000;
				BCPosition.y += pointer.getDirection().y * Gdx.graphics.getDeltaTime() * 3000;
			}
		}
		//Gdx.app.log("After", BCPosition.toString());
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
	 * Get the position of where the bubble should be relatively to the cannon.
	 * @return BCPosition
	 */
	public Vector2 getBCPosition() {
		return BCPosition;
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
		
		//Gdx.app.log("Bubble Cannon Position is", "" + BCPosition); 
		BCPosition = pointer.getOrigin().add(pointer.getDirection().scl(100));
		
		// draw the cannon
		sprite.draw(batch);
	}
}
