package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.IOException;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

public class ArcadeBoardFactory extends FileBoardFactory {
	@Override
	public List<Board> makeLevels() {
		List<Board> boards = null; 
		try {
			boards = parseFile("levels/arcadeboard.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boards;
	}
}
