package nl.tudelft.ti2206.bubbleshooter;

import com.badlogic.gdx.math.Circle;

public class Bubble {
	public enum Orientation {
		NORTH_EAST,
		EAST,
		SOUTH_EAST,
		SOUTH_WEST,
		WEST,
		NORTH_WEST
	}

	private Bubble[] neighbors;
	private Circle bounds;
	private boolean isRoot;
	private boolean marked;

	public Bubble() {
		neighbors = new Bubble[6];
	}
}
