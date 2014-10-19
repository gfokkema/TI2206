package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

/**
 * Interface of the behaviour of a {@link Bubble}
 * @author group-15
 *
 */
public interface BubbleBehaviour {	
	
	/**
	 * Get the group of {@link Bubble}s which are needed to be removed.
	 * @param cell TODO
	 * @return {@link Collection<{@link Bubble}>}
	 */
	int chain(GridCell cell);
	
	/**
	 * Removes the group of {@link Bubble}s gotten from {@link #chain(GridCell)}.
	 * @param cell TODO
	 * @return amount of {@link Bubble}s removed.
	 */
	int remove(GridCell cell);
}
