package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.BomBubble;
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
	
	protected Bubble parse(String bubble) {
		if (bubble.equals("--")) return null;
		
		int value = Integer.parseInt(bubble);
		switch(value) {
			case 0:	case 1:	case 2:	case 3:	case 4: return new Bubble(BubbleColors.values()[value].getColor());
			case 5: return new Bubble();
			case 6: return new StoneBubble();
			case 7: return new WildcardBubble();
			case 8: return new BomBubble();
			case 9: return new MichaelBayBubble();
		}
		//unknown bubble
		return null;
	}
		
	/**
	 * Add a bubble to a board with the specified index.
	 * This method can be overridden by subclasses to add factory specific behaviour.
	 * @param board		the Board to add the Bubble to
	 * @param bubble	The Bubble to add to the Board
	 * @param i			the x-coordinate in the Grid
	 * @param j			the y-coordinate in the Grid
	 */

	protected void add(Board board, Bubble b, int i, int j) {
		board.add(b, i, j);
	}
	
	protected void add(Board board, int idx) {
		board.add(new Bubble(), idx);
	}
}
