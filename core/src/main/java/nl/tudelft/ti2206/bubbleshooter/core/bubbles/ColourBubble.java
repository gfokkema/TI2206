package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.ArrayList;
import java.util.Random;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

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
	protected Color color;
	protected int colour;
	
	
	
	/**
	 * ColourBubble constructor.
	 */
	@SuppressWarnings("static-access")
	public ColourBubble() {
		super(new ColourBehaviour());
		
		this.color = getRandomColor(BubbleColors.values().length -1);
		colour = color.rgba8888(color);
	}
	
	/**
	 * Secondary ColorBubble constructor.
	 * @param colourLimit the amount of colors a bubble may be.
	 */
	public ColourBubble(int colourLimit) {
		super(new ColourBehaviour());
		this.color = getRandomColor(colourLimit);
		colour = color.rgba8888(color);
	}
	
	public ColourBubble(ArrayList<Color> colours) {
		super(new ColourBehaviour());
		this.color = getRandomColor(colours);
		colour = color.rgba8888(color);
	}
		
	/**
	 * Instantiate a new Bubble, with the given color.
	 * This function is for testing purposes, therefore
	 * it's protected and can only be used in the same package.
	 * @param c - the Color of the Bubble.
	 */
	public ColourBubble(Color colour) {
		super(new ColourBehaviour());
		this.colour = colour.rgb888(colour);
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
		return colours.get(new Random().nextInt(colours.size()));
	}
	
	/**
	 * Return the color of this {@link Bubble}.
	 * @return color of this {@link Bubble}.
	 */
	@Override
	public Color getColor() {
		return color;
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
