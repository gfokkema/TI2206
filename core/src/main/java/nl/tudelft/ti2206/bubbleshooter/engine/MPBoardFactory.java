package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;

/**
 * This class creates an {@link MPBoardFactory} using the internal file "levels/mpboard.txt". 
 */
public class MPBoardFactory extends FileBoardFactory {
	/**
	 * Parses the internal file and creates an iterator over all {@link Grid}s that were parsed and created.
	 * @return	iterator over all {@link Grid}s
	 */
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
