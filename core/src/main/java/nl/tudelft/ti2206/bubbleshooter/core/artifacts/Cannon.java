package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import nl.tudelft.ti2206.bubbleshooter.Bubble;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
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
	Bubble projectile;
	Pointer pointer;
	Texture image;
	Sprite sprite;
	float angle;
	
	Texture fg = new Texture("Bubble-Blue.png");
	Sprite bubbleSprite;
	
	private final float LEFT_BOUNDARY = 40;
	private final float RIGHT_BOUNDARY = -40;
	private final int sensitivity = 100;
	private final int velocity = 5;
	
	
	/**
	 * Cannon constructor
	 * @param x coordinate
	 * @param y coordinate
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
		projectile = new Bubble();
		projectile.setCircle(0, 0, fg.getHeight()/4);
		pointer.setOrigin(new Vector2(x-fg.getWidth()/4, y));
		projectile.setDirection(pointer.getDirection());
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
		projectile.setDirection(pointer.getDirection());
		
		// debugging...
		Gdx.app.log("Degrees is", "" + angle);
	}
		
		
	/**
	 * Shoot the actual bubble: pew pew!
	 */
	public void shoot() {
		
		Circle BCPosition = projectile.getCircle();
		
		BCPosition.x += projectile.getDirection().x *velocity;
		BCPosition.y += projectile.getDirection().y *velocity;
		
		projectile.setCircle(BCPosition.x, BCPosition.y, fg.getHeight()/4);
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
		projectile.setPosition(pointer.getOrigin().add(pointer.getDirection().scl(100)));

		// draw the cannon
		sprite.draw(batch);
	}
	
	/**
	 * Draw the bubble which the cannon shoots
	 * @param batch
	 */
	public void drawBubble(SpriteBatch batch){
		// follow cannon angle
		projectile.setCircle(projectile.getPosition(), fg.getHeight()/4);
		// bubble stays within given bounds.
		
		Circle pCircle = projectile.getCircle();
		
		if(pCircle.x < 190) pCircle.x = 190;
		if(pCircle.x > Gdx.graphics.getWidth() - 190 - fg.getWidth()/2) pCircle.x = Gdx.graphics.getWidth() - 190 - fg.getWidth()/2;
		if(pCircle.y > Gdx.graphics.getHeight() - fg.getHeight()/2) pCircle.y = Gdx.graphics.getHeight() - fg.getHeight()/2;
		batch.draw(fg, pCircle.x, pCircle.y, 32, 32);	
		sprite.draw(batch);
	}
	
	/**
	 * Get the associated pointer with the cannon.
	 * @return pointer
	 */
	public Pointer getPointer() {
		return pointer;
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
	public Vector2 getProjectilePosition() {
		return projectile.getPosition();
	}
}
