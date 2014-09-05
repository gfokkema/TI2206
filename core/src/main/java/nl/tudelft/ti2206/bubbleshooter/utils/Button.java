package nl.tudelft.ti2206.bubbleshooter.utils;

import nl.tudelft.ti2206.bubbleshooter.screens.MainMenuScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	private Texture tex;
	private Sprite sprite;
	private BitmapFont font;
	private String text;
	private CallBack func;

	/**
	 * Functional interface which represents a callback function.
	 * This is called in {@link MainMenuScreen}
	 * @author skip
	 *
	 */
	public interface CallBack {
		public void apply();
	}

	public Button(Rectangle bounds, Color color, BitmapFont font, String text, CallBack func) {
		this.func = func;
		this.font = font;
		this.text = text;
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
		func.apply();
		//TODO: Play click sound
	}

	/**
	 * Draws the button and the text
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		font.draw(batch, text, sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
	}
}
