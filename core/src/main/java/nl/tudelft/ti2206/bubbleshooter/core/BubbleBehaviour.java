package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;
import java.util.HashMap;

public class BubbleBehaviour {
	public boolean Group(Board board, Integer current, Integer neighbour) {
		return board.getBubbles().get(current).getColor().equals(board.getBubbles().get(neighbour).getColor());
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
		HashMap<Integer, Bubble> bubbleGroup = new HashMap<Integer, Bubble>();
		board.depthFirst(
				id,
				(current, neighbour) -> this.Group(board, current, neighbour), bubbleGroup);
		bubbleGroup.put(id, board.getBubbles().get(id));
		return bubbleGroup.values();
	}
	
	public Collection<Bubble> remove(Board board, int id, int projectile) {
		if(id == projectile) return remove(board,projectile);
		else return null;
	}
	
	public Collection<Bubble> remove(Board board, int projectile) {
		Collection<Bubble> sameColors = getGroup(board, projectile);
		if (sameColors.size() >= 3) {
			return sameColors;
		} else return null;
	}
}
