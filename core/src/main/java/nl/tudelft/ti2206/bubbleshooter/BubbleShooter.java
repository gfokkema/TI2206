package nl.tudelft.ti2206.bubbleshooter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SkinID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;
import nl.tudelft.ti2206.bubbleshooter.input.AbstractProcessor;
import nl.tudelft.ti2206.bubbleshooter.screens.MainMenuScreen;
import nl.tudelft.ti2206.bubbleshooter.util.FileHighscore;
import nl.tudelft.ti2206.bubbleshooter.util.Logger;
import nl.tudelft.ti2206.bubbleshooter.util.ScreenLogger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
	public Assets assets;
	public BitmapFont font;
	public FileHighscore scores;
	public MainMenuScreen mms;
	public SoundEngine engine;
	public SpriteBatch batch;
	public static Map<Integer, Function<AbstractProcessor, Boolean>> keyDownBindings
		= new HashMap<Integer, Function<AbstractProcessor, Boolean>>();
	public static Map<Integer, Function<AbstractProcessor, Boolean>> keyUpBindings
		= new HashMap<Integer, Function<AbstractProcessor, Boolean>>();

	// Initialize the keybindings.
	static {
		keyDownBindings.put(Keys.LEFT, AbstractProcessor::cannonLeft);
		keyDownBindings.put(Keys.RIGHT, AbstractProcessor::cannonRight);
		keyDownBindings.put(Keys.SPACE, AbstractProcessor::cannonShoot);

		keyUpBindings.put(Keys.LEFT, AbstractProcessor::cannonStopLeft);
		keyUpBindings.put(Keys.RIGHT, AbstractProcessor::cannonStopRight);
	}

	/**
	 * Create the spritebatch and bitmapfont.
	 * Set the main-menu screen
	 */
	@Override
	public void create() {
		assets = Assets.getAssets();
		batch = new SpriteBatch();
		font = new BitmapFont();
		engine = new SoundEngine(assets);
		Logger.getLogger().addLog(new ScreenLogger());
	
		assets.load(MusicID.GAME, "eerie.ogg");
		assets.load(MusicID.MENU, "BGMenu.ogg");
		assets.load(SkinID.BUTTON, "brown_button.png");
		assets.load(SoundID.BUBBLE, "BubbleSFX.wav");
		assets.load(SoundID.BUTTON, "ButtonSFX.wav");
		assets.load(SoundID.CANNON, "BubbleSFX.wav");
		assets.load(TextureID.BACKGROUND, "BG_back.png");
		assets.load(TextureID.BORDER, "MPborder.png");
		assets.load(TextureID.BUBBLE, "Bubble-Blue.png");
		assets.load(TextureID.CANNON, "cannon.png");
		assets.load(TextureID.STONEBUBBLE, "StoneBubble.png");
		assets.finish();
		
		assets.get(TextureID.BUBBLE).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		assets.get(TextureID.STONEBUBBLE).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		assets.get(TextureID.CANNON).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
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
}
