package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;

/**
 * The Single-Player {@link EndingCondition}.
 * @author group-15
 *
 */
public class BelowLineCondition extends EndingDecorator {
	private static final long serialVersionUID = -4235795766520890630L;

	public BelowLineCondition(EndingCondition end) {
		super(end);
	}

	/**
	 * The actual {@link EndingCondition} for the Single-Player mode.
	 */
	@Override
	public void check(Board board) {
		HashMap<Integer, Bubble> bubbles = board.getBubbles();
		
		int width = board.getGrid().getWidth();
		int height = board.getGrid().getHeight();
		
		int max = (int) ((2*width -1) * Math.floor(height/2));
		if (Math.floor(height/2) == height/2)
			width--;
		else
			max += width;

		for(int i = max-width; i <= max; i++)
			if(bubbles.containsKey(i)) this.lost();
		
		super.check(board);
	}

}
