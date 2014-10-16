package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

/**
 * Interface of the behaviour of a {@link Bubble}
 * @author group-15
 *
 */
public interface BubbleBehaviour {	
	
	/**
	 * Get the group of {@link Bubble}s which are needed to be removed.
	 * @param board the {@link Board} used.
	 * @param id the id of the {@link Bubble} on the {@link Grid}.
	 * @return {@link Collection<{@link Bubble}>}
	 */
	Collection<Bubble> getGroup(Board board, int id);
	
	/**
	 * Removes the group of {@link Bubble}s gotten from {@link #getGroup(Board, int)}.
	 * @param board the {@link Board} used.
	 * @param id the id of the {@link Bubble} on the {@link Grid}.
	 * @param projectile the fired {@link Projectile} position on the {@link Grid}
	 * @return amount of {@link Bubble}s removed.
	 */
	int remove(Board board, int id, int projectile);
}
