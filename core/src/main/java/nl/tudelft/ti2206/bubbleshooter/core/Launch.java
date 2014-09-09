package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Launch extends Game {
	/**
	 * Initialize a spritebatch and bitmapfont for later use.
	 */
	public SpriteBatch batch;
	public BitmapFont font;
	
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
	 * Clean up afterwards.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
