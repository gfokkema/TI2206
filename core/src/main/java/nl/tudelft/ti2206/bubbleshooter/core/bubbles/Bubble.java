package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.io.Serializable;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

/**
 * The {@link Bubble} class is an abstract base class for all {@link Bubble}s used on the playing field.
 * {@link Bubble}s on the field have an orientation (six directions - because of the hexagonal board).
 * {@link Bubble}s are either contained in a {@link GridCell}, or they are a {@link Projectile}.
 */
public abstract class Bubble extends BSDrawable implements Serializable {
	private static final long serialVersionUID = -3647601554787437036L;
	transient BubbleBehaviour behaviour;

	/**
	 * Creates a {@link Bubble} with the specified {@link BubbleBehaviour}.
	 * @param b	the specified {@link BubbleBehaviour}
	 */
	public Bubble(BubbleBehaviour b) {
		this.behaviour = b;
	}

	/**
	 * Returns the {@link BubbleBehaviour} of this {@link Bubble}.
	 * @return	the {@link BubbleBehaviour}
	 */
	public BubbleBehaviour getBehaviour() {
		return this.behaviour;
	}
	
	/**
	 * Returns the width of this {@link Bubble}.
	 * @return width of this {@link Bubble}.
	 */
	@Override
	public int getWidth() {
		return 32;
	}

	/**
	 * Returns the height of this {@link Bubble}.
	 * @return height of this {@link Bubble}.
	 */
	@Override
	public int getHeight() {
		return 32;
	}
}
