package nl.tudelft.ti2206.bubbleshooter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiPredicate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import nl.tudelft.ti2206.bubbleshooter.Bubble.Orientation;
import nl.tudelft.ti2206.bubbleshooter.utils.DisjointSet;


/**
 * Represents the play field with all the bubbles.
 */
public class Board {
	private int width = 8, height = 20;
	private HashMap<Integer,Bubble> bubbles;

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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
    /**
     * Checks the bubble that gets shot, with all the other bubbles if there is a collision.
     * @param b - the bubble that gets shot.
     * @return A boolean gets returned depending if there was a collision.
     */
	public boolean collides(Bubble b) {
		for (Bubble c : bubbles.values()) {
			if (c.collides(b)) {
				return true;
			}
		}

		return false;
	}

	public void add(Bubble b, int i, int j) {
		// Add the Bubble to the list
		// Update the bounds of the circle
	}
	
	public Map<Integer, Bubble> getBubbles() {
		return bubbles;
	}

	/**
	 * Get a List of all the same colors adjacent to the
	 * given Bubble.
	 * @param id - The id on the grid of the Bubble.
	 * @return The List of the Bubble at the given id and its
	 *         adjacent Bubbles of the same color.
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
	 * Traversal to find all of the nodes that should be removed. If nothing
	 * should be removed, then nothing is returned.
	 * @param b - The bubble where it all starts.
	 * @return An Optional which represents nothing, or the List of nodes that
	 *         should be removed.
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

	public boolean adjacent(int a, int b) {
		if (a > b) { int temp = a; a = b; b = temp; }
		if (b < 1) 					return false;
		if (b >= width * height) 	return false;
		
		Vector2 xy_a = toXY(a);
		Vector2 xy_b = toXY(b);
		
		return	xy_a.y == xy_b.y && b - a == 1 ||
				xy_b.y - xy_a.y == 1 && b - a > 0 &&
					((b - a) == width || (b - a) == width - 1);
	}

	public int toIdx(int x, int y) {
		return y * width - y / 2 + x;
	}

	public Vector2 toXY(int idx) {
		int rowset = (width * 2 - 1);
		
		int y_even = idx / rowset;
		int y = y_even * 2 + (idx - y_even * rowset) / width;
		int x = idx - toIdx(0, y);
		
		return new Vector2(x, y);
	}

}
