package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;


public class MPBoardFactory extends FileBoardFactory {
	@Override
	public Iterator<Grid> makeLevels() {
		List<Grid> grids = null; 
		try {
			grids = parseFile("levels/mpboard.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return grids.iterator();
	}
}
