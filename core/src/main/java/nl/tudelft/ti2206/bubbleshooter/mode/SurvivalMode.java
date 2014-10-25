package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;

public class SurvivalMode extends SinglePlayerMode{
	private int shotcounter;	
	
	public SurvivalMode(EndingCondition end, Iterator<Grid> grids, Score score) {
		super(end, grids, score);
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
