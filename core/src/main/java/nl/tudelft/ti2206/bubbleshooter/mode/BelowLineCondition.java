package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Bubble;

/**
 * The Single-Player {@link EndingCondition}.
 * @author group-15
 *
 */
public class BelowLineCondition extends EndingDecorator {

	public BelowLineCondition(EndingCondition end) {
		super(end);
	}

	/**
	 * The actual {@link EndingCondition} for the Single-Player mode.
	 */
	@Override
	public int check(BSMode mode) {
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
		
		return super.check(mode);
	}

}
