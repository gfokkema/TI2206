package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.math.Vector2;

/**
 * The {@link Board} class represents the playing field which contains all the
 * {@link Bubble} objects.
 */
public class Board extends BSDrawable implements Serializable, Collidable {
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
		Collection<BSDrawable> drawables = new ArrayList<BSDrawable>();
		drawables.add(this);
		grid.cells.values().forEach((GridCell c) -> {
			if (c.isOccupied()) drawables.add(c.getBubble());
		});
		return drawables;
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
	public boolean collides(Projectile p) {
		if (grid.collides(p)) return true;
		
		if (p.getBounds().y + 16 > 480) return true;
		if (p.getBounds().x - 16 < 32
				|| p.getBounds().x - 16 > grid.getWidth() * 32) {
			Vector2 dir = p.getDirection();
			dir.x = -dir.x;
		}
		return false;
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
