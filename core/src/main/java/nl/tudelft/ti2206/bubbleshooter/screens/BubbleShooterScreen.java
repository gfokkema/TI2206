package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

public class BubbleShooterScreen extends ScreenAdapter {
	Launch game;
	Board board;
	float elapsed;

	public BubbleShooterScreen(Launch game) {
		this.game = game;
		this.board = new Board(8, 15);
	}

	@Override
	public void resize (int width, int height) {
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		board.draw(game.batch);
		game.batch.end();
	}
}
