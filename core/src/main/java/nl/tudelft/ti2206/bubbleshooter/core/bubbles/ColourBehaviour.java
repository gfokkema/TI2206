package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

public class ColourBehaviour implements BubbleBehaviour {
	
	/**
	 * Get a {@link Collection} of all {@link Bubble} objects adjacent to the
	 * given {@link Bubble} that have the same color.
	 * 
	 * @param id
	 *            the grid id of the {@link Bubble}
	 * @return {@link Collection} of {@link Bubble} objects adjacent to id and
	 *         with the same color
	 */
	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		// Search for bubbles of the same color
		HashMap<Integer, Bubble> bubbleGroup = new HashMap<Integer, Bubble>();
		board.depthFirst(
				id,
				(current, neighbour) -> testColor(board, current, neighbour),
				bubbleGroup);
		bubbleGroup.put(id, board.getBubbles().get(id));
		return bubbleGroup.values();
	}
	
	/**
	 * Calls the other {@link #remove(Board, int)}
	 * Determines if the id is equal to the projectile.
	 */
	@Override
	public int remove(Board board, int id, int projectile) {
		if(id == projectile) return remove(board,projectile);
		else return 0;
	}
	
	/**
	 * Removes the group gotten from {@link #getGroup(Board, int)}.
	 * @param board the {@link Board} used.
	 * @param projectile the {@link Projectile} on the {@link Grid}.
	 * @return amount of {@link Bubble}s are removed.
	 */
	public int remove(Board board, int projectile) {
		Collection<Bubble> sameColors = getGroup(board, projectile);
		if (sameColors.size() >= 3) {
			return board.removeAll(sameColors);
		} else return 0;
	}
	
	/**
	 * Tests colors of the neighbouring {@link Bubble}s.
	 * @param board the {@link Board} used.
	 * @param current the current {@link Bubble}.
	 * @param neighbour the neighbouring {@link Bubble}.
	 * @return true if the current is equal to the neighbour.
	 */
	private boolean testColor(Board board, Integer current, Integer neighbour) {
		return board.getBubbles().get(current).getColor().equals(board.getBubbles().get(neighbour).getColor());
	}
}
