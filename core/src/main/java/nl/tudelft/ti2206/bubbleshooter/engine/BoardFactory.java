package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;

/**
 * This is the abstract factory for our Grids.
 */
public abstract class BoardFactory {
	public abstract Iterator<Grid> makeLevels();
		
	/**
	 * Add a bubble to a grid with the specified index.
	 * This method can be overridden by subclasses to add factory specific behaviour.
	 * @param grid		the Grid to add the Bubble to
	 * @param b			The Bubble to add to the Grid
	 * @param i			the x-coordinate in the Grid
	 * @param j			the y-coordinate in the Grid
	 */
	protected void add(Grid grid, Bubble b, int i, int j) {
		grid.add(b, i, j);
	}
	
	/**
	 * Add a bubble to a grid with the specified index.
	 * This method can be overridden by subclasses to add factory specific behaviour.
	 * @param grid		the Grid to add the Bubble to
	 * @param b			The Bubble to add to the Grid
	 * @param idx		the idx in the Grid
	 */
	protected void add(Grid grid, Bubble b, int idx) {
		grid.add(b, idx);
	}
}
