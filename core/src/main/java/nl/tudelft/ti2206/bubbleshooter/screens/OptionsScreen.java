package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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
		
		LabelStyle labelstyle = new LabelStyle(game.font, Color.WHITE);
		TextButtonStyle style = new TextButtonStyle();
		style.font = game.font;
		
		Label vollabel = new Label("Change Volume", labelstyle);
		TextButton volup = new TextButton("+", style);
		TextButton voldown = new TextButton("-", style);
		Label sfxlabel = new Label("Change SFX", labelstyle);
		TextButton sfxup = new TextButton("+", style);
		TextButton sfxdown = new TextButton("-", style);
		
		volup.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.engine.setBGMVolume(game.engine.getBGMVolume() + volumeStep);
			}
		});
		voldown.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.engine.setBGMVolume(game.engine.getBGMVolume() - volumeStep);
			}
		});
		sfxup.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.engine.setSFXVolume(game.engine.getSFXVolume() + volumeStep);
			}
		});
		sfxdown.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.engine.setSFXVolume(game.engine.getSFXVolume() - volumeStep);
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
		
		table.add(vollabel).expandX().center().row();
		table.add(volgroup).expandX().center().row();
		table.add(sfxlabel).expandX().center().row();
		table.add(sfxgroup).expandX().center().row();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}
