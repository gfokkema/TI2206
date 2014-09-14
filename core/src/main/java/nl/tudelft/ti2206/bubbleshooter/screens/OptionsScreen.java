package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.audio.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.audio.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;
import nl.tudelft.ti2206.bubbleshooter.utils.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Rectangle;

/**
 * The options screen, where the player may change volume settings.
 * @author group-15
 *
 */
public class OptionsScreen extends ScreenAdapter {
	/**
	 * Variable initialization.
	 * Set the screen title.
	 */
	public static final String title = "Options";
	private final float volumeStep = 0.1f;

	Launch game;
	ArrayList<Button> buttons;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public OptionsScreen(Launch game) {
		this.game = game;
		this.buttons = new ArrayList<Button>();

		// Button that turns the BGM volume up!
		Button BGMvolup = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 + 50, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Volume Up!",
				() -> { game.engine.setBGMVolume(game.engine.getBGMVolume() + volumeStep); game.engine.play(SoundID.BUTTON); }
		);
		
		// Button that turns the BGM volume down!
		Button BGMvoldown = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 + 50, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Volume Down!",
				() -> { game.engine.setBGMVolume(game.engine.getBGMVolume() - volumeStep); game.engine.play(SoundID.BUTTON); }
		);
		
		// Button that turns the SFX volume up!
		Button SFXvolup = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 - 25, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"SFX Up!",
				() -> { game.engine.setSFXVolume(game.engine.getSFXVolume() + volumeStep); game.engine.play(SoundID.BUTTON); }
		);
		
		// Button that turns the SFX volume down!
		Button SFXvoldown = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 - 25, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"SFX Down!",
				() -> { game.engine.setSFXVolume(game.engine.getSFXVolume() - volumeStep); game.engine.play(SoundID.BUTTON); }
		);
		
		// Button that sends the player back to the main menu.
		Button back = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 100, 200, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Back",
				() -> { this.game.setScreen(game.mms); game.engine.play(SoundID.BUTTON); }
		);
		
		//Add buttons, each with their own callback.
		buttons.add(BGMvolup);
		buttons.add(BGMvoldown);
		buttons.add(SFXvolup);
		buttons.add(SFXvoldown);
		buttons.add(back);
	}
	
	/**
	 * Render the buttons
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		update();
		game.batch.begin();

		game.font.setColor(new Color(0xFFFF00FF));
		game.font.draw(game.batch, title, Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 + 125);

		buttons.forEach((Button b) -> b.draw(game.batch));
		game.batch.end();
	}

	/**
	 * Update during each frame. Called by {@link #render(float)}
	 * Checks if buttons were hit and reacts to it.
	 */
	private void update() {
        boolean left_down = Gdx.input.justTouched();
		if (left_down) {
			int x = Gdx.input.getX();
			int y = Gdx.graphics.getHeight() - Gdx.input.getY();
			buttons.stream()
				.filter((Button b) -> b.hit(x,y))
				.findFirst()
				.ifPresent(Button::apply);
		}
	}
	
	/**
	 * Play some background music.
	 * This music will be played in the main menu screen and options screen
	 */
	@Override
	public void show() {
		game.engine.play(MusicID.MENU);
	}
	
	/**
	 * Hide is being called when the main menu screen is not the current screen.
	 */
	@Override
	public void hide() {
		game.engine.pause();
	}
}
