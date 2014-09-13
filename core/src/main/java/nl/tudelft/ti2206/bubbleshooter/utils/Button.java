package nl.tudelft.ti2206.bubbleshooter.utils;

import nl.tudelft.ti2206.bubbleshooter.SoundEffect;
import nl.tudelft.ti2206.bubbleshooter.screens.MainMenuScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * The button class is responsible for creating the buttons in the menu screens.
 * Clicking on a button will also play a sound effect.
 * @author group-15
 *
 */
public class Button {
	private Texture tex;
	private Sprite sprite;
	private BitmapFont font;
	private String text;
	private CallBack func;
	SoundEffect SFX;
	private final String SFXname = "ButtonSFX.wav";

	/**
	 * Functional interface which represents a callback function.
	 * This is called in {@link MainMenuScreen}
	 * 
	 */
	@FunctionalInterface
	public interface CallBack {
		public void apply();
	}

	public Button(Rectangle bounds, Color color, BitmapFont font, String text, CallBack func) {
		this.func = func;
		this.font = font;
		this.text = text;
		this.SFX = new SoundEffect(SFXname);
		// play once to initialize id, so the volume may be set.
		SFX.setVolume(0.5f);
		Pixmap button_pixels = new Pixmap((int)bounds.width, (int)bounds.height, Pixmap.Format.RGBA8888);
		button_pixels.setColor(color);
		button_pixels.fill();
		tex = new Texture(button_pixels);
		button_pixels.dispose();
		sprite = new Sprite(tex);
		sprite.setPosition(bounds.x, bounds.y);
	}

	/**
	 * Determines whether the button is hit at coordinates x and y.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean hit(int x, int y) {
		return sprite.getBoundingRectangle().contains(x, y);
	}

	/**
	 * Apply the callback function
	 */
	public void apply() {
		SFX.play();
		func.apply();
	}

	/**
	 * Draws the button and the text
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		font.draw(batch, text, sprite.getX(), sprite.getY());
	}
	
	/**
	 * Get the SFX of the button.
	 * @return
	 */
	public SoundEffect getSFX() {
		return SFX;
	}
}
