package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashSet;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

public class ColourBehaviour implements BubbleBehaviour {
	int size = 0;

	/**
	 * Get a {@link Collection} of all {@link Bubble} objects adjacent to the
	 * given {@link Bubble} that have the same color.
	 * 
	 * @return {@link Collection} of {@link Bubble} objects adjacent to id and
	 *         with the same color
	 */
	@Override
	public int chain(GridCell cell) {
		cell.removeBubble();
		return 1;
	}
	
	/**
	 * Calls the other {@link #remove(Board, int)}
	 * Determines if the id is equal to the projectile.
	 */
	@Override
	public int remove(GridCell cell) {
		Collection<GridCell> sameColors = new HashSet<GridCell>();
		sameColors.add(cell);
		Bubble base = cell.getBubble();
		cell.forEachNeighbor(g -> g.depthFirst(sameColors, (Bubble b) -> compareColors(base, b), true));
		size = sameColors.size();
		if (size >= 3) {
			sameColors.forEach((GridCell g) -> {
				g.removeBubble();
				size++;
			});
		} else {
			size = 0;
		}
		return size;
	}

	public boolean compareColors(Bubble a, Bubble b) {
		return a.getColor().equals(b.getColor());
	}

	@Override
	public int trigger(GridCell cell) {
		return 0;
	}
}
