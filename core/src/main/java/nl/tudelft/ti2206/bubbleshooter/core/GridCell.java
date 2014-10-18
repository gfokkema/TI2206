package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

	public boolean isOccupied() {
		return bubble != null;
	}

	public void depthFirst(Collection<Bubble> acc) {
		if (acc.contains(bubble)) 	return;
		if (!isOccupied())			return;
		
		ArrayList<Bubble> result = new ArrayList<Bubble>();
		result.add(this.bubble);
		this.neighbors.forEach(g -> g.depthFirst(acc));
	}
	
	public Collection<Bubble> getBubbles(Collection<Bubble> acc) {
		ArrayList<Bubble> result = new ArrayList<Bubble>();
		this.depthFirst(result);
		return result;
	}
	
	public void setBubble(Bubble b) {
		this.bubble = b;
	}
}