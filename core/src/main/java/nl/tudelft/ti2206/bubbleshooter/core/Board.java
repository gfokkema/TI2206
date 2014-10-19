package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

/**
 * The {@link Board} class represents the playing field which contains all the
 * {@link Bubble} objects.
 */
public class Board extends BSDrawable implements Serializable {
	private static final long serialVersionUID = -4815917036827285256L;
	private Grid grid;
	private HashMap<Integer, Bubble> bubbles;

	/**
	 * Constructs a {@link Board} that can hold {@link Bubble} objects. The
	 * {@link Board} represents the playing field.
	 * 
	 * @param width
	 *            the width of the board in bubbles
	 * @param height
	 *            the height of the board in bubbles
	 */
	public Board(int width, int height) {
		this.grid = new Grid(width, height);
	}
	
	public Collection<BSDrawable> getDrawables() {
		return grid.getDrawables();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Remove all the {@link Bubble}s that are both in the given
	 * {@link Collection} and on the grid.
	 * @param bs	The collection specifying the elements that should be removed.
	 * @return		the number of bubbles that have been removed
	 */
	public int removeAll(Collection<Bubble> bs) {
		if(bs == null || bs.isEmpty()) return 0;
		bubbles.values().removeAll(bs);
		setChanged();
		notifyObservers(bs.size() + " bubbles have been removed.");
		return bs.size();
	}
	
	@Override
	public TextureID getTexture() {
		return TextureID.BORDER;
	}

	@Override
	public int getWidth() {
		return 320;
	}

	@Override
	public int getHeight() {
		return 480;
	}

}
