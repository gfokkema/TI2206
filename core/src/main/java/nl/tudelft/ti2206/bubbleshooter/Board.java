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

	public Optional<List<Bubble>> collides(Bubble b) {
		// Get the Bubble's index on the hex grid
		// Check each Bubble around this bubble
		// Return a list of all bubbles that it collided with
		return Optional.of(new ArrayList<Bubble>());
	}

	public void add(Bubble b, int i, int j) {
		// Add the bubble to the arraylist
		// Update the bounds of the Bubble
		// Add neighbors to the Bubble
	}

	public void draw(SpriteBatch batch) {
		// Draw all the bubbles in the arraylist
	}
}
