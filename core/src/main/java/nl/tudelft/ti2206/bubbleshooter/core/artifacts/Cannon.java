package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import nl.tudelft.ti2206.bubbleshooter.Bubble;
import nl.tudelft.ti2206.bubbleshooter.Projectile;

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
	Sprite sprite;
	Pointer pointer;
	float angle;

	Projectile projectile;
	boolean moving = false;
	
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
		Texture image = new Texture("cannon.png");
		
		// sprite settings
		sprite = new Sprite(image);
		sprite.setOrigin(sprite.getWidth() / 2, 25);
		sprite.setPosition(x - image.getWidth() / 2, y);
		pointer = new Pointer(new Vector2(x, y));
		pointer.setOrigin(new Vector2(x - 16, y));
		
		// add bubble
		Vector2 origin = new Vector2(pointer.origin.x + 16, pointer.origin.y + 16)
						.add(pointer.getDirection().scl(100));
		projectile = new Projectile(new Circle(origin, 16), pointer.direction, 0);
		setAngle(0);
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
		
		projectile.setBounds(new Circle(getBubblePos(), 16));
			
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
	public Projectile shoot() {
		Projectile fired = projectile;
		projectile = new Projectile(new Circle(getBubblePos(), 16), pointer.direction, 0);
		
		fired.setVelocity(velocity);
		return fired;
	}
		
	/**
	 * Get the cannon angle
	 * @return angle
	 */
	public float getAngle() {
		return angle;
	}
	
	public Bubble getBubble() {
		return this.projectile;
	}
	
	/**
	 * Draw the bubble which the cannon shoots
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		Circle c = projectile.getBounds();
		// bubble stays within given bounds.
		if(c.x < 190 + 16) c.x = 190 + 16;
		if(c.x > Gdx.graphics.getWidth() - 190 - 16) c.x = Gdx.graphics.getWidth() - 190 - 16;
		if(c.y > Gdx.graphics.getHeight() - 16) c.y = Gdx.graphics.getHeight() - 16;
		// check for left/right key presses
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			angle += sensitivity * Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			
			// debugging...
			Gdx.app.log("Angle is", "" + angle); 
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			angle -= sensitivity * Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			
			// debugging...
			Gdx.app.log("Angle is", "" + angle); 
		}
		sprite.draw(batch);
	}
	
	private Vector2 getBubblePos() {
		return new Vector2(pointer.origin.x + 16, pointer.origin.y + 16)
						.add(pointer.getDirection().scl(100));
	}
}
