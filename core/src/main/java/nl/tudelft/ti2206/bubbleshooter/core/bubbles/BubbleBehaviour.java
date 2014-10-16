package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

public interface BubbleBehaviour {	
	Collection<Bubble> getGroup(Board board, int id);
	
	Collection<Bubble> remove(Board board, int id, int projectile);
}
