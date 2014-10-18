package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

/**
 * This class describes the {@link BubbleBehaviour} of the {@link MichaelBayBubble}.
 * When the {@link MichaelBayBubble} is hit, all {@link Bubble}s are removed.
 * This results into passing on to the next level immediately.
 * @author group-15
 *
 */
public class MichaelBayBehaviour implements BubbleBehaviour {
	
	/**
	 * Gets the {@link Collection<{@link Bubble}>} of {@link Bubble}s to be removed.
	 * @return the {@link Collection<{@link Bubble}>} of all bubbles.
	 */
	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		HashMap<Integer, Bubble> group = new HashMap<Integer, Bubble>();
		// FIXME:
		//group.put(id, board.getBubbles().get(id));
		//for(Entry<Integer, Bubble> b: board.getBubbles().entrySet()) {
			//group.put(b.getKey(), b.getValue());
		//}
		return group.values();
		
	}
	
	/**
	 * Removes all the {@link Bubble}s.
	 */
	@Override
	public int remove(Board board, int id, int projectile) {
		if(board.getGrid().adjacent(id, projectile)) {
			return 3 * board.removeAll(getGroup(board, id));
		}
		return 0;
	}
}
