package nl.tudelft.ti2206.bubbleshooter;

import java.util.Optional;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;

public class Bubble {
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
			return orientations[(this.ordinal() + 3) % 6];
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
	protected Color color;
	protected Bubble[] neighbors;
	protected Circle bounds;
	
	public Bubble() {
		this.color = getRandomColor();
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
	
	public Color getColor() {
		return this.color;
	}

	/**
	 * Pick a ColorValue at random.
	 * @return a randomly chosen ColorValue.
	 */
	protected Color getRandomColor() {
		return colors[(new Random()).nextInt(colors.length)];
	}
}
