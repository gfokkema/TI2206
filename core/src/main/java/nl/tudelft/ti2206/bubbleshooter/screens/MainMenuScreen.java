package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The actual menu screen, which shows the various buttons to choose from.
 * Such buttons include:
 * - Starting game
 * - Options menu
 * - Viewing high scores
 * @author group-15
 *
 */
public class MainMenuScreen extends AbstractScreen {
	/**
	 * Variable initialization.
	 * Set the title of the screen.
	 * Set the BGM used.
	 */
	public static final String title = "Bubble Shooter";

	OptionsScreen options;
	BubbleShooterScreen BBS;

	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session.
	 */
	public MainMenuScreen(BubbleShooter game) {
		super(game);
		
		TextButtonStyle style = new TextButtonStyle();
		style.font = game.font;
		
		TextButton singleplay = new TextButton("Single player", style);
		TextButton multiplay = new TextButton("Multi player", style);
		TextButton options = new TextButton("Options", style);
		TextButton quit = new TextButton("Quit", style);
		
		singleplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.setScreen(new BubbleShooterScreen(game));
			}
		});
		multiplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.setScreen(new MultiPlayerScreen(game));
			}
		});
		options.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.setScreen(new OptionsScreen(game));
			}
		});
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				Gdx.app.exit();
			}
		});
		
		table.add(singleplay).expandX().center().row();
		table.add(multiplay).expandX().center().row();
		table.add(options).expandX().center().row();
		table.add(quit).expandX().center().row();
	}
	
	/**
	 * Play some main menu background music.
	 * This music will be played in the main menu screen and options screen
	 */
	@Override
	public void show() {
		super.show();
		game.engine.play(MusicID.MENU);
	}
	
	/**
	 * Hide is being called when the main menu screen is not the current screen.
	 */
	@Override
	public void hide() {
		super.hide();
		game.engine.pause();
	}
}
