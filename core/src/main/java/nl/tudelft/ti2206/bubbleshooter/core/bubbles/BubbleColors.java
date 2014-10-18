package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import com.badlogic.gdx.graphics.Color;

/**
 * Enum of all the different possible colors.
 */
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