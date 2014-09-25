package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public class MultiPlayerCondition implements EndingCondition {

	@Override
	public int check(BSMode mode) {
		
		if(mode.board.isEmpty()) return -1;
		
		HashMap<Integer, Bubble> bubbles = mode.board.getBubbles();
		int max = (mode.board.getGrid().getWidth()-1)* mode.board.getGrid().getHeight() - 1;
		
		for(int i = max-mode.board.getGrid().getWidth(); i <= max; i++)
			if(bubbles.containsKey(i)) return -1; 
		
		return 0;
	}

	@Override
	public void addStatsObserver(StatsObserver o) {
	}

}
