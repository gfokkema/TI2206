package nl.tudelft.ti2206.bubbleshooter;

import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bubble extends Sprite {
	public enum Orientation {
		NORTH_EAST, EAST, SOUTH_EAST, SOUTH_WEST, WEST, NORTH_WEST;

		/**
		 * Returns the opposite orientation, for example
		 * SOUTH_EAST.getOpposite() will return NORTH_EAST.
		 * 
		 * @return the opposite orientation
		 */
		public Orientation getOpposite() {
			return orientations[this.ordinal() + 3];
		}
	}

	private static final Orientation[] orientations = Orientation.values();

	public enum Color {
		RED(0xFF0000FF), GREEN(0x00FF00FF), BLUE(0x0000FFFF), PURPLE(0xFF00FFFF), YELLOW(
				0xFFFF00FF);

		protected int rgba;

		private Color(int rgba) {
			this.rgba = rgba;
		}
	}

	private Color color;
	private Bubble[] neighbors;
	private Circle bounds;

	public Bubble(Vector2 mid) {
		super(new Texture("Bubble-Blue.png"));
		bounds = new Circle(mid, 64);
		this.neighbors = new Bubble[6];
	}

	public Optional<Bubble> getNeighbor(Orientation dir) {
		return Optional.ofNullable(neighbors[dir.ordinal()]);
	}

	public void setNeighbor(Bubble b, Orientation dir) {
		neighbors[dir.ordinal()] = b;
	}

	/**
	 * Checks if the if the bubbles hit each other
	 * 
	 * @param b
	 *            - the bubble that gets shot
	 * 
	 * @return a boolean depending on the collide
	 */
	public boolean collides(Bubble b) {
		return bounds.overlaps(b.bounds);

	}

	@Override
	public void draw(Batch batch) {
		batch.draw(this.getTexture(), bounds.x, bounds.y);
	}
}
