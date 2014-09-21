package nl.tudelft.ti2206.bubbleshooter.core;

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
		return Orientation.values()[(this.ordinal() + 3) % 6];
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