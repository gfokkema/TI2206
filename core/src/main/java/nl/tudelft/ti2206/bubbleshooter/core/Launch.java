package nl.tudelft.ti2206.bubbleshooter.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Launch extends Game {
	
	/**
	 * Initialize a spritebatch and bitmapfont for later use.
	 */
	SpriteBatch batch;
	BitmapFont font;
	
	/**
	 * Create the spritebatch and bitmapfont.
	 * Set the main-menu screen
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}
	
	/**
	 * Render the screen.
	 */
	@Override
	public void render() {
		// render the screen from the create method
		super.render();
	}
	
	/**
	 * Clean up afterwards.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
