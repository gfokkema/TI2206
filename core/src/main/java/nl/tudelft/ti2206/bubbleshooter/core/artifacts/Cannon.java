package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import nl.tudelft.ti2206.bubbleshooter.Projectile;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Create a cannon which can shoot bubbles!
 * This class also handles the input necessary to shoot and rotate the cannon.
 * @author group-15
 *
 */
public class Cannon extends Sprite {
	
	/**
	 * Variable initialization.
	 */
	Pointer pointer;
	float volume;
	Projectile projectile;
	
	private final float LEFT_BOUNDARY = 60;
	private final float RIGHT_BOUNDARY = -60;
	private final int sensitivity = 100;
	private final int velocity = 5;
	
	/**
	 * Constructs a {@link Cannon} using a specified {@link Texture}
	 * @param tex		{@link Texture} to use
	 * @param settings	{@link SoundAsset} to use
	 * @param x			coordinate
	 * @param y 		coordinate
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
	
	/**
	 * Constructs a {@link Cannon} using the predefined {@link Texture}
	 * @param x		coordinate
	 * @param y 	coordinate
	 */
	public Cannon(int x, int y) {
		this(new Texture("cannon.png"), x, y);
	}
	
	/**
	 * Set the angle of the cannon (in other words the direction of which the cannon shoots in).
	 * @param degrees the angle in amount of degrees.
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
	
	/**
	 * Returns the {@link Projectile} this {@link Cannon} is currently holding.
	 * @return	{@link Projectile} of this {@link Cannon}
	 */
	public Projectile getProjectile() {
		return this.projectile;
	}
		
		
	/**
	 * Shoot the actual bubble: pew pew!
	 * @return	{@link Projectile} that has been shot
	 */
	public Projectile shoot() {
		Projectile fired = projectile;
		fired.setVelocity(velocity);
		fired.setDirection(new Vector2(pointer.direction));
		
		projectile = new Projectile(new Circle(getBubblePos(), 16), pointer.direction, 0);
		return fired;
	}
	
	/**
	 * Moves the cannon to the left
	 * @param dt	the amount to move
	 */
	public void left(float dt) {
		setAngle(this.getRotation() + sensitivity * dt);
	}
	
	/**
	 * Moves the cannon to the right
	 * @param dt	the amount to move
	 */
	public void right(float dt) { 
		setAngle(this.getRotation() - sensitivity * dt);
	}
	
	/**
	 * Get the associated pointer with the cannon.
	 * @return pointer
	 */
	public Pointer getPointer() {
		return pointer;
	}
	
	/**
	 * Returns the location the {@link Projectile} should have, given the current {@link Cannon} direction.
	 * @return	Vector2 the position of the bubble.
	 */
	private Vector2 getBubblePos() {
		return new Vector2(pointer.origin.x + 16, pointer.origin.y + 16)
						.add(pointer.getDirection().nor().scl(100));
	}
}
