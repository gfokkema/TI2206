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

	Pointer pointer;
	
	Texture image;
	Sprite sprite;
	float angle;
	
	Bubble projectile;
	Texture fg;
	Sprite bubbleSprite;
	
	boolean fired;
	
	private final float LEFT_BOUNDARY = 60;
	private final float RIGHT_BOUNDARY = -60;
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
		fg = new Texture("Bubble-Blue.png");
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
	}
		
		
	/**
	 * Shoot the actual bubble: pew pew!
	 */
	public void shoot() {
		
		Circle BCPosition = projectile.getCircle();
		
		BCPosition.x += projectile.getDirection().x *velocity;
		BCPosition.y += projectile.getDirection().y *velocity;
		
		projectile.setCircle(BCPosition.x, BCPosition.y, fg.getHeight()/4);
		
		// projectile out of screen
		if(projectile.getCircle().y > Gdx.graphics.getHeight()) {
			fired = false;
		}
		
	}
	
	/**
	 * draw attributes
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		update();
		sprite.draw(batch);
		batch.draw(fg, projectile.getBounds().x, projectile.getBounds().y, 32, 32);
	}
	
	/**
	 * Controls handleInput
	 */
	public void handleInput() {
		//if pressed left, turn cannon to the left
		if(Gdx.input.isKeyPressed(Keys.LEFT) && !fired) {
			angle += sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
		}
		
		//if pressed right, turn cannon to the right
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && !fired) {
			angle -= sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle); 
		}
		
		//if pressed space, trigger shoot
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			fired = true;
		}		
	}
	
	/**
	 * Update attributes which are able to move
	 */
	private void update() {		
		
		// if fired, check if the projectile hits the wall
		// perform shoot
		if(fired) {
			if(projectile.getCircle().x < 190 || projectile.getCircle().x > Gdx.graphics.getWidth() - 190 - fg.getWidth()/2)
				projectile.getDirection().setAngle(180 -projectile.getDirection().angle());
			shoot();
		}
		// else draw projectile on cannon
		else {
			projectile.setPosition(new Vector2(pointer.getOrigin()).add(new Vector2(pointer.getDirection()).scl(100)));
			projectile.setDirection(pointer.getDirection());
		}
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
