package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.io.Serializable;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;

/**
 * The {@link Bubble} class is an abstraction of {@link Bubble}s used for the {@link Bubble}s on the play-field.
 * {@link Bubble}s on the field have an orientation (six directions - because of the hexagonal board).
 * Furthermore bubbles possess a position and bounding circle (the latter can be compared to a "hit-box").
 * @author group-15
 *
 */
public abstract class Bubble extends BSDrawable implements Serializable {
	private static final long serialVersionUID = -3647601554787437036L;
	
	/**
	 * Initialization of behaviour and bounds.
	 */
	transient BubbleBehaviour behaviour;

	/**
	 * Bubbles contain a {@link BubbleBehaviour} and bounds.
	 * @param b
	 */
	public Bubble(BubbleBehaviour b) {
		this.behaviour = b;
	}

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
