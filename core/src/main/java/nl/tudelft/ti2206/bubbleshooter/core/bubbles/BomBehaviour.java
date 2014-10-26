package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

/**
 * This class describes the {@link BubbleBehaviour} of the {@link BomBubble}.
 * All adjacent {@link Bubble}s to the {@link BomBubble} will be removed when hit.
 * @author group-15
 *
 */
public class BomBehaviour implements BubbleBehaviour{
	int score = 0;
	
	@Override
	public int chain(GridCell cell) {
		return trigger(cell);
	}
	
	/**
	 * Remove each neighboring {@link Bubble}.
	 * @param cell	The {@link GridCell} containing the {@link BomBubble}.
	 * @return the amount of {@link Bubble}s removed due to this explosion.
	 */
	@Override
	public int remove(GridCell cell) {
		cell.removeBubble();
		cell.forEachNeighbor((GridCell gc) -> score += gc.chain());
		return score;
	}

	@Override
	public int trigger(GridCell cell) {
		return remove(cell);
	}
}
