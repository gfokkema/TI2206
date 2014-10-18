package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

/**
 * This class describes the {@link BubbleBehaviour} of the {@link BomBubble}.
 * All adjacent {@link Bubble}s to the {@link BomBubble} will be removed when hit.
 * @author group-15
 *
 */
public class BomBehaviour implements BubbleBehaviour{
	
	/**
	 * Gets the {@link Collection<{@link Bubble}>} of {@link Bubble}s to be removed.
	 * @return the {@link Collection<{@link Bubble}>} of all adjacent bubbles.
	 */
	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		HashMap<Integer, Bubble> group = new HashMap<Integer, Bubble>();
		// FIXME: make this work again
		//group.put(id, board.getBubbles().get(id));
		//for(Entry<Integer, Bubble> b: board.getBubbles().entrySet()) {
		//	if(board.getGrid().adjacent(b.getKey(), id)) group.put(b.getKey(), b.getValue());
		//}
		return group.values();
	}
	
	/**
	 * Removes the {@link Bubble}s caught within the blast of the {@link BomBubble}.
	 */
	@Override
	public int remove(Board board, int id, int projectile) {
		if(board.getGrid().adjacent(id, projectile)) {
			return 3 * board.removeAll(getGroup(board,id));
		}
		return 0;
	}
}
