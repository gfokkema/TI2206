package nl.tudelft.ti2206.bubbleshooter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiPredicate;

import nl.tudelft.ti2206.bubbleshooter.Bubble.Orientation;
import nl.tudelft.ti2206.bubbleshooter.utils.DisjointSet;


/**
 * Represents the play field with all the bubbles.
 */
public class Board {
	private int width = 8, height = 20;
	private HashMap<Integer,Bubble> bubbles;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;

		bubbles = new HashMap<Integer,Bubble>(this.width * this.height);
		for (int i = 0; i < 40; i++) {
			bubbles.put(i, new Bubble());
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
    /**
     * Checks the bubble that gets shot, with all the other bubbles if there is a collision.
     * @param b - the bubble that gets shot.
     * @return A boolean gets returned depending if there was a collision.
     */
	public boolean collides(Bubble b) {
		// Get the Bubble's index on the hex grid
		// Check each Bubble around this bubble
		// Return true on collision
		//

		// int collision = 0;// kan ook met break
		// while(collision == 0){
		// Float bubbleX = b.getX();
		// Float bubbleY = b.getY();
		//
		// Float KleurBubble = bubbleArray.get(b);
		//
		//
		// Float stillBubbleX= getHexagonabove(bubbleX)
		// Float stillBubbleY=getHexagoneCoordinabove(bubbleY)
		//
		// if (!isEmpty(stillBubbleX && stillBubbleY){
		// if(hasHitThriceorMore(b)){
		// collision =1;//break;
		// destroy bubbles}
		// else{
		// collision =1;//break;
		// getStuck()}
		// }
		// else{
		// setBubbleX(directionX+1?) + setBubbleX(directionY+1?)
		// }

		for (Bubble c : bubbles.values()) {
			if (c.collides(b)) {
				return true;
			}
		}

		return false;
	}

	public void add(Bubble b, int i, int j) {
		// Add the Bubble to the list
		// Update the bounds of the circle
	}
	
	public Map<Integer, Bubble> getBubbles() {
		return bubbles;
	}

	/**
	 * Traversal to find all of the nodes that should be removed. If nothing
	 * should be removed, then nothing is returned.
	 * @param b - The bubble where it all starts.
	 * @return An Optional which represents nothing, or the List of nodes that
	 *         should be removed.
	 */
	public Optional<List<Bubble>> getNextRemoved(int id) {
		List<Bubble> result = new ArrayList<Bubble>();

		// Search for bubbles of the same color
		List<Bubble> sameColors = breadthFirst(
				id,
				(current, neighbor) -> bubbles.get(current).color == bubbles.get(neighbor).color,
				new DisjointSet(width*height)
		);

		if (sameColors.isEmpty()) {
			return Optional.empty();
		}
		result.addAll(sameColors);

		// Use the same DisjointSet for all the breadth-first searches
		DisjointSet ds = new DisjointSet(width * height);
		List<Bubble> connectedToCeiling = new ArrayList<Bubble>();
		for(int ceilingIndex = 0; ceilingIndex < width; ceilingIndex++) {
			if(!bubbles.containsKey(ceilingIndex)) {
				//There's no bubble here
				continue;
			}
			connectedToCeiling.addAll(breadthFirst(
					ceilingIndex,
					(current, neighbor) -> true,
					ds
			));
		}
		return null;
	}

	private List<Bubble> breadthFirst(Integer subject, BiPredicate<Integer, Integer> condition, DisjointSet ds) {
		Queue<Integer> q = new LinkedList<Integer>();
		List<Bubble> result = new ArrayList<Bubble>();

		q.add(subject);
		while(!q.isEmpty()) {
			int currentIndex = q.remove();
			for(Orientation o : Bubble.orientations) {
				int neighborIndex = o.fromIndex(currentIndex, this.width);
				if (neighborIndex < 0 || neighborIndex >= width*height){
					//Invalid index
					continue;
				}
				Bubble neighbor = bubbles.get(neighborIndex);
				if (neighbor == null) {
					//Continue, because there's no neighbor here
					continue;
				}
				if (!ds.connected(currentIndex, neighborIndex)) {
					if (condition.test(currentIndex, neighborIndex)) {
						ds.union(currentIndex, neighborIndex);
						q.add(neighborIndex);
						result.add(bubbles.get(neighborIndex));
					}
				}
			}
		}
		return result;
	}

	public void removeAll(List<Bubble> bs) {
		bubbles.values().removeAll(bs);
	}
}
