package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

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
		return remove(cell);
	}
	
	/**
	 * Removes all the {@link Bubble}s.
	 */
	@Override
	public int remove(GridCell cell) {
		return 0;
	}
}
