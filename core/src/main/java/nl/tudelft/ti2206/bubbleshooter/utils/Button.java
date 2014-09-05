package nl.tudelft.ti2206.bubbleshooter.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	Texture tex;
	Sprite sprite;

	public Button(Rectangle bounds, Color color) {
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

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
}
