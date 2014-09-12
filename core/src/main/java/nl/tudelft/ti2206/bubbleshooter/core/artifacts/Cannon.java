package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import nl.tudelft.ti2206.bubbleshooter.Board;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
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
	Board board;	
	
	Texture image;
	Sprite sprite;
	float angle;
	
	Projectile projectile;
	Texture fg;
	Sprite bubbleSprite;
	

	
	private final float LEFT_BOUNDARY = 60;
	private final float RIGHT_BOUNDARY = -60;
	private final int sensitivity = 100;
	private final int velocity = 5;
	
	/**
	 * Cannon constructor
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Cannon(int x, int y, Board board) {
		pointer = new Pointer(new Vector2(x, y));
		this.board = board;
		
		// texture for cannon + angle
		image = new Texture("cannon.png");
		angle = 0;
		
		fg = new Texture("Bubble-Blue.png");
		
		// sprite settings
		sprite = new Sprite(image);
		sprite.setOrigin(sprite.getWidth()/2, 25);
		sprite.setPosition(x - image.getWidth()/2, y);
		
		// add bubble
		CreateProjectile();
		pointer.setOrigin(new Vector2(x-fg.getWidth()/4, y));
	}
	
	/**
	 * Set the angle of the cannon (in other words the direction of which the cannon shoots in).
	 * @param degrees the angle in amount of degrees
	 */
	public void setAngle(float degrees) {
		
		if(degrees > LEFT_BOUNDARY) {
			angle = LEFT_BOUNDARY;
		}
		else if(degrees < RIGHT_BOUNDARY) {
			angle = RIGHT_BOUNDARY;
		}
		else
			angle = degrees;
				
		// rotate the actual rotation difference.
		sprite.rotate(sprite.getRotation() - angle);
		sprite.setRotation(angle);
		pointer.setAngle(angle);
	}
		
	/**
	 * Create a new Projectile
	 * @return new Projectile
	 */
	public Projectile CreateProjectile() {
		Circle circle = new Circle(new Vector2(pointer.getOrigin()).add(new Vector2(pointer.getDirection()).scl(100)), 16);
		return projectile = new Projectile(circle, new Vector2(pointer.getDirection()), 0);	
	}
	
	/**
	 * draw attributes
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		batch.setColor(projectile.getColor());
		batch.draw(fg, projectile.getBounds().x, projectile.getBounds().y, 32, 32);
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
	
	public int getVelocity() {
		return velocity;
	}
	
	public float getSensitivity() {
		return sensitivity;
	}
	
	public Projectile getProjectile() {
		return projectile;
	}
}
