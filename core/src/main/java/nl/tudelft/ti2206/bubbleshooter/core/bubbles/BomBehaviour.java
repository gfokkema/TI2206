package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

/**
 * This class describes the {@link BubbleBehaviour} of the {@link BomBubble}.
 * All adjacent {@link Bubble}s to the {@link BomBubble} will be removed when hit.
 * @author group-15
 *
 */
public class BomBehaviour implements BubbleBehaviour{
	
	/**
	 * Gets the {@link Collection<{@link Bubble}>} of {@link Bubble}s to be removed.
	 * @return the {@link Collection<{@link Bubble}>} of all adjacent bubbles.
	 */
	@Override
	public int chain(GridCell cell) {
		return remove(cell);
	}
	
	/**
	 * Removes the {@link Bubble}s caught within the blast of the {@link BomBubble}.
	 */
	@Override
	public int remove(GridCell cell) {
		cell.removeBubble();
		cell.forEachNeighbor((GridCell gc) -> gc.chain());
		return 0;
	}
}
