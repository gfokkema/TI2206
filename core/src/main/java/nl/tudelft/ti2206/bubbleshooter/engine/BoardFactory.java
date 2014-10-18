package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
/**
 * This is the abstract factory for our Boards.
 */
public abstract class BoardFactory {
	public abstract List<Board> makeLevels();
		
	/**
	 * Add a bubble to a board with the specified index.
	 * This method can be overridden by subclasses to add factory specific behaviour.
	 * @param board		the Board to add the Bubble to
	 * @param b			The Bubble to add to the Board
	 * @param i			the x-coordinate in the Grid
	 * @param j			the y-coordinate in the Grid
	 */
	protected void add(Board board, Bubble b, int i, int j) {
		board.getGrid().add(b, i, j);
	}
	
	/**
	 * Add a bubble to a board with the specified index.
	 * This method can be overridden by subclasses to add factory specific behaviour.
	 * @param board		the Board to add the Bubble to
	 * @param b			The Bubble to add to the Board
	 * @param idx		the idx in the Grid
	 */
	protected void add(Board board, Bubble b, int idx) {
		board.getGrid().add(b, idx);
	}
	
}
