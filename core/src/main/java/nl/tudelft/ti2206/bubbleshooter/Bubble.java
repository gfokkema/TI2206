package nl.tudelft.ti2206.bubbleshooter;

import java.util.Optional;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bubble extends Sprite {
	public enum Orientation {
		NORTH_EAST,
		EAST,
		SOUTH_EAST,
		SOUTH_WEST,
		WEST,
		NORTH_WEST;

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
	protected static Orientation[] orientations = Orientation.values();

	public enum ColorValue {
		RED(0xFF0000FF),
		GREEN(0x00FF00FF),
		BLUE(0x0000FFFF),
		PURPLE(0xFF00FFFF),
		YELLOW(0xFFFF00FF);

		protected int rgba;

		private ColorValue(int rgba) {
			this.rgba = rgba;
		}
	}
	protected static ColorValue[] colors = ColorValue.values();

	protected ColorValue color;
	protected Bubble[] neighbors;
	protected Circle bounds;

	public Bubble(Vector2 mid) {
		super(new Texture("Bubble-Blue.png"));
		this.bounds = new Circle();
		this.setPosition(mid.x, mid.y);
		this.neighbors = new Bubble[6];
		this.color = colors[getRandomColor()];
		this.setColor(new Color(color.rgba));
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
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.bounds.setPosition(x, y);
	}

	protected int getRandomColor() {
		Random r = getRNG();
		return r.nextInt(colors.length);
	}

	protected Random getRNG() {
		return new Random();
	}
}
