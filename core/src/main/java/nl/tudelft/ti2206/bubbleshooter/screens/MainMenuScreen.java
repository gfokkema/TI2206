package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.BackgroundMusic;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;
import nl.tudelft.ti2206.bubbleshooter.utils.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Rectangle;

/**
 * The actual menu screen, which shows the various buttons to choose from.
 * Such buttons include:
 * - Starting game
 * - Options menu
 * - Viewing high scores
 * @author group-15
 *
 */
public class MainMenuScreen extends ScreenAdapter {
	public static final String title = "Bubble Shooter";
	private final String BGMname = "BGMenu.ogg";
	BackgroundMusic BGM;
	ArrayList<Button> buttons;
	Launch game;
	OptionsScreen options;
	BubbleShooterScreen BBS;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public MainMenuScreen(Launch game) {
		this.game = game;
		this.buttons = new ArrayList<Button>();
		BGM = new BackgroundMusic(BGMname);
		options = new OptionsScreen(game, BGM);
		BBS = new BubbleShooterScreen(game);
		
		//Add buttons, each with their own callback.
		Button play = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 50, 200, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Play!",
				() -> this.game.setScreen(BBS)
		);
		Button settings = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 25, 200, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Settings",
				() -> this.game.setScreen(options)
		);
		Button quit = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 100, 200, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Quit",
				() -> Gdx.app.exit()
		);
		buttons.add(play);
		buttons.add(settings);
		buttons.add(quit);
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
		game.font.draw(game.batch, title, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 125);

		buttons.forEach((Button b) -> b.draw(game.batch));
		game.batch.end();
	}

	/**
	 * Update during each frame. Called by {@link #render(float)}
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
	 * Play some main menu background music
	 * This music will be played in the main menu screen and options screen
	 */
	@Override
	public void show() {
		BGM.getBGM().play();
		
	}
	
	/**
	 * Hide is being called when the main menu screen is not the current screen.
	 */
	@Override
	public void hide() {
		BGM.getBGM().pause();
	}
	
	public void applySettings() {
		options.applySettings();
	}
}
