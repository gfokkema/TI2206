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
 */
public class ColourBubble extends Bubble{
	private static final long serialVersionUID = 746300724926407976L;
	protected int colour;
	
	/**
	 * Creates a {@link ColourBubble} with a random color.
	 */
	public ColourBubble() {
		super(new ColourBehaviour());
		this.colour = Color.rgba8888(getRandomColor(BubbleColors.values().length - 1));
	}
	
	/**
	 * Creates a {@link ColourBubble} with a random color,
	 * chosen from the colours with an index below colourLimit.
	 * @param colourLimit	the maximum colourindex
	 */
	public ColourBubble(int colourLimit) {
		super(new ColourBehaviour());
		this.colour = Color.rgba8888(getRandomColor(colourLimit));
		
	}
	
	/**
	 * Creates a {@link ColourBubble} with a random color,
	 * chosen from the supplied list of colours.
	 * @param colours	a list of colours
	 */
	public ColourBubble(ArrayList<Color> colours) {
		super(new ColourBehaviour());
		this.colour = Color.rgba8888(getRandomColor(colours));
	}
		
	/**
	 * Creates a {@link ColourBubble} with the given color.
	 * @param colour	a colour
	 */
	public ColourBubble(Color colour) {
		super(new ColourBehaviour());
		this.colour = Color.rgba8888(colour);
	}
		
	/**
	 * Pick a ColorValue at random with an index lower than the specified limit.
	 * @param colourLimit	the index limit
	 * @return a randomly chosen ColorValue.
	 */
	protected Color getRandomColor(int colourLimit) {
		BubbleColors[] colors = BubbleColors.values();
		return colors[(new Random()).nextInt(colourLimit)].getColor();
	}
	
	/**
	 * Pick a ColorValue at random from all available colours.
	 * @return a randomly chosen ColorValue.
	 */
	protected Color getRandomColor(ArrayList<Color> colours) {
		return colours.get(new Random().nextInt(colours.size()));
	}
	
	/**
	 * Return the color of this {@link Bubble}.
	 * @return color of this {@link Bubble}.
	 */
	@Override
	public Color getColor() {
		return new Color(colour);
	}
	
	/**
	 * Returns the texture of this {@link Bubble}.
	 * @return {@link TextureID} of this {@link Bubble}.
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.BUBBLE;
	}
	
}
