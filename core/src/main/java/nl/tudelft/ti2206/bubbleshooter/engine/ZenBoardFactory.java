package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;

public class ZenBoardFactory extends BoardFactory {
	@Override
	public List<Board> makeLevels() {
		Board b = new Board(8, 15);
		List<Board> boards = new ArrayList<>();
		boards.add(b);
		
		for (int i = 0; i < 40; i++) {
			add(b, new ColourBubble(), i);
		}
		
		return boards;
	}
	
}
