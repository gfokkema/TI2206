package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;
import java.util.HashMap;

public class BubbleBehaviour {

	private Bubble bubble;
	
	public BubbleBehaviour(Bubble bubble) {
		this.bubble = bubble;
	}
	
	public boolean Group(Board board, Integer current, Integer neighbour) {
		return board.getBubbles().get(current).getColor().equals(board.getBubbles().get(neighbour).getColor());
	}
}
