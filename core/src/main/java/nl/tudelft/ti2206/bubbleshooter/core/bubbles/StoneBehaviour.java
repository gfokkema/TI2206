package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

/**
 * This class describes the {@link BubbleBehaviour} of the {@link StoneBubble}.
 * When the {@link StoneBubble} is hit, nothing really happens.
 * This means it can be only removed if it is removed by disconneting a group.
 * @author group-15
 *
 */
public class StoneBehaviour implements BubbleBehaviour {
	
	/**
	 * Remove the {@link StoneBubble} when it's part of a chain reaction.
	 */
	@Override
	public int chain(GridCell cell) {
		cell.removeBubble();
		return 1;
	}

	/**
	 * Don't remove, because it's not part of a chain reaction.
	 */
	@Override
	public int remove(GridCell cell) {
		return 0;
	}

	@Override
	public int trigger(GridCell cell) {
		return 0;
	}
}
