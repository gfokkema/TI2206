package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;

import nl.tudelft.ti2206.bubbleshooter.core.Bubble.BubbleColors;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * The {@link Board} class represents the playing field which contains all the
 * {@link Bubble} objects.
 */
public class Board extends BSDrawable implements Serializable {
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
		this.bubbles = new HashMap<Integer, Bubble>(grid.getWidth()
				* grid.getHeight());
	}

	/**
	 * Checks the {@link Bubble} that gets shot for collisions with all the
	 * other bubbles.
	 * 
	 * @param b
	 *            {@link Bubble} that has been shot
	 * @return true if there's a collision, false otherwise
	 */
	public boolean collides(Projectile b) {
		for (Bubble v : bubbles.values()) {
			if (v.collides(b))
				return true;
		}

		if (b.getBounds().y + 16 > 480)
			return true;
		if (b.getBounds().x - 16 < 32
				|| b.getBounds().x - 16 > grid.getWidth() * 32) {
			Vector2 dir = b.getDirection();
			dir.x = -dir.x;
		}
		return false;
	}

	/**
	 * Add a {@link Bubble} to the {@link Board} on a specific {@link Board}
	 * index.
	 * 
	 * @param b
	 *            {@link Bubble} that has to be added
	 * @param idx
	 *            {@link Board} index
	 * @return true if the {@link Board} has been added successfully, false
	 *         otherwise
	 */
	public boolean add(Bubble b, int idx) {
		if (bubbles.containsKey(idx))
			return false;

		// Add the Bubble to the list
		bubbles.put(idx, b);

		// Update the bounds of the circle
		b.setBounds(new Circle(grid.getLoc(idx), 16));
		
		setChanged();
		notifyObservers("Bubble has been added to index " + idx + ".");
		
		return true;
	}

	/**
	 * Add a {@link Bubble} to the {@link Board} on a specific hex index.
	 * 
	 * @param b
	 *            {@link Bubble} that has to be added
	 * @param i
	 *            hexagonal x-coordinate
	 * @param j
	 *            hexagonal y-coordinate
	 * @return true if the {@link Board} has been added successfully, false
	 *         otherwise
	 */
	public boolean add(Bubble b, int i, int j) {
		return add(b, grid.toIdx(i, j));
	}

	public int add(Bubble b) {
		int new_idx = grid.getIndex(b.getMidPoint());
		return add(b, new_idx) ? new_idx : -1;
	}

	public Collection<BSDrawable> getDrawables() {
		Collection<BSDrawable> drawables = new ArrayList<BSDrawable>();
		drawables.add(this);
		drawables.addAll(bubbles.values());
		return drawables;
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
	
	/**
	 * Remove all the {@link Bubble}s that are both in the given
	 * {@link Collection} and on the grid.
	 * 
	 * @param bs
	 *            The collection specifying the elements that should be removed.
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
	
	public HashMap<Integer, Bubble> getBubbles() {
		return bubbles;
	}
	
	public HashMap<Integer, Bubble> getColourGroup(Bubble bubble) {
		HashMap<Integer, Bubble> instance = new HashMap<Integer, Bubble>();
		for(Entry<Integer, Bubble> b: bubbles.entrySet()) {
			if(b.getValue().getColor().equals(bubble.getColor())) instance.put(b.getKey(), b.getValue());
		}
		return instance;
	}
	
	public HashMap<Integer, Bubble> getColourGroup(Color color) {
		HashMap<Integer, Bubble> instance = new HashMap<Integer, Bubble>();
		for(Entry<Integer, Bubble> b: bubbles.entrySet()) {
			if(b.getValue().getColor().equals(color)) instance.put(b.getKey(), b.getValue());
		}
		return instance;
	}
			
	public HashMap<Integer, Bubble> getPowerUps() {
		HashMap<Integer, Bubble> powerUps = new HashMap<Integer, Bubble>();
		for(Entry<Integer, Bubble> b : bubbles.entrySet()) {		
			for(int i = 5; i<BubbleColors.values().length; i++) {
				if(BubbleColors.values()[i].getColor().equals(b.getValue().getColor())) {
					powerUps.put(b.getKey(), b.getValue());
				}
			}
		}
		return powerUps;
	}
	
	public Grid getGrid() {
		return grid;
	}

	public boolean isEmpty() {
		return bubbles.isEmpty();
	}
}
