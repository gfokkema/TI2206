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

		int a_coeff;
		int b_coeff;

		private Orientation(int a, int b) {
			this.a_coeff = a;
			this.b_coeff = b;
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
		 * newIndex = startIndex + a * width + b,
		 * where a and b are either -1, 0 or 1.
		 * NORTH_EAST.fromIndex(6, 4) will return 3.
		 * @param index
		 * @param width
		 * @return
		 */
		public int fromIndex(int index, int width) {
			return index + a_coeff * width + b_coeff;
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
	
	public Bubble() {
		this.color = getRandomColor();
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
