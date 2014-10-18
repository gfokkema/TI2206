package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

/**
 * This class describes the {@link BubbleBehaviour} of the {@link StoneBubble}.
 * When the {@link StoneBubble} is hit, nothing really happens.
 * This means it can be only removed if it is removed by disconneting a group.
 * @author group-15
 *
 */
public class StoneBehaviour implements BubbleBehaviour {
	
	/**
	 * Gets the {@link Collection<{@link Bubble}>} of {@link Bubble}s to be removed.
	 * @return null no bubbles should be removed.
	 */
	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		return null;
	}

	/**
	 * Removes nothing.
	 */
	@Override
	public int remove(Board board, int id, int projectile) {
		return 0;
	}
}
