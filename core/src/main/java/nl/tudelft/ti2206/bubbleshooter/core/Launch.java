package nl.tudelft.ti2206.bubbleshooter.core;

import nl.tudelft.ti2206.bubbleshooter.audio.Assets;
import nl.tudelft.ti2206.bubbleshooter.audio.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.audio.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.audio.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.audio.SoundEngine;
import nl.tudelft.ti2206.bubbleshooter.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Initial launch class.
 * Sets up some objects for later use.
 * Goes directly to the main menu screen.
 * @author group-15
 *
 */
public class Launch extends Game {
	/**
	 * Initialize a spritebatch and bitmapfont for later use.
	 * Assign the initial music being used
	 */
	public Assets assets;
	public BitmapFont font;
	public MainMenuScreen mms;
	public SoundEngine engine;
	public SpriteBatch batch;
	
	/**
	 * Create the spritebatch and bitmapfont.
	 * Set the main-menu screen
	 */
	@Override
	public void create() {
		assets = new Assets();
		batch = new SpriteBatch();
		font = new BitmapFont();
		engine = new SoundEngine(assets);
		
		assets.load(MusicID.GAME, "BGMenu.ogg");
		assets.load(MusicID.MENU, "BGMenu.ogg");
		assets.load(SoundID.BUBBLE, "BubbleSFX.wav");
		assets.load(SoundID.BUTTON, "ButtonSFX.wav");
		
		assets.load(TextureID.BACKGROUND, "back_one_player.png");
		assets.load(TextureID.BUBBLE, "Bubble-Blue.png");
		assets.load(TextureID.CANNON, "cannon.png");
		assets.finish();
		
		mms = new MainMenuScreen(this);
		this.setScreen(mms);
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
