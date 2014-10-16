package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

public class StoneBehaviour implements BubbleBehaviour {
	@Override
	public Collection<Bubble> getGroup(Board board, int id) {
		return null;
	}

	@Override
	public int remove(Board board, int id, int projectile) {
		return 3;
	}
}
