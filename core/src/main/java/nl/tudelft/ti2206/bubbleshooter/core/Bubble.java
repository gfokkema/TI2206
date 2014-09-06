package nl.tudelft.ti2206.bubbleshooter.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Bubbles are used in the {@link #BubbleShooterScreen}.
 * The player is able to fire bubbles towards other bubbles.
 * @author group-15
 *
 */
public class Bubble {
	
	/**
	 * Rectangle and texture.
	 * The former being used for placement.
	 */
	Rectangle bubble;
	Texture bubble_img;

	/**
	 * Creates a new bubble.
	 * @param tex the name of the bubble texture.
	 */
	public Bubble(String tex) {
		this.bubble_img = new Texture(Gdx.files.internal(tex));
		this.bubble = new Rectangle();
	}
	
	/**
	 * Set position of the bubble on screen (in pixels)
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.bubble.x = x;
		this.bubble.y = y;
	}
	
	/**
	 * Get the rectangle bound to the bubble.
	 * @return
	 */
	public Rectangle getRectangle() {
		return bubble;	
	}
	
	/**
	 * Draws the bubble
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(bubble_img, bubble.x, bubble.y);
	}
}
