package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble.BubbleColors;

/**
 * This is the abstract factory for our Boards.
 */
public abstract class BoardFactory {
	public abstract List<Board> makeLevels();
		
	/**
	 * Add a bubble to a board with the specified index.
	 * This method can be overridden by subclasses to add factory specific behaviour.
	 * @param board		the Board to add the Bubble to
	 * @param bubble	The Bubble to add to the Board
	 * @param i			the x-coordinate in the Grid
	 * @param j			the y-coordinate in the Grid
	 */
	protected void add(Board board, String bubble, int i, int j) {
		if (bubble.equals("--")) return;
		
		int colorvalue = Integer.parseInt(bubble);
		board.add(new Bubble(BubbleColors.values()[colorvalue].getColor()), i, j);
	}
	
	protected void add(Board board, int idx) {
		board.add(new Bubble(), idx);
	}
}
