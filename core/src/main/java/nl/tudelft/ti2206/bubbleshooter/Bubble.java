package nl.tudelft.ti2206.bubbleshooter;

import java.util.Optional;

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
		 * Returns the opposite orientation,
		 * for example SOUTH_EAST.getOpposite()
		 * will return NORTH_EAST.
		 * @return the opposite orientation
		 */
		public Orientation getOpposite() {
			return orientations[this.ordinal() + 3];
		}
	}
	private static final Orientation[] orientations = Orientation.values();

	public enum Color {
		RED,
		GREEN,
		BLUE,
		PURPLE,
		ORANGE
	}

	private Color color;
	private Bubble[] neighbors;
	private Circle bounds;

	public Bubble() {
		this.neighbors = new Bubble[6];
	}

	public Optional<Bubble> getNeighbor(Orientation dir) {
		return Optional.ofNullable(neighbors[dir.ordinal()]);
	}

	public void setNeighbor(Bubble b, Orientation dir) {
		neighbors[dir.ordinal()] = b;
	}
}
