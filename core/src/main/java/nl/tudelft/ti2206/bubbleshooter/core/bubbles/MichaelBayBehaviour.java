package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

/**
 * This class describes the {@link BubbleBehaviour} of the {@link MichaelBayBubble}.
 * When the {@link MichaelBayBubble} is hit, all {@link Bubble}s are removed.
 * This results into passing on to the next level immediately.
 * @author group-15
 *
 */
public class MichaelBayBehaviour implements BubbleBehaviour {
	
	/**
	 * Gets the {@link Collection<{@link Bubble}>} of {@link Bubble}s to be removed.
	 * @return the {@link Collection<{@link Bubble}>} of all bubbles.
	 */
	@Override
	public int chain(GridCell cell) {
		return trigger(cell);
	}
	
	/**
	 * Removes all the {@link Bubble}s.
	 */
	@Override
	public int remove(GridCell cell) {
		Collection<GridCell> allCells = new HashSet<GridCell>();
		cell.depthFirst(allCells, false);
		Collection<GridCell> allOccupied = allCells.stream().filter(g -> g.isOccupied())
						 									.collect(Collectors.toList());
		allOccupied.forEach(g -> g.removeBubble());
		return allOccupied.size();
	}

	@Override
	public int trigger(GridCell cell) {
		return remove(cell);
	}
}
