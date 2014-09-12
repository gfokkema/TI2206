package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.core.Launch;
import nl.tudelft.ti2206.bubbleshooter.utils.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Rectangle;

public class OptionsScreen extends ScreenAdapter {
	
	public static final String title = "Options";
	Launch game;
	Music BGM;
	float volume;
	private final float volumeStep = 0.1f;
	ArrayList<Button> buttons;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public OptionsScreen(Launch game, Music BGM) {
		this.BGM = BGM;
		this.game = game;
		volume = BGM.getVolume();
		this.buttons = new ArrayList<Button>();

		//Add buttons, each with their own callback.
		Button volup = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 + 50, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Volume Up!",
				() -> volumeUp()
		);
		Button voldown = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 + 50, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Volume Down!",
				() -> volumeDown()
		);
		Button back = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 25, 200, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Back",
				() -> this.game.setScreen(new MainMenuScreen(game, this.BGM))
		);
		buttons.add(volup);
		buttons.add(voldown);
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
	 *
	 */
	@Override
	public void show() {
		BGM.play();
	}
	
	@Override
	public void hide() {
		BGM.pause();
	}
	
	/**
	 * Set volume boundaries, so it stays within the [0,1] interval.
	 */
	public void checkVolume() {
		if(volume < 0) volume = 0;
		if(volume > 1) volume = 1;
	}
	
	/**
	 * Raise the volume.
	 */
	public void volumeUp() {
		this.BGM.setVolume(volume+=volumeStep);
		checkVolume();
	}
	
	/**
	 * Lower the volume
	 */
	public void volumeDown() {
		this.BGM.setVolume(volume-=volumeStep);
		checkVolume();
	}
	
}
