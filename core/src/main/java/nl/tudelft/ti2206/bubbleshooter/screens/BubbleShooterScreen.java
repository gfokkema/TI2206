package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;

import com.badlogic.gdx.graphics.GL30;

import nl.tudelft.ti2206.bubbleshooter.Board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

public class BubbleShooterScreen extends ScreenAdapter {
	Launch game;
	Bubble blue;
	Cannon cannon;
	Board board;
	float elapsed;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game
	 */
	public BubbleShooterScreen(Launch game) {
		this.game = game;
		this.board = new Board(8, 15);
		cannon = new Cannon(Gdx.graphics.getWidth()/2,100);
	}
	

	@Override
	public void resize (int width, int height) {
	}
	
	/**
	 * Render everything on the bubble shooter screen
	 */
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		board.draw(game.batch);
		cannon.draw(game.batch);
		game.batch.end();
	}
}
