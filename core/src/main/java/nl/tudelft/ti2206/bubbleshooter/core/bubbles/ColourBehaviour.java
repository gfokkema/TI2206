package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashSet;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

public class ColourBehaviour implements BubbleBehaviour {
	
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
		cell.depthFirst(sameColors, (Bubble b) -> compareColors(cell.getBubble(), b));
		int size = sameColors.size();
		if (size >= 3) {
			sameColors.forEach((GridCell g) -> g.removeBubble());
			return size;
		}
		return 0;
	}

	public boolean compareColors(Bubble a, Bubble b) {
		return a.getColor().equals(b.getColor());
	}
}
