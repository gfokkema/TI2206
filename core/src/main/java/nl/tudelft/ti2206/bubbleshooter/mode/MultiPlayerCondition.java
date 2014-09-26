package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public class MultiPlayerCondition implements EndingCondition {

	@Override
	public int check(BSMode mode) {
		
		if(mode.board.isEmpty()) return 1;
		
		HashMap<Integer, Bubble> bubbles = mode.board.getBubbles();
		int width = mode.board.getGrid().getWidth();
		int height = mode.board.getGrid().getHeight();
		
		int max = (int) ((2*width -1) * Math.floor(height/2));
		if (Math.floor(height/2) == height/2)
			width--;
		else
			max += width;
				
		for(int i = max-width; i <= max; i++)
			if(bubbles.containsKey(i)) return -1; 
		
		return 0;
	}

	@Override
	public void addStatsObserver(StatsObserver o) {
	}

}
