package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class ColourBubble extends Bubble{
	private static final long serialVersionUID = 746300724926407976L;

	public enum BubbleColors {
		RED(Color.RED),
		GREEN(Color.GREEN),
		BLUE(Color.BLUE),
		PURPLE(Color.PURPLE),
		YELLOW(Color.YELLOW),
		ORANGE(Color.ORANGE),
		PINK(Color.PINK),
		WHITE(Color.WHITE),
		
		GRAY(Color.GRAY),
		MAGENTA(Color.MAGENTA),
		CYAN(Color.CYAN),
		MAROON(Color.MAROON),
		NAVY(Color.NAVY),
		OLIVE(Color.OLIVE);
		
		private Color color;
		private BubbleColors(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
	}
	
	protected Color colour;
	
	public ColourBubble() {
		super(new ColourBehaviour());
		this.colour = getRandomColor(BubbleColors.values().length -1);
	}
	
	public ColourBubble(int colourLimit) {
		super(new ColourBehaviour());
		this.colour = getRandomColor(colourLimit);
	}
		
	/**
	 * Instantiate a new Bubble, with the given color.
	 * This function is for testing purposes, therefore
	 * it's protected and can only be used in the same package.
	 * @param c - the Color of the Bubble.
	 */
	public ColourBubble(Color colour) {
		super(new ColourBehaviour());
		this.colour = colour;
	}
		
	/**
	 * Pick a ColorValue at random.
	 * @return a randomly chosen ColorValue.
	 */
	protected Color getRandomColor(int colourLimit) {
		BubbleColors[] colors = BubbleColors.values();
		return colors[(new Random()).nextInt(colourLimit)].getColor();
	}
	
	/**
	 * Return the color of this {@link Bubble}.
	 * @return color of this {@link Bubble}.
	 */
	public Color getColor() {
		return colour;
	}
	
}
