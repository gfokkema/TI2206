package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.math.Circle;

/**
 * This class represents a cell in the {@link Grid}.
 * A {@link GridCell} has a specified size and collision area,
 * and it can contain a {@link Bubble}.
 * The {@link GridCell} also knows who it's neighbors are.
 */
public class GridCell extends BSDrawable implements Collidable {
	private static final long serialVersionUID = 4801310405424982822L;
	private Set<GridCell> neighbors;
	private Bubble bubble;
	private Circle bounds;
	
	/**
	 * Constructs a {@link GridCell} with a specified bounding area.
	 * @param bounds	{@link Circle} representing bounding area
	 */
	public GridCell(Circle bounds) {
		this.neighbors = new HashSet<>();
		this.bounds = bounds;
	}
	
	@Override
	public boolean collides(Projectile p) {
		return bounds.overlaps(p.getBounds());
	}
	
	/**
	 * This method connects a {@link GridCell} to it's given neighbor.
	 * @param c		the {@link GridCell} to connect
	 */
	public void connect(GridCell c) {
		if (c != null && neighbors.add(c)) c.connect(this);
	}
	
	/**
	 * This method returns the {@link Bubble} contained in this {@link GridCell}.
	 * @return		the contained {@link Bubble}
	 */
	public Bubble getBubble() {
		return this.bubble;
	}
	
	/**
	 * This method returns the neighboring {@link GridCell}s of this {@link GridCell}.
	 * @return		the neighboring {@link GridCell}s
	 */
	public Set<GridCell> getNeighbors() {
		return neighbors;
	}
 
	/**
	 * This method sets the {@link Bubble} contained in this {@link GridCell}.
	 * @param b		the {@link Bubble} that should be contained
	 */
	public void setBubble(Bubble b) {
		this.bubble = b;
		b.setBounds(bounds);
	}

	/**
	 * This method removes the {@link Bubble} contained in this {@link GridCell}.
	 */
	public void removeBubble() {
		this.bubble = null;
	}

	/**
	 * This method continues the chaining of {@link Bubble} removal, which was started from another {@link GridCell}.
	 * @return	the score associated with this removal
	 */
	public int chain() {
		if (!isOccupied()) return 0;
		return bubble.getBehaviour().chain(this);
	}

	/**
	 * This method initiates the chaining of {@link Bubble} removal starting from this {@link GridCell}.
	 * @return	the score associated with this removal
	 */
	public int remove() {
		if (!isOccupied()) return 0;
		
		int score = bubble.getBehaviour().remove(this);
		setChanged();
		notifyObservers("You have scored " + score + " points!");
		return score;
	}

	/**
	 * Trigger the {@link Bubble} in this {@link GridCell}.
	 * @return	the score associated with this removal
	 */
	public int trigger() {
		if (!isOccupied()) return 0;
		return bubble.getBehaviour().trigger(this);
	}

	/**
	 * This method checks whether this {@link GridCell} contains a {@link Bubble} or not.
	 * @return	true if there's a {@link Bubble}, false otherwise
	 */
	public boolean isOccupied() {
		return bubble != null;
	}

	/**
	 * This method initiates a depthFirst search for {@link GridCell}s
	 * containing {@link Bubble}s starting from this {@link GridCell}.
	 * @param acc	the accumulator for the search
	 */
	public void depthFirst(Collection<GridCell> acc) {
		depthFirst(acc, true);
	}

	/**
	 * This method initiates a depthFirst search for {@link GridCell}s
	 * containing {@link Bubble}s starting from this {@link GridCell}.
	 * It also accepts a boolean to indicate whether unoccupied cells
	 * are to be ignored
	 * @param acc				the accumulator for the search
	 * @param ignoreUnoccupied	ignore unoccupied cells
	 */
	public void depthFirst(Collection<GridCell> acc, boolean ignoreUnoccupied) {
		depthFirst(acc, bubble -> true, ignoreUnoccupied);
	}

	/**
	 * This method initiates a depthFirst search for {@link GridCell}s
	 * containing {@link Bubble}s starting from this {@link GridCell}.
	 * This method also accepts a grouping condition.
	 * @param acc				the accumulator for the search
	 * @param condition			the condition that should be matched
	 * @param ignoreUnoccupied	ignore unoccupied cells
	 */
	public void depthFirst(Collection<GridCell> acc, Predicate<Bubble> condition, boolean ignoreUnoccupied) {
		if (acc.contains(this)) 		return;
		if (ignoreUnoccupied && !isOccupied())
			return;
		if (!condition.test(bubble))	return;

		acc.add(this);
		forEachNeighbor(g -> g.depthFirst(acc, condition, ignoreUnoccupied));
	}

	/**
	 * This method executes an action for each neighbor it has.
	 * @param consumer	the action that should be executed
	 */
	public void forEachNeighbor(Consumer<? super GridCell> consumer) {
		neighbors.forEach(consumer);
	}

	/**
	 * Trigger the neighboring bubbles (used upon collision).
	 */
	public int triggerNeighbors() {
		int score = 0;
		for (GridCell g : neighbors) {
			score += g.trigger();
		}
		return score;
	}

	@Override
	public TextureID getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
