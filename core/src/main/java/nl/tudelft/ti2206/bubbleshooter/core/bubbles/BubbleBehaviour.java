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
	 * @param cell The {@link GridCell} which contains the {@link Bubble}.
	 * @return the amount of {@link Bubble}s removed because of the chain effect
	 * after this call.
	 */
	int chain(GridCell cell);
	
	/**
	 * Removal behaviour of a bubble. If there's a call to {@link GridCell}'s
	 * chain() method, it means that it can potentially trigger a chainreaction.
	 * Whether or not that's the case, is determined by the chain method of the
`	 * next {@link Bubble}'s {@link BubbleBehaviour}.
	 * @param cell The {@link GridCell} which contains the {@link Bubble}.
	 * @return the amount of {@link Bubble}s removed.
	 */
	int remove(GridCell cell);

	/**
	 * Trigger this {@link BubbleBehaviour}. This is used for example when a
	 * {@link Projectile} collided, when it triggers its neighboring {@link GridCell}s.
	 * @param cell The {@link GridCell}l which contains the {@link Bubble}.
	 * @return the amount of {@link Bubble}s removed.
	 */
	int trigger(GridCell cell);
}
