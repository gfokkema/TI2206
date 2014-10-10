package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;

import com.badlogic.gdx.math.Vector2;

/**
 * The type of {@link Grid} a board uses.
 * @author group-15
 *
 */
public class Grid implements Serializable {
	private static final long serialVersionUID = -3156876087711309439L;
	private int width = 8, height = 20;
	
	/**
	 * Constructs the mathematical representation of a grid.
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the width of the {@link Board}.
	 * @return	width in bubbles
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the height of the {@link Board}.
	 * @return	height in bubbles
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Checks whether two {@link Bubble} objects are adjacent to each other
	 * @param a		{@link Bubble} object a
	 * @param b		{@link Bubble} object b
	 * @return		true if a and b are adjacent, false otherwise
	 */
	public boolean adjacent(int a, int b) {
		if (a > b) { int temp = a; a = b; b = temp; }
		if (b < 0)	return false;
		if (a < 0)	return false;
		
		Vector2 xy_a = toXY(a);
		Vector2 xy_b = toXY(b);
		
		return	xy_a.y == xy_b.y && b - a == 1 ||
				xy_b.y - xy_a.y == 1 && b - a > 0 &&
					((b - a) == width || (b - a) == width - 1);
	}

	/**
	 * Converts a given hexagonal xy-coordinate into an index value
	 * @param x		hexagonal x-coordinate
	 * @param y		hexagonal y-coordinate
	 * @return		the board index
	 */
	public int toIdx(int x, int y) {
		if (x < 0 || y < 0 || x >= width - y % 2 || y >= height)
			throw new IndexOutOfBoundsException();
		
		return y * width - y / 2 + x;
	}

	/**
	 * Converts a given board index into a hexagonal xy-coordinate
	 * @param idx	the board index
	 * @return		{@link Vector2} representing the hexagonal xy-coordinates
	 */
	public Vector2 toXY(int idx) {
		int rowset = (width * 2 - 1);
		
		int y_even = idx / rowset;
		int y = y_even * 2 + (idx - y_even * rowset) / width;
		int x = idx - toIdx(0, y);
		
		if (idx < 0 || y >= height) throw new IndexOutOfBoundsException();
		return new Vector2(x, y);
	}
	
	/**
	 * Returns the topleft xy-coordinates of a bubble
	 * @param idx	the index of the bubble on the board
	 * @return		{@link Vector2} representing xy-coordinates
	 */
	public Vector2 getLoc(int idx) {
		int odd = (idx % (width * 2 - 1)) / width;
		Vector2 xy = toXY(idx);
		
		int x = (int)xy.x * 32	+ odd * 16 + 32;
		int y = (int)xy.y * 28;
		
		// offset the game field with 190 px and correct from left -> mid
		x = x + 16;
		// flip the coordinate system and correct from top -> mid
		y = 480 - (y + 16);

		return new Vector2(x, y);
	}

	/**
	 * Get the index of the grid cell containing the given
	 * {@link Vector2}.
	 * @param loc The (x,y)-coordinate on the grid.
	 * @return The index of the grid containing the given
	 *         coordinate.
	 */
	public int getIndex(Vector2 loc) {
		int x = (int)loc.x;
		int y = (int)loc.y;
		x = x - 32;
		y = 480 - y;
		int y_id = y / 28;
		int x_id = x - (y_id & 1) * 16;
		x_id /= 32;
		
		return toIdx(x_id, y_id);
	}
}
