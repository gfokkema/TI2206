package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;
import java.util.Random;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * The bubble class creates bubbles used for the bubbles on the play-field.
 * Bubbles on the field have an orientation (six directions - because of the hexagonal board).
 * Furthermore bubbles possess a color and bounding circle (the latter can be compared to a "hit-box").
 * @author group-15
 *
 */
public class Bubble extends BSDrawable implements Serializable {
	private static final long serialVersionUID = -3647601554787437036L;
	
	public enum BubbleColors {
		RED(Color.RED),
		GREEN(Color.GREEN),
		BLUE(Color.BLUE),
		PURPLE(Color.PURPLE),
		YELLOW(Color.YELLOW),
		GRAY(Color.GRAY),
		CYAN(Color.CYAN);
		
		private Color color;
		private BubbleColors(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
	}

	protected int color;
	protected Circle bounds;
	protected BubbleBehaviour behaviour;
	
	/**
	 * Instantiate a new Bubble, with a Random color.
	 */
	public Bubble() {
		this(new BubbleBehaviour());
	}

	/**
	 * Instantiate a new Bubble, with the given color.
	 * This function is for testing purposes, therefore
	 * it's protected and can only be used in the same package.
	 * @param c - the Color of the Bubble.
	 */
	public Bubble(Color c) {
		this(c, new BubbleBehaviour());
	}
	
	public Bubble(BubbleBehaviour b) {
		this.color = Color.rgba8888(getRandomColor(4));
		this.behaviour = b;
		this.bounds = new Circle();
	}
	
	public Bubble(Color c, BubbleBehaviour b) {
		this.color = Color.rgba8888(c);
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
	 * Pick a ColorValue at random.
	 * @return a randomly chosen ColorValue.
	 */
	protected Color getRandomColor(int length) {
		BubbleColors[] colors = BubbleColors.values();
		return colors[(new Random()).nextInt(length)].getColor();
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
	
	/**
	 * Return the color of this {@link Bubble}.
	 * @return color of this {@link Bubble}.
	 */
	@Override
	public Color getColor() {
		return new Color(color);
	}
}
