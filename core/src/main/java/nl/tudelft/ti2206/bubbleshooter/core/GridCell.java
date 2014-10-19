package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;

import com.badlogic.gdx.math.Circle;

public class GridCell implements Collidable {
	private Set<GridCell> neighbors;
	private Bubble bubble;
	private Circle bounds;
	
	public GridCell(Circle bounds) {
		this.neighbors = new HashSet<>();
		this.bounds = bounds;
	}
	
	@Override
	public boolean collides(Projectile p) {
		return bounds.overlaps(p.getBounds());
	}
	
	public void connect(GridCell c) {
		if (c != null && neighbors.add(c)) c.connect(this);
	}
	
	public Bubble getBubble() {
		return this.bubble;
	}

	public void setBubble(Bubble b) {
		this.bubble = b;
		b.setBounds(bounds);
	}

	public void removeBubble() {
		this.bubble = null;
	}

	public int chain() {
		if (!isOccupied()) return 0;
		return bubble.getBehaviour().chain(this);
	}

	public int remove() {
		if (!isOccupied()) return 0;
		return bubble.getBehaviour().remove(this);
	}

	public int trigger() {
		if (!isOccupied()) return 0;
		return bubble.getBehaviour().trigger(this);
	}

	public boolean isOccupied() {
		return bubble != null;
	}

	public void depthFirst(Collection<GridCell> acc) {
		depthFirst(acc, true);
	}

	public void depthFirst(Collection<GridCell> acc, boolean ignoreUnoccupied) {
		depthFirst(acc, bubble -> true, ignoreUnoccupied);
	}

	public void depthFirst(Collection<GridCell> acc, Predicate<Bubble> condition, boolean ignoreUnoccupied) {
		if (acc.contains(this)) 		return;
		if (ignoreUnoccupied && !isOccupied())
			return;
		if (!condition.test(bubble))	return;

		acc.add(this);
		forEachNeighbor(g -> g.depthFirst(acc, condition, ignoreUnoccupied));
	}

	public void forEachNeighbor(Consumer<? super GridCell> consumer) {
		neighbors.forEach(consumer);
	}

	public void triggerNeighbors() {
		forEachNeighbor(g -> g.trigger());
	}
}
