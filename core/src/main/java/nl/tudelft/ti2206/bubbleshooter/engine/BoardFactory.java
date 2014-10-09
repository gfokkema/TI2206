package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.MichaelBayBubble;
import nl.tudelft.ti2206.bubbleshooter.core.StoneBubble;
import nl.tudelft.ti2206.bubbleshooter.core.WildcardBubble;
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
		
		int value = Integer.parseInt(bubble);
		switch(value) {
			case 0:	case 1:	case 2:	case 3:	case 4: board.add(new Bubble(BubbleColors.values()[value].getColor()), i, j);
			case 5: board.add(new Bubble(), i, j);
			case 6: board.add(new StoneBubble(), i ,j);
			case 7: board.add(new WildcardBubble(), i ,j);
			case 8: board.add(new MichaelBayBubble(), i, j);
		}
	}
	
	protected void add(Board board, int idx) {
		board.add(new Bubble(), idx);
	}
}
