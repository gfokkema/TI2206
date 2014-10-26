package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;

public class SurvivalMode extends SinglePlayerMode {
	private int shotcounter;	
	
	/**
	 * Initialize the counter to 1.
	 * @param end	the {@link EndingCondition}
	 * @param grids	the {@link Grid}s which we will cycle through
	 * @param score	the {@link Score} object for keeping track of scoring.
	 */
	public SurvivalMode(EndingCondition end, Iterator<Grid> grids, Score score) {
		super(end, grids, score);
		this.shotcounter = 1;
	}

	/**
	 * Insert 2 rows starting at the given index, when there has been 7 shots with the
	 * {@link Cannon}
	 * @param row	The row index where to start inserting rows.
	 */
	@Override
	public void insertRows(int row) {
		if(shotcounter >= 7) {
			grid.insertRows(row);
			for (int i = 0; i < grid.getGridWidth() * 2 - 1; i++) {
				grid.add(new ColourBubble(4), i);
			}
			shotcounter = 1;
		} else shotcounter ++;
	}
	
}
