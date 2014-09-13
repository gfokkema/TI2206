package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.BackgroundMusic;
import nl.tudelft.ti2206.bubbleshooter.SoundEffect;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;
import nl.tudelft.ti2206.bubbleshooter.utils.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Rectangle;

public class OptionsScreen extends ScreenAdapter {
	
	public static final String title = "Options";
	Launch game;
	BackgroundMusic BGM;
	SoundEffect SFX;
	float BGMvolume;
	float SFXvolume;
	private final float volumeStep = 0.1f;
	ArrayList<Button> buttons;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public OptionsScreen(Launch game, BackgroundMusic BGM) {
		this.BGM = BGM;
		this.game = game;
		BGMvolume = this.BGM.getVolume();
		this.buttons = new ArrayList<Button>();

		//Add buttons, each with their own callback.
		Button BGMvolup = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 + 50, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Volume Up!",
				() -> BGMvolumeUp()
		);
		Button BGMvoldown = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 + 50, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Volume Down!",
				() -> BGMvolumeDown()
		);
		
		Button SFXvolup = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 - 25, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"SFX Up!",
				() -> SFXvolumeUp()
		);
		Button SFXvoldown = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 2 - 25, 100, 50),
				new Color(0xFFFF00FF),
				game.font,
				"SFX Down!",
				() -> SFXvolumeDown()
		);
		Button back = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 100, 200, 50),
				new Color(0xFFFF00FF),
				game.font,
				"Back",
				() -> this.game.setScreen(game.mms)
		);
		
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
	
	/**
	 * Raise the volume.
	 */
	public void BGMvolumeUp() {
		this.BGM.setVolume(BGMvolume+=volumeStep);
		BGMcheckVolume();
	}
	
	/**
	 * Lower the volume
	 */
	public void BGMvolumeDown() {
		this.BGM.setVolume(BGMvolume-=volumeStep);
		BGMcheckVolume();
	}
	
	
	/**
	 * Raise the volume.
	 */
	public void SFXvolumeUp() {
		buttons.forEach((Button b) -> {
			this.SFX = b.getSFX(); 
			SFXvolume = this.SFX.getVolume();
			SFXvolume+=volumeStep;
			SFXcheckVolume();
			b.getSFX().setVolume(SFXvolume); 
			Gdx.app.log("SFXVol", "" +SFXvolume);
		});
	}
	
	/**
	 * Lower the volume
	 */
	public void SFXvolumeDown() {
		buttons.forEach((Button b) -> {
			this.SFX = b.getSFX(); 
			SFXvolume = this.SFX.getVolume();
			SFXvolume-=volumeStep;
			SFXcheckVolume();
			b.getSFX().setVolume(SFXvolume); 
			Gdx.app.log("SFXVol", "" +SFXvolume);
		});
	}
	
	/**
	 * Set volume boundaries for BGM, so it stays within the [0,1] interval.
	 */
	public void BGMcheckVolume() {
		if(BGMvolume < 0) BGMvolume = 0;
		if(BGMvolume > 1) BGMvolume = 1;
	}
	
	/**
	 * Set volume boundaries for SFX, so it stays within the [0,1] interval.
	 */
	public void SFXcheckVolume() {
		if(SFXvolume < 0) SFXvolume = 0;
		if(SFXvolume > 1) SFXvolume = 1;
	}
	
	/**
	 * Apply the settings set in the options screen to other screens.
	 */
	public void applySettings() {
		game.mms.buttons.forEach((Button b) -> {
			b.getSFX().setVolume(SFXvolume);
		});
	}
}
