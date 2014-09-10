package nl.tudelft.ti2206.bubbleshooter;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;

public class Bubble {
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
		 * @param index
		 * @param width
		 * @return
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
	protected Color color;
	protected Circle bounds;
	
	/**
	 * Instantiate a new Bubble, with a Random color.
	 */
	public Bubble() {
		this.color = getRandomColor();
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
