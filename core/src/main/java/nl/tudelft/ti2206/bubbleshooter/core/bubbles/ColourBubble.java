package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.ArrayList;
import java.util.Random;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * The 'regular' bubbles that only possess a color.
 * They come in different flavors:
 * - Red 
 * - Blue
 * - Green
 * - Yellow
 * - Pink
 * @author group-15
 *
 */
public class ColourBubble extends Bubble{
	private static final long serialVersionUID = 746300724926407976L;

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
	
	protected Color colour;
	
	/**
	 * ColourBubble constructor.
	 */
	public ColourBubble() {
		super(new ColourBehaviour());
		this.colour = getRandomColor(BubbleColors.values().length -1);
	}
	
	/**
	 * Secondary ColorBubble constructor.
	 * @param colourLimit the amount of colors a bubble may be.
	 */
	public ColourBubble(int colourLimit) {
		super(new ColourBehaviour());
		this.colour = getRandomColor(colourLimit);
	}
	
	public ColourBubble(ArrayList<Color> colours) {
		super(new ColourBehaviour());
		this.colour = getRandomColor(colours);
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
	
	protected Color getRandomColor(ArrayList<Color> colours) {
		Gdx.app.log("colours", colours.toString());
		return colours.get(new Random().nextInt(colours.size()));
	}
	
	/**
	 * Return the color of this {@link Bubble}.
	 * @return color of this {@link Bubble}.
	 */
	@Override
	public Color getColor() {
		return colour;
	}
	
	/**
	 * Returns the texture of this {@link Bubble}.
	 * @return {@link #Texture} of this {@link Bubble}.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.BUBBLE;
	}
	
}
