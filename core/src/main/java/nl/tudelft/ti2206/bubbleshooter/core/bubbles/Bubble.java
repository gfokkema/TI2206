package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.io.Serializable;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * The bubble class creates bubbles used for the bubbles on the play-field.
 * Bubbles on the field have an orientation (six directions - because of the hexagonal board).
 * Furthermore bubbles possess a color and bounding circle (the latter can be compared to a "hit-box").
 * @author group-15
 *
 */
public abstract class Bubble extends BSDrawable implements Serializable {
	private static final long serialVersionUID = -3647601554787437036L;
	
	Circle bounds;
	BubbleBehaviour behaviour;

	public Bubble(BubbleBehaviour b) {
		this.behaviour = b;
		this.bounds = new Circle();
	}

	/**
	 * Check if this Bubble collides with b.
	 * @param b - the bubble that gets shot.
	 * @return true if the Bubbles collided.
	 */
	public boolean collides(Bubble b) {
		return bounds.overlaps(b.bounds);
	}
	
	/**
	 * Sets the bounding {@link Circle} of the bubble.
	 * @param position the position as x and y coordinate.
	 * @param radius radius the radius of the circle.
	 */
	public void setCircle(Vector2 position, float radius) {
		bounds.set(position, radius);
	}
	
	/**
	 * Sets the position of the bubble.
	 * radius stays the same.
	 * @param position vector with x and y coordinate.
	 */
	public void setPosition(Vector2 position) {
		bounds.set(position, bounds.radius);
	}
			
	/**
	 * Get the bounding {@link Circle} of this {@link Bubble}.
	 * @return	{@link Circle} that bounds this bubble
	 */
	public Circle getBounds() {
		return this.bounds;
	}
	
	/**
	 * Gets the midpoint of the bubble.
	 * @return vector2 with x and y coordinate.
	 */
	public Vector2 getMidPoint() {
		return new Vector2(bounds.x, bounds.y);
	}
	
	/**
	 * Set the bounding {@link Circle} of this {@link Bubble}.
	 * @param c	{@link Circle} that bounds this bubble
	 */
	public void setBounds(Circle c) {
		this.bounds = c;
		setChanged();
		notifyObservers();
	}
	
	public BubbleBehaviour getBehaviour() {
		return this.behaviour;
	}
	
	/**
	 * Returns the texture of this {@link Bubble}.
	 * @return {@link #Texture} of this {@link Bubble}.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.BUBBLE;
	}
	
	/**
	 * Gets the position of the bubble.
	 * @return vector2 with c and y coordinate.
	 */
	@Override
	public Vector2 getPosition() {
		return new Vector2(bounds.x - 16, bounds.y - 16);
	}

	/**
	 * Returns the width of this {@link Bubble}.
	 * @return width of this {@link Bubble}.
	 */
	@Override
	public int getWidth() {
		return 32;
	}

	/**
	 * Returns the height of this {@link Bubble}.
	 * @return height of this {@link Bubble}.
	 */
	@Override
	public int getHeight() {
		return 32;
	}
	
}
