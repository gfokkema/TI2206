package nl.tudelft.ti2206.bubbleshooter.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class BubbleShooterScreen extends ScreenAdapter {
	Game game;

	public BubbleShooterScreen(Game game) {
		this.game = game;
	}

	@Override
	public void resize (int width, int height) {
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

	@Override
	public void dispose () {
	}

}
