package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;
import java.util.HashMap;

public class BubbleBehaviour {

	private Bubble bubble;
	
	public BubbleBehaviour(Bubble bubble) {
		this.bubble = bubble;
	}
	
	/**
	 * Get a {@link Collection} of all {@link Bubble} objects adjacent to the
	 * given {@link Bubble} that have the same color.
	 * 
	 * @param id
	 *            the grid id of the {@link Bubble}
	 * @return {@link Collection} of {@link Bubble} objects adjacent to id and
	 *         with the same color
	 */
	public Collection<Bubble> getGroup(Board board, int id) {
		// Search for bubbles of the same color
		HashMap<Integer, Bubble> sameColors = new HashMap<Integer, Bubble>();
		board.depthFirst(
				id,
				(current, neighbor) -> board.getBubbles().get(current).getColor().equals(board.getBubbles()
						.get(neighbor).getColor()), sameColors);
		sameColors.put(id, board.getBubbles().get(id));
		return sameColors.values();
	}
}
