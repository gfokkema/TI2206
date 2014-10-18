package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The screen shown when selecting to play a multi-player game.
 * The options are to host or to join a multi-player game.
 * @author group-15
 *
 */
public class MultiPlayerScreen extends AbstractScreen {
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public MultiPlayerScreen(BubbleShooter game) {
		super(game);
		
		TextButton host = new TextButton("Host a game", buttonStyle);
		TextButton join = new TextButton("Join a game", buttonStyle);
		
		host.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				game.setScreen(new HostGameScreen(game));
			}
		});
		join.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				game.setScreen(new JoinGameScreen(game));
			}
		});
		
		table.add(host).expandX().center().row();
		table.add(join).expandX().center().row();
	}
	
	/**
	 * Render the things onto the screen.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}