package nl.tudelft.ti2206.bubbleshooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents the play field with all the bubbles.
 * @author skip
 *
 */
public class Board {
	private ArrayList<Bubble> bubbles;

	public Board(int width, int height) {
		bubbles = new ArrayList<Bubble>(width*height);
	}

	public boolean collides(Bubble b) {
		// Get the Bubble's index on the hex grid
		// Check each Bubble around this bubble
		// Return true on collision
		return true;
	}

	public void attach(Bubble b, int i, int j) {
		// Attach the Bubble to its neighbors.
		// Add the Bubble to the List.
		// Update the bounds of the Bubble.
	}

	/**
	 * Traversal to find all of the nodes that should be removed.
	 * If nothing should be removed, then nothing is returned.
	 * @param b - The bubble where it all starts.
	 * @return An Optional which represents nothing, or the
	 * 	List of nodes that should be removed.
	 */
	public Optional<List<Bubble>> getNextRemoved(Bubble b) {
		return null;
	}

	public void removeAll(List<Bubble> bs) {
		bubbles.removeAll(bs);
	}

	public void draw(SpriteBatch batch) {
		// Draw all the bubbles in the List
	}
}
