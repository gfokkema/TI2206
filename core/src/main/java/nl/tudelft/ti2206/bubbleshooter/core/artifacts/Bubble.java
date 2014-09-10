package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;

public class Bubble {
	public enum Orientation {
		EAST,		//+ 1
		SOUTH_EAST, //+ width
		SOUTH_WEST, //+ width - 1
		WEST,		//- 1
		NORTH_WEST, //- width
		NORTH_EAST; //- width + 1 == - (width - 1)

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
		 * index. For example:
		 * NORTH_EAST.fromIndex(6, 4) will return 3.
		 * @param index
		 * @param width
		 * @return
		 */
		public int fromIndex(int index, int width) {
			//TODO
			return 0;
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
