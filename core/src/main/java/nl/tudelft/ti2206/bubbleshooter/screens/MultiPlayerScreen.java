package nl.tudelft.ti2206.bubbleshooter.screens;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MultiPlayerScreen extends AbstractScreen {
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public MultiPlayerScreen(BubbleShooter game) {
		super(game);
		
		TextButtonStyle style = new TextButtonStyle();
		style.font = game.font;
		
		TextButton host = new TextButton("Host a game", style);
		TextButton join = new TextButton("Join a game", style);
		
		host.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.setScreen(new HostGameScreen(game));
			}
		});
		join.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.setScreen(new JoinGameScreen(game));
			}
		});
		
		table.add(host).expandX().center().row();
		table.add(join).expandX().center().row();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}