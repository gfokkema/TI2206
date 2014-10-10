package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

public class MichaelBayBehaviour extends BubbleBehaviour {

	public MichaelBayBehaviour(Bubble bubble) {
		super(bubble);
	}

	
	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		HashMap<Integer, Bubble> group = new HashMap<Integer, Bubble>();
		group.put(id, board.getBubbles().get(id));
		for(Entry<Integer, Bubble> b: board.getBubbles().entrySet()) {
			group.put(b.getKey(), b.getValue());
		}
		return group.values();
		
	}
}
