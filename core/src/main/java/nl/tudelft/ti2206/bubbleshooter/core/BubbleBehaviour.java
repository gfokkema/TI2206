package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;
import java.util.HashMap;

public interface BubbleBehaviour {
	
	Collection<Bubble> getGroup(Board board, int id);
	
	Collection<Bubble> remove(Board board, int id, int projectile);
}
