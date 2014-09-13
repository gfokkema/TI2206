package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import nl.tudelft.ti2206.bubbleshooter.Projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Create a cannon which can shoot bubbles!
 * @author Nando &amp; Owen
 *
 */
public class Cannon extends Sprite {
	Pointer pointer;
	Projectile projectile;
	
	private final float LEFT_BOUNDARY = 60;
	private final float RIGHT_BOUNDARY = -60;
	private final int sensitivity = 100;
	private final int velocity = 5;
	
	/**
	 * Cannon constructor
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Cannon(Texture tex, int x, int y) {
		super(new Sprite(tex));
		this.setOrigin(this.getWidth() / 2, 25);
		this.setPosition(x - this.getWidth() / 2, y);
		
		pointer = new Pointer(new Vector2(x, y));
		pointer.setOrigin(new Vector2(x - 16, y));
		
		// add bubble
		projectile = new Projectile(new Circle(getBubblePos(), 16), pointer.direction, 0);
		setAngle(0);
	}
	
	public Cannon(int x, int y) {
		this(new Texture("cannon.png"), x, y);
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
			degrees = LEFT_BOUNDARY;
		}
		
		if(degrees < RIGHT_BOUNDARY) {
			degrees = RIGHT_BOUNDARY;
		}
		
		// rotate the actual rotation difference.
		this.setRotation(degrees);
		pointer.setAngle(degrees);
		
		projectile.setBounds(new Circle(getBubblePos(), 16));
	}
	
	public Projectile getProjectile() {
		return this.projectile;
	}
		
		
	/**
	 * Shoot the actual bubble: pew pew!
	 */
	public Projectile shoot() {
		Projectile fired = projectile;
		fired.setVelocity(velocity);
		fired.setDirection(new Vector2(pointer.direction));
		
		projectile = new Projectile(new Circle(getBubblePos(), 16), pointer.direction, 0);
		return fired;
	}
	
	/**
	 * Controls handleInput
	 */
	public void handleInput() {
		//if pressed left, turn cannon to the left
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			setAngle(this.getRotation() + sensitivity*Gdx.graphics.getDeltaTime());
		}
		
		//if pressed right, turn cannon to the right
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) { 
			setAngle(this.getRotation() - sensitivity*Gdx.graphics.getDeltaTime());
		}
	}
	
	/**
	 * Get the associated pointer with the cannon.
	 * @return pointer
	 */
	public Pointer getPointer() {
		return pointer;
	}
	
	private Vector2 getBubblePos() {
		return new Vector2(pointer.origin.x + 16, pointer.origin.y + 16)
						.add(pointer.getDirection().nor().scl(100));
	}
}
