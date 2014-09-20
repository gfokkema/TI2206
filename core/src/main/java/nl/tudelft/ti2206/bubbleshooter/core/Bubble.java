package nl.tudelft.ti2206.bubbleshooter.core;

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
public class Bubble extends BSDrawable {
	
	/**
	 * The possible orientations.
	 */
	public enum Orientation {
		EAST(0, 1),			//+ 1
		SOUTH_EAST(1, 0),	//+ width
		SOUTH_WEST(1, -1),	//+ width - 1
		WEST(0,-1),			//- 1
		NORTH_WEST(-1, 0),	//- width
		NORTH_EAST(-1, 1);	//- width + 1 == - (width - 1)

		int delta_y;
		int delta_x;

		private Orientation(int a, int b) {
			this.delta_y = a;
			this.delta_x = b;
		}

		/**
		 * Returns the opposite orientation, for example
		 * SOUTH_EAST.getOpposite() will return NORTH_EAST.
		 * @return the opposite orientation
		 */
		public Orientation getOpposite() {
			return orientations[(this.ordinal() + 3) % 6];
		}

		/**
		 * Returns the index at this direction from the given
		 * index. It works according to the following formula:
		 * newIndex = startIndex + delta_y * width + delta_x,
		 * where delta_y and delta_x both be either -1, 0 or 1.
		 * NORTH_EAST.fromIndex(6, 4) will return 3.
		 * @param index	{@link Board} index
		 * @param width	{@link Board} width
		 * @return		new {@link Board} index
		 */
		public int fromIndex(int index, int width) {
			return index + delta_y * width + delta_x;
		}
	}
	protected static Orientation[] orientations = Orientation.values();
	
	protected static Color[] colors = {
		Color.RED,
		Color.GREEN,
		Color.BLUE,
		Color.PURPLE,
		Color.YELLOW
	};
	private Color color;
	private Circle bounds;
	
	/**
	 * Instantiate a new Bubble, with a Random color.
	 */
	public Bubble() {
		this.color = getRandomColor();
		this.bounds = new Circle();
	}

	/**
	 * Instantiate a new Bubble, with the given color.
	 * This function is for testing purposes, therefore
	 * it's protected and can only be used in the same package.
	 * @param c - the Color of the Bubble.
	 */
	protected Bubble(Color c) {
		this.color = c;
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
	protected Color getRandomColor() {
		return colors[(new Random()).nextInt(colors.length)];
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
	}

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

	@Override
	public int getWidth() {
		return 32;
	}

	@Override
	public int getHeight() {
		return 32;
	}
	
	/**
	 * return color
	 * @return color
	 */
	@Override
	public Color getColor() {
		return this.color;
	}
}
