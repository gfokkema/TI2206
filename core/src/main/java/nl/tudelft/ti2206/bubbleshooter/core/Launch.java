package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Launch extends Game {
	/**
	 * Initialize a spritebatch and bitmapfont for later use.
	 */
	public SpriteBatch batch;
	public BitmapFont font;
	Music BGM;
	private final String BGMname = "lol.ogg";
	
	/**
	 * Create the spritebatch and bitmapfont.
	 * Set the main-menu screen
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		// initial setup of first music
		BGM = Gdx.audio.newMusic(Gdx.files.internal(BGMname));
		this.BGM.setVolume(0.5f);
		this.BGM.setLooping(true);
		this.setScreen(new MainMenuScreen(this, BGM));
		
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
