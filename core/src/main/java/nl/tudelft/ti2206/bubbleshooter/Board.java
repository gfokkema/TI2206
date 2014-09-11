package nl.tudelft.ti2206.bubbleshooter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import nl.tudelft.ti2206.bubbleshooter.Bubble.Orientation;

/**
 * The {@link Board} class represents the playing field which contains all the {@link Bubble} objects.
 */
public class Board {
	private int width = 8, height = 20;
	private HashMap<Integer,Bubble> bubbles;

	/**
	 * Constructs a {@link Board} that can hold {@link Bubble} objects.
	 * The {@link Board} represents the playing field.
	 * @param width		the width of the board in bubbles
	 * @param height	the height of the board in bubbles
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;

		bubbles = new HashMap<Integer,Bubble>(this.width * this.height);
		for (int i = 4; i < 10; i++) {
			bubbles.put(i, new Bubble());
		}
		for (int i = 0; i < 4; i++) {
			bubbles.put(i, new Bubble(Color.RED));
		}
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
     * Checks the {@link Bubble} that gets shot for collisions with all the other bubbles.
     * @param b	{@link Bubble} that has been shot
     * @return 	true if there's a collision, false otherwise
     */
	public boolean collides(Bubble b) {
		for (Bubble c : bubbles.values()) {
			if (c.collides(b)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add a {@link Bubble} to the {@link Board} on a specific hex index.
	 * @param b	{@link Bubble} that has to be added
	 * @param i	hexagonal x-coordinate
	 * @param j	hexagonal y-coordinate
	 */
	public void add(Bubble b, int i, int j) {
		// Add the Bubble to the list
		// Update the bounds of the circle
	}
	
	/**
	 * Returns a {@link Map} with the {@link Bubble} objects that are currently on the {@link Board}.
	 * @return	{@link Map} with all {@link Bubble} objects
	 */
	public Map<Integer, Bubble> getBubbles() {
		return bubbles;
	}

	/**
	 * Get a {@link Collection} of all {@link Bubble} objects
	 * adjacent to the given {@link Bubble} that have the same color.
	 * @param id	the grid id of the {@link Bubble}
	 * @return 		{@link Collection} of {@link Bubble} objects adjacent to id and with the same color
	 */
	public Collection<Bubble> getColorGroup(int id) {
		// Search for bubbles of the same color
		HashMap<Integer, Bubble> sameColors = new HashMap<Integer, Bubble>();
		depthFirst(
				id,
				(current, neighbor) -> bubbles.get(current).color == bubbles.get(neighbor).color,
				sameColors
		);
		sameColors.put(id, bubbles.get(id));
		return sameColors.values();
	}
	
	/**
	 * Traversal to find all of the nodes that should be removed.
	 * If nothing should be removed, then nothing is returned.
	 * @return 		{@link Collection} that's either empty or filled with nodes that will be removed 
	 */
	public Collection<Bubble> getDisconnectedGroup() {
		// The same Map will be used for each depth-first search.
		HashMap<Integer, Bubble> connectedToCeiling = new HashMap<Integer, Bubble>();
		for(int ceilingIndex = 0; ceilingIndex < width; ceilingIndex++) {
			if(!bubbles.containsKey(ceilingIndex)) {
				//There's no bubble here
				continue;
			}
			depthFirst(
					ceilingIndex,
					(current, neighbor) -> true,
					connectedToCeiling
			);
		}
		List<Bubble> result = new ArrayList<Bubble>(bubbles.values());
		// Remove all of the bubbles that are not connected to the ceiling.
		result.removeAll(connectedToCeiling.values());
		removeAll(result);
		return result;
	}

	private void depthFirst(Integer currentIndex, BiPredicate<Integer, Integer> condition, Map<Integer, Bubble> remove) {
		for(Orientation o : Bubble.orientations) {
			int neighborIndex = o.fromIndex(currentIndex, this.width);
			if (!adjacent(currentIndex, neighborIndex))	continue;
			// Check if there's a neighbor Bubble.
			if (!bubbles.containsKey(neighborIndex))	continue;

			// Check if the neighbor hasn't already been visited.
			if (!remove.containsKey(neighborIndex)) {
				if (condition.test(currentIndex, neighborIndex)) {
					remove.put(neighborIndex, bubbles.get(neighborIndex));
					depthFirst(neighborIndex, condition, remove);
				}
			}
		}
	}

	public void removeAll(Collection<Bubble> bs) {
		bubbles.values().removeAll(bs);
	}

	/**
	 * Checks whether two {@link Bubble} objects are adjacent to each other
	 * @param a		{@link Bubble} object a
	 * @param b		{@link Bubble} object b
	 * @return		true if a and b are adjacent, false otherwise
	 */
	public boolean adjacent(int a, int b) {
		if (a > b) { int temp = a; a = b; b = temp; }
		
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
}
