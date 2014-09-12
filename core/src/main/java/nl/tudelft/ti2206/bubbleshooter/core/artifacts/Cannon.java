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
	boolean fired;
	int counter = 0; //temp
	
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
		
		//temp
		counter++;
		
		if(counter == 100) {
			fired = false;
			counter = 0;
		}
		
	}
	
	public void draw(SpriteBatch batch) {
		update();
		sprite.draw(batch);
		batch.draw(fg, projectile.getBounds().x, projectile.getBounds().y, 32, 32);
	}
	
	public void handleInput() {
		if(Gdx.input.isKeyPressed(Keys.LEFT) && !fired) {
			angle += sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			
			// debugging...
			Gdx.app.log("Angle is", "" + angle); 
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && !fired) {
			angle -= sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
			
			// debugging...
			Gdx.app.log("Angle is", "" + angle); 
		}
				
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			fired = true;
		}		
	}
	
	private void update() {
		if(fired) {
			
			if (projectile.getCircle().x < 190 || projectile.getCircle().x > Gdx.graphics.getWidth() - 190 - fg.getWidth()/2) {
				projectile.getDirection().setAngle(180 -projectile.getDirection().angle());
			}
			
			shoot();
		}			
		
		else
			projectile.setPosition(new Vector2(pointer.getOrigin()).add(new Vector2(pointer.getDirection()).scl(100)));
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
