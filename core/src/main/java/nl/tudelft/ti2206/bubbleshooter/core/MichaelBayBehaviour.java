package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

public class MichaelBayBehaviour implements BubbleBehaviour {
	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		HashMap<Integer, Bubble> group = new HashMap<Integer, Bubble>();
		group.put(id, board.getBubbles().get(id));
		for(Entry<Integer, Bubble> b: board.getBubbles().entrySet()) {
			group.put(b.getKey(), b.getValue());
		}
		return group.values();
		
	}
	
	public Collection<Bubble> remove(Board board, int id, int projectile) {
		if(board.getGrid().adjacent(id, projectile)) {
			return getGroup(board, id);
		}
		return new ArrayList<Bubble>();
	}
}
