package nl.tudelft.ti2206.bubbleshooter.screens;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.ArcadeGameFactory;
import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.GameFactory;
import nl.tudelft.ti2206.bubbleshooter.SurvivalGameFactory;
import nl.tudelft.ti2206.bubbleshooter.ZenGameFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ModeScreen extends AbstractScreen {

	public ModeScreen(BubbleShooter game) {
		super(game);
		
		Label message = new Label("Choose your mode!", labelStyle);
		TextButton zenplay = new TextButton("Zen", buttonStyle);
		TextButton survival = new TextButton("Survival", buttonStyle);
		TextButton arcade = new TextButton("Arcade", buttonStyle);

		zenplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				GameFactory fact = new ZenGameFactory(game);
				game.setScreen(new BubbleShooterScreen(game, fact));
			}
		});
		
		survival.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				GameFactory fact = new SurvivalGameFactory(game);
				game.setScreen(new BubbleShooterScreen(game, fact));
			}
		});
		
		arcade.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				GameFactory fact = new ArcadeGameFactory(game, Duration.ofMinutes(2));
				game.setScreen(new BubbleShooterScreen(game, fact));
			}
		});
		
		table.add(message).expandX().center().row();
		table.add(zenplay).expandX().center().row();
		table.add(survival).expandX().center().row();
		table.add(arcade).expandX().center().row();
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
