package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;

public class GridCell {
	private ArrayList<GridCell> neighbors;
	private Bubble bubble;
	
	public GridCell() {
		neighbors = new ArrayList<>();
	}
	
	public Bubble getBubble() {
		return this.bubble;
	}

	public boolean isOccupied() {
		return bubble != null;
	}

	public Collection<Bubble> getBubbles(Collection<Bubble> acc) {
		ArrayList<Bubble> result = new ArrayList<Bubble>();
		this.depthFirst(result);
		return result;
	}

	public void depthFirst(Collection<Bubble> acc) {
		if (acc.contains(bubble)) 	return;
		if (!isOccupied())			return;
		ArrayList<Bubble> result = new ArrayList<Bubble>();
		result.add(this.bubble);
		this.neighbors.forEach(g -> g.depthFirst(acc));
	}
}
