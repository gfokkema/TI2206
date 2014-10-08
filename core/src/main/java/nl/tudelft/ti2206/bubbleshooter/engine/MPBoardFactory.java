package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.IOException;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;


public class MPBoardFactory extends FileBoardFactory {
	@Override
	public List<Board> makeLevels() {
		List<Board> boards = null; 
		try {
			boards = parseFile("levels/mpboard.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boards;
	}
}
