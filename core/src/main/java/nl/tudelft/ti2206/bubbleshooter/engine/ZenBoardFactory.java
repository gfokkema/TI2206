package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;

public class ZenBoardFactory extends BoardFactory {
	@Override
	public List<Grid> makeLevels() {
		Grid g = new Grid("Zen", 8, 15);
		List<Grid> grids = new ArrayList<>();
		grids.add(g);
		
		for (int i = 0; i < 40; i++) {
			add(g, new ColourBubble(), i);
		}
		
		return grids;
	}
	
}
