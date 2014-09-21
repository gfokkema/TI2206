package nl.tudelft.ti2206.bubbleshooter.engine;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * This class holds all attributes the renderer needs to have for rendering a {@link TextureID} to the screen.
 */
public abstract class BSDrawable {
	/**
	 * This method returns the {@link TextureID} of the concrete object it represents.
	 * @return	{@link TextureID} representing a {@link Texture}
	 */
	public abstract TextureID getTexture();
	
	/**
	 * This method returns the left most corner of this {@link BSDrawable}.
	 * @return	{@link Vector2} containing xy-coords of the left most corner
	 */
	public Vector2 getPosition() {
		return new Vector2(0, 0);
	}
	
	/**
	 * This method returns the origin, or rotation point, of this {@link BSDrawable}.
	 * @return	{@link Vector2} containing xy-coords of the origin
	 */
	public Vector2 getOrigin() {
		return new Vector2(0, 0);
	}
	
	/**
	 * This method returns the width of this {@link BSDrawable}.
	 * @return	width in pixels
	 */
	public abstract int getWidth();
	
	/**
	 * This method returns the height of this {@link BSDrawable}.
	 * @return	height in pixels
	 */
	public abstract int getHeight();
	
	/**
	 * This method returns the {@link Color} of this {@link BSDrawable}.
	 * @return	{@link Color} of this {@link BSDrawable}
	 */
	public Color getColor() {
		return Color.WHITE;
	}
	
	/**
	 * This method returns the angle of rotation in degrees for this {@link BSDrawable}.
	 * @return	angle in degrees
	 */
	public float getRotation() {
		return 0;
	}
}
