package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;
import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Create a cannon which can shoot bubbles!
 * This class also handles the input necessary to shoot and rotate the cannon.
 * @author group-15
 *
 */
public class Cannon extends BSDrawable implements Serializable {
	private static final long serialVersionUID = 687534278195329047L;
	/**
	 * Variable initialization.
	 */
	Pointer pointer;
	Projectile projectile;
	Vector2 position;
	
	private final float LEFT_BOUNDARY = 60;
	private final float RIGHT_BOUNDARY = -60;
	private final int sensitivity = 100;
	private final int velocity = 5;
	
	/**
	 * Constructs a {@link Cannon} using a specified at the specified location.
	 * @param x			coordinate
	 * @param y 		coordinate
	 */
	public Cannon(int x, int y) {
		position = new Vector2(x - 50, y);
		pointer = new Pointer(new Vector2(x, y));
		pointer.setOrigin(new Vector2(x - 16, y));
		
		// add bubble
		projectile = new Projectile(new Circle(getBubblePos(), 16), pointer.direction, 0);
		setAngle(0);
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
		pointer.setAngle(degrees);
		
		projectile.setBounds(new Circle(getBubblePos(), 16));
		
		setChanged();
		notifyObservers("Cannon angle is now " + degrees + " degrees.");
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
	 * @param board	{@link Board} that this projectile will be shot at
	 * @return		{@link Projectile} that has been shot
	 */
	public Projectile shoot(ArrayList<Color> colors) {
		Projectile fired = projectile;
		fired.setVelocity(velocity);
		fired.setDirection(new Vector2(pointer.direction));
		
		projectile = new Projectile(colors, new Circle(getBubblePos(), 16), pointer.direction.cpy(), 0);
		Assets.getAssets().get(SoundID.CANNON).play();
		setChanged();
		notifyObservers("Cannon has been shot!");
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
						.add(pointer.getDirection().cpy().nor().scl(100));
	}

	/**
	 * Returns the {@link TextureID} of this {@link Cannon}.
	 * @return {@link TextureID} of this {@link Cannon}
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.CANNON;
	}
	
	/**
	 * Returns the position of this {@link Cannon}.
	 * @return {@link #position} of this {@link Cannon}.
	 */
	@Override
	public Vector2 getPosition() {
		return position;
	}
	
	/**
	 * Returns the origin of this {@link Cannon}.
	 * @return the origin of this {@link Cannon}.
	 */
	@Override
	public Vector2 getOrigin() {
		return new Vector2(50, 16);
	}

	/**
	 * Returns the width of this {@link Cannon}.
	 * @return the width of this {@link Cannon}.
	 */
	@Override
	public int getWidth() {
		return 100;
	}

	/**
	 * Returns the height of this {@link Cannon}.
	 * @return the height of this {@link Cannon}.
	 */
	@Override
	public int getHeight() {
		return 100;
	}
	
	/**
	 * Returns the angle of this {@link Cannon}.
	 * @return the angle of thie {@link Cannon}.
	 */
	@Override
	public float getRotation() {
		return pointer.getAngle();
	}
}
