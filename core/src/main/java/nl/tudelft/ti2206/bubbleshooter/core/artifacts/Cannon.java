package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import nl.tudelft.ti2206.bubbleshooter.Bubble;
import nl.tudelft.ti2206.bubbleshooter.Projectile;
import nl.tudelft.ti2206.bubbleshooter.SoundEffect;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;

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
	float angle;
	Launch game;
	float volume;
	SoundEffect SFX;
	Projectile projectile;
	
	private final float LEFT_BOUNDARY = 60;
	private final float RIGHT_BOUNDARY = -60;
	private final int sensitivity = 100;
	private final int velocity = 5;
	
	/**
	 * Cannon constructor
	 * @param game the current game session
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Cannon(SoundEffect settings, int x, int y) {
		super(new Sprite(new Texture("cannon.png")));
		this.setOrigin(this.getWidth() / 2, 25);
		this.setPosition(x - this.getWidth() / 2, y);
		pointer = new Pointer(new Vector2(x, y));
		pointer.setOrigin(new Vector2(x - 16, y));
		
		// add bubble
		projectile = new Projectile(new Circle(getBubblePos(), 16), pointer.direction, 0);
		setAngle(0);
		SFX = settings;
		Gdx.app.log("SFXVolkannon", "" + settings.getVolume());
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
		this.rotate(this.getRotation() - angle);
		this.setRotation(angle);
		pointer.setAngle(angle);
		
		projectile.setBounds(new Circle(getBubblePos(), 16));
	}
	
	public Bubble getBubble() {
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
		// wub wub :D
		projectile.getSFX().setVolume(SFX.getVolume());
		projectile.getSFX().play();
		return fired;
	}
	
	/**
	 * Controls handleInput
	 */
	public void handleInput() {
		//if pressed left, turn cannon to the left
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			angle += sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle);
		}
		
		//if pressed right, turn cannon to the right
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			angle -= sensitivity*Gdx.graphics.getDeltaTime(); 
			setAngle(angle);
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
