package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.math.Vector2;

/**
 * The {@link Board} class represents the playing field which contains all the
 * {@link Bubble} objects.
 */
public class Board extends BSDrawable implements Serializable, Collidable {
	private static final long serialVersionUID = -4815917036827285256L;
	private Grid grid;
	private HashMap<Integer, Bubble> bubbles;

	/**
	 * Constructs a {@link Board} that can hold {@link Bubble} objects. The
	 * {@link Board} represents the playing field.
	 * 
	 * @param width
	 *            the width of the board in bubbles
	 * @param height
	 *            the height of the board in bubbles
	 */
	public Board(int width, int height) {
		this.grid = new Grid(width, height);
		this.bubbles = new HashMap<Integer, Bubble>(grid.getWidth() * grid.getHeight());
	}

	public boolean bubbleBelowLine() {
		return bubbleBelowLine(grid.getGridHeight() - 2);
	}

	protected boolean bubbleBelowLine(int lineRow) {
		int start = grid.toIdx(0,lineRow);
		int lastRowWidth = grid.getGridWidth() - (grid.getGridHeight() % 2 - 1);
		int finish = grid.toIdx(lastRowWidth - 1, grid.getGridHeight() - 1);
		for(int i = start; i <= finish; i++) {
			if(bubbles.containsKey(i)) return true;
		}
		return false;
	}

	/**
	 * Performs a depth-first search starting from the given index. Results are
	 * added to the given {@link Map}. Each node will be tested against the
	 * given {@link BiPredicate}, and will only be added to the solution set if
	 * the test evaluates to true.
	 * 
	 * @param currentIndex
	 *            The index of the node to start the search from.
	 * @param condition
	 *            The condition to test against against each potential result.
	 * @param remove
	 *            The solution set accumulator, which at the end contains all
	 *            results.
	 */
	public void depthFirst(Integer currentIndex,
			BiPredicate<Integer, Integer> condition, Map<Integer, Bubble> remove) {
		for (Orientation o : Orientation.values()) {
			int neighborIndex = o.fromIndex(currentIndex, grid.getWidth());
			if (!grid.adjacent(currentIndex, neighborIndex))
				continue;
			// Check if there's a neighbor Bubble.
			if (!bubbles.containsKey(neighborIndex))
				continue;

			// Check if the neighbor hasn't already been visited.
			if (!remove.containsKey(neighborIndex)) {
				if (condition.test(currentIndex, neighborIndex)) {
					remove.put(neighborIndex, bubbles.get(neighborIndex));
					depthFirst(neighborIndex, condition, remove);
				}
			}
		}
	}
	
	public HashMap<Integer, Bubble> getBubbles() {
		return bubbles;
	}
	
	/**
	 * Traversal to find all of the nodes that should be removed. If nothing
	 * should be removed, then nothing is returned.
	 * 
	 * @return {@link Collection} that's either empty or filled with nodes that
	 *         will be removed
	 */
	public Collection<Bubble> getDisconnectedGroup() {
		// The same Map will be used for each depth-first search.
		HashMap<Integer, Bubble> connectedToCeiling = new HashMap<Integer, Bubble>();
		for (int ceilingIndex = 0; ceilingIndex < grid.getWidth(); ceilingIndex++) {
			if (!bubbles.containsKey(ceilingIndex)) {
				// There's no bubble here
				continue;
			}
			connectedToCeiling.put(ceilingIndex, bubbles.get(ceilingIndex));
			depthFirst(ceilingIndex, (current, neighbor) -> true,
					connectedToCeiling);
		}
		List<Bubble> result = new ArrayList<Bubble>(bubbles.values());
		// Remove all of the bubbles that are not connected to the ceiling.
		result.removeAll(connectedToCeiling.values());
		return result;
	}
	
	public Collection<BSDrawable> getDrawables() {
		Collection<BSDrawable> drawables = new ArrayList<BSDrawable>();
		drawables.add(this);
		grid.cells.values().forEach((GridCell c) -> {
			if (c.isOccupied()) drawables.add(c.getBubble());
		});
		return drawables;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public Collection<Bubble> getGroup(int idx) {
		return bubbles.get(idx).getBehaviour().getGroup(this, idx);
	}

	public boolean isEmpty() {
		for (GridCell c : grid.cells.values()) {
			if (c.isOccupied()) return false;
		};
		return true;
	}
	
	/**
	 * Remove all the {@link Bubble}s that are both in the given
	 * {@link Collection} and on the grid.
	 * @param bs	The collection specifying the elements that should be removed.
	 * @return		the number of bubbles that have been removed
	 */
	public int removeAll(Collection<Bubble> bs) {
		if(bs == null || bs.isEmpty()) return 0;
		bubbles.values().removeAll(bs);
		setChanged();
		notifyObservers(bs.size() + " bubbles have been removed.");
		return bs.size();
	}
	
	@Override
	public TextureID getTexture() {
		return TextureID.BORDER;
	}

	@Override
	public int getWidth() {
		return 320;
	}

	@Override
	public int getHeight() {
		return 480;
	}

	@Override
	public boolean collides(Projectile p) {
		if (grid.collides(p)) return true;
		
		if (p.getBounds().y + 16 > 480) return true;
		if (p.getBounds().x - 16 < 32
				|| p.getBounds().x - 16 > grid.getWidth() * 32) {
			Vector2 dir = p.getDirection();
			dir.x = -dir.x;
		}
		return false;
	}
}
