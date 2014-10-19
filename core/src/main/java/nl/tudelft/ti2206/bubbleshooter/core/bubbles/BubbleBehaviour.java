package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

/**
 * Interface of the behaviour of a {@link Bubble}
 * @author group-15
 *
 */
public interface BubbleBehaviour {	
	
	/**
	 * Chaining behaviour of a {@link Bubble}. Determines whether it
	 * responds to a chainreaction effect.
	 * @param cell The GridCell which contains the bubble.
	 * @return the amount of bubbles removed because of the chain effect
	 * after this call.
	 */
	int chain(GridCell cell);
	
	/**
	 * Removal behaviour of a bubble. If there's a call to {@link GridCell}'s
	 * chain() method, it means that it can potentially trigger a chainreaction.
	 * Whether or not that's the case, is determined by the chain method of the
`	 * next {@link Bubble}'s {@link BubbleBehaviour}.
	 * @param cell The GridCell which contains the bubble.
	 * @return the amount of bubbles removed.
	 */
	int remove(GridCell cell);
}
