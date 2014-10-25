package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SkinID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Settings;
import nl.tudelft.ti2206.bubbleshooter.logger.Logger;
import nl.tudelft.ti2206.bubbleshooter.logger.ScreenLogger;
import nl.tudelft.ti2206.bubbleshooter.score.FileHighscore;
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
public class BubbleShooter extends Game {
	/**
	 * Initialize a spritebatch and bitmapfont for later use.
	 * Assign the initial music being used
	 */
	public BitmapFont font;
	public FileHighscore scores;
	public MainMenuScreen mms;
	public SpriteBatch batch;

	/**
	 * Create the spritebatch and bitmapfont.
	 * Set the main-menu screen
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		Logger.getLogger().addLog(new ScreenLogger());
		Settings settings = Settings.getSettings();
		settings.addTheme("themes/space/");
		settings.addTheme("themes/dark/");
		loadAssets();

		scores = new FileHighscore();
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
	
	/**
	 * Loads the assets.
	 */
	public void loadAssets() {
		Assets assets = Assets.getAssets();
		assets.load(MusicID.GAME, "eerie.ogg");
		assets.load(MusicID.MENU, "BGMenu.ogg");
		assets.load(SkinID.BUTTON, "brown_button.png");
		assets.load(SoundID.BUBBLE, "BubbleSFX.wav");
		assets.load(SoundID.BUTTON, "ButtonSFX.wav");
		assets.load(SoundID.CANNON, "BubbleSFX.wav");
		assets.loadTextures();
	}
}
