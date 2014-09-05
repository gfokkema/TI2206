package nl.tudelft.ti2206.bubbleshooter.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	private Texture tex;
	private Sprite sprite;
	private CallBack func;

	/**
	 * Functional interface which represents a callback function.
	 *
	 * @author skip
	 *
	 */
	public interface CallBack {
		public void apply();
	}

	public Button(Rectangle bounds, Color color, CallBack func) {
		this.func = func;
		Pixmap button_pixels = new Pixmap((int)bounds.width, (int)bounds.height, Pixmap.Format.RGBA8888);
		button_pixels.setColor(color);
		button_pixels.fill();
		tex = new Texture(button_pixels);
		button_pixels.dispose();
		sprite = new Sprite(tex);
		sprite.setPosition(bounds.x, bounds.y);
	}

	public boolean hit(int x, int y) {
		return sprite.getBoundingRectangle().contains(x, y);
	}

	/**
	 * Apply the callback function
	 */
	public void apply() {
		func.apply();
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
}
