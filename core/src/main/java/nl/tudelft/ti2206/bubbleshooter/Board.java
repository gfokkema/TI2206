package nl.tudelft.ti2206.bubbleshooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the play field with all the bubbles.
 */
public class Board extends Sprite {
	private ArrayList<Bubble> bubbles;
	private int width, height;

	public Board(int width, int height) {
		super(new Texture("back_one_player.png"));
		this.width = width;
		this.height = height;

		bubbles = new ArrayList<Bubble>(width * height);
		bubbles.add(new Bubble(new Vector2(100, 400)));
		bubbles.add(new Bubble(new Vector2(164, 400)));
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

		for (Bubble c : bubbles) {
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

	public void attach(Bubble b, int i, int j) {
		// Attach the Bubble to its neighbors.
	}

	/**
	 * Traversal to find all of the nodes that should be removed. If nothing
	 * should be removed, then nothing is returned.
	 * @param b - The bubble where it all starts.
	 * @return An Optional which represents nothing, or the List of nodes that
	 *         should be removed.
	 */
	public Optional<List<Bubble>> getNextRemoved(Bubble b) {
		return null;
	}

	public void removeAll(List<Bubble> bs) {
		bubbles.removeAll(bs);
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
		bubbles.forEach((Bubble b) -> b.draw(batch));
	}
}
