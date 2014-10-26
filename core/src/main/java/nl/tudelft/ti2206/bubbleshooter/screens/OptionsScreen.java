package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Settings;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The options screen, where the player may change volume settings.
 * @author group-15
 *
 */
public class OptionsScreen extends AbstractScreen {
	/**
	 * Variable initialization.
	 * Set the screen title.
	 */
	public static final String title = "Options";
	private final float volumeStep = 0.1f;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public OptionsScreen(BubbleShooter game) {
		super(game);
				
		Label vollabel = new Label("Change Volume", labelStyle);
		TextButton volup = new TextButton("+", buttonStyle);
		TextButton voldown = new TextButton("-", buttonStyle);
		Label sfxlabel = new Label("Change SFX", labelStyle);
		TextButton sfxup = new TextButton("+", buttonStyle);
		TextButton sfxdown = new TextButton("-", buttonStyle);
		Label themelabel = new Label("Current Theme", labelStyle);
		TextButton themenext = new TextButton("Change Theme", buttonStyle);

		
		volup.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				SoundEngine.getSoundEngine().setBGMVolume(SoundEngine.getSoundEngine().getBGMVolume() + volumeStep);
			}
		});
		voldown.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				SoundEngine.getSoundEngine().setBGMVolume(SoundEngine.getSoundEngine().getBGMVolume() - volumeStep);
			}
		});
		sfxup.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				SoundEngine.getSoundEngine().setSFXVolume(SoundEngine.getSoundEngine().getSFXVolume() + volumeStep);
			}
		});
		sfxdown.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				SoundEngine.getSoundEngine().setSFXVolume(SoundEngine.getSoundEngine().getSFXVolume() - volumeStep);
			}
		});
		themenext.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				Settings.getSettings().nextTheme();
				game.setScreen(new OptionsScreen(game));
			}
		});
		
		HorizontalGroup volgroup = new HorizontalGroup();
		volgroup.space(20);
		volgroup.addActor(volup);
		volgroup.addActor(voldown);
		
		HorizontalGroup sfxgroup = new HorizontalGroup();
		sfxgroup.space(20);
		sfxgroup.addActor(sfxup);
		sfxgroup.addActor(sfxdown);
		
		HorizontalGroup themegroup = new HorizontalGroup();
		themegroup.space(20);
		themegroup.addActor(themenext);
		
		table.add(vollabel).expandX().center().row();
		table.add(volgroup).expandX().center().row();
		table.add(sfxlabel).expandX().center().row();
		table.add(sfxgroup).expandX().center().row();
		table.add(themelabel).expandX().center().row();
		table.add(themegroup).expandX().center().row();
	}
	
	/**
	 * Render the things onto the screen.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(new MainMenuScreen(game));
	}
}
