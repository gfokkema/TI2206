package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

public class ColourBehaviour implements BubbleBehaviour {
	
	/**
	 * Get a {@link Collection} of all {@link Bubble} objects adjacent to the
	 * given {@link Bubble} that have the same color.
	 * 
	 * @return {@link Collection} of {@link Bubble} objects adjacent to id and
	 *         with the same color
	 */
	@Override
	public int chain(GridCell cell) {
		// Search for bubbles of the same color
		HashMap<Integer, Bubble> bubbleGroup = new HashMap<Integer, Bubble>();
		// FIXME:
		//board.depthFirst(
		//		id,
		//		(current, neighbour) -> testColor(board, current, neighbour),
		//		bubbleGroup);
		bubbleGroup.put(id, board.getGrid().cells.get(id).getBubble());
		return bubbleGroup.values();
	}
	
	/**
	 * Calls the other {@link #remove(Board, int)}
	 * Determines if the id is equal to the projectile.
	 */
	@Override
	public int remove(GridCell cell) {
		if(id == projectile) return remove(board,projectile);
		else return 0;
	}
	
	/**
	 * Removes the group gotten from {@link #chain(GridCell)}.
	 * @param board the {@link Board} used.
	 * @param projectile the {@link Projectile} on the {@link Grid}.
	 * @return amount of {@link Bubble}s are removed.
	 */
	public int remove(Board board, int projectile) {
		Collection<Bubble> sameColors = chain(null);
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
		// FIXME:
		HashMap<Integer, GridCell> c = board.getGrid().cells;
		return c.get(current).getBubble().getColor().equals(c.get(neighbour).getBubble().getColor());
	}
}
