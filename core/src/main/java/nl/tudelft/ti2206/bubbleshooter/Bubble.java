package nl.tudelft.ti2206.bubbleshooter;

import java.util.Optional;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bubble extends Sprite {
	public enum Orientation {
		NORTH_EAST,
		EAST,
		SOUTH_EAST,
		SOUTH_WEST,
		WEST,
		NORTH_WEST;

		/**
		 * Returns the opposite orientation, for example
		 * SOUTH_EAST.getOpposite() will return NORTH_EAST.
		 * 
		 * @return the opposite orientation
		 */
		public Orientation getOpposite() {
			return orientations[this.ordinal() + 3];
		}
	}
	protected static Orientation[] orientations = Orientation.values();

	public enum ColorValue {
		RED(0xFF0000FF),
		GREEN(0x00FF00FF),
		BLUE(0x0000FFFF),
		PURPLE(0xFF00FFFF),
		YELLOW(0xFFFF00FF);

		protected int rgba;

		private ColorValue(int rgba) {
			this.rgba = rgba;
		}
	}
	protected static ColorValue[] colors = ColorValue.values();

	protected ColorValue color;
	protected Bubble[] neighbors;
	protected Circle bounds;

	/**
	 * Add a new Bubble at position mid
	 * @param mid the position of the Bubble.
	 */
	public Bubble(Vector2 mid) {
		super(new Texture("Bubble-Blue.png"));
		this.bounds = new Circle();
		this.setPosition(mid.x, mid.y);
		this.neighbors = new Bubble[6];
		this.color = getRandomColor();
		this.setColor(new Color(color.rgba));
	}

	/**
	 * Get the neighbor at the Orientation specified.
	 * @param orient - the Orientation of the neighbor.
	 * @return The neighbor, or nothing if there is no neighbor.
	 */
	public Optional<Bubble> getNeighbor(Orientation orient) {
		return Optional.ofNullable(neighbors[orient.ordinal()]);
	}

	/**
	 * Set the neighbor at the given Orientation.
	 * @param b - the new neighbor of this Bubble.
	 * @param orient - the Orientation of the neighbor
	 */
	public void setNeighbor(Bubble b, Orientation orient) {
		neighbors[orient.ordinal()] = b;
	}

	/**
	 * Check if this Bubble collides with b.
	 * @param b - the bubble that gets shot.
	 * @return true if the Bubbles collided.
	 */
	public boolean collides(Bubble b) {
		return bounds.overlaps(b.bounds);

	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.bounds.setPosition(x, y);
	}

	/**
	 * Pick a ColorValue at random.
	 * @return a randomly chosen ColorValue.
	 */
	protected ColorValue getRandomColor() {
		return colors[getRNG().nextInt(colors.length)];
	}

	/**
	 * Get a specific Random Number Generator (RNG).
	 * @return a RNG
	 */
	protected Random getRNG() {
		return new Random();
	}
}
