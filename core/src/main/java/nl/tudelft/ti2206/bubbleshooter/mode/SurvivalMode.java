package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;

public class SurvivalMode extends SinglePlayerMode{
	private int shotcounter;	
	
	public SurvivalMode(EndingCondition end, BoardFactory factory) {
		super(end, factory);
		this.shotcounter = 1;
	}

	@Override
	public void insertRows(int row) {
		if(shotcounter >= 5) {
			grid.insertRows(row);
			for (int i = 0; i < grid.getGridWidth() * 2 - 1; i++) {
				grid.add(new ColourBubble(4), i);
			}
			shotcounter = 1;
		} else shotcounter ++;
	}
	
}
