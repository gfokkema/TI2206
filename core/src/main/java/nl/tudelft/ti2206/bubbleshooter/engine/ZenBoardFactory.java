package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;

public class ZenBoardFactory extends BoardFactory {
	@Override
	public Iterator<Grid> makeLevels() {
		
		return new Iterator<Grid>() {
			@Override
			public boolean hasNext() {
				return true;
			}
			
			@Override
			public Grid next() {
				Grid g = new Grid("Zen", 8, 15);
				for (int i = 0; i < 40; i++) {
					add(g, new ColourBubble(4), i);
				}
				return g;
			}
		};
	}
}
