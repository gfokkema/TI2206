package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;

public class BomBehaviour extends BubbleBehaviour{

	public BomBehaviour(Bubble bubble) {
		super(bubble);
	}

	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		Gdx.app.log("hoi", "");
		HashMap<Integer, Bubble> group = new HashMap<Integer, Bubble>();
		group.put(id, board.getBubbles().get(id));
		for(Entry<Integer, Bubble> b: board.getBubbles().entrySet()) {
			Gdx.app.log("group", group.toString());
			if(board.getGrid().adjacent(b.getKey(), id)) group.put(b.getKey(), b.getValue());
		}
		return group.values();
		
	}
	
	@Override
	public Collection<Bubble> remove(Board board, int id, int projectile) {
		HashMap<Integer, Bubble> bomBubble = board.getColourGroup(bubble);
		Collection<Bubble> remove = new ArrayList<Bubble>();
		for(Entry<Integer, Bubble> b: bomBubble.entrySet()) {
			if(board.getGrid().adjacent(b.getKey(), projectile)) {
				remove.addAll(getGroup(board, b.getKey()));
			}
		}
		
		return remove;
	}
}
