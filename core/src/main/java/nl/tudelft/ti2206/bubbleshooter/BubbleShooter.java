package nl.tudelft.ti2206.bubbleshooter;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerProcessor;
import nl.tudelft.ti2206.bubbleshooter.mode.ZenMode;
import nl.tudelft.ti2206.bubbleshooter.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	public MainMenuScreen mms;
	public SoundEngine engine;
	public SpriteBatch batch;
	private BSMode game_mode;
	public static Map<Integer, Consumer<SinglePlayerProcessor>> keyBindings;
	
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
		game_mode = new ZenMode(this);
		
		assets.load(MusicID.GAME, "BGMenu.ogg");
		assets.load(MusicID.MENU, "BGMenu.ogg");
		assets.load(SoundID.BUBBLE, "BubbleSFX.wav");
		assets.load(SoundID.BUTTON, "ButtonSFX.wav");
		assets.load(SoundID.CANNON, "BubbleSFX.wav");
		assets.load(TextureID.BACKGROUND, "Background.png");
		assets.load(TextureID.BUBBLE, "Bubble-Blue.png");
		assets.load(TextureID.CANNON, "cannon.png");
		assets.finish();
		
		mms = new MainMenuScreen(this);
		this.setScreen(mms);
	}

	public Collection<BSDrawable> getDrawables() {
		return game_mode.getDrawables();
	}

	@Override
	public void render() {
		game_mode.update(Gdx.graphics.getDeltaTime());
		this.getScreen().render(Gdx.graphics.getDeltaTime());
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
