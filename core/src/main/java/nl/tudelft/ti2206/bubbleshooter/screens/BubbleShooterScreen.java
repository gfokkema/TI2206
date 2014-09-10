package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.Board;
import nl.tudelft.ti2206.bubbleshooter.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

public class BubbleShooterScreen extends ScreenAdapter {
	Launch game;
	Board board;
	float elapsed;
	Texture bg = new Texture("back_one_player.png");
	Texture fg = new Texture("Bubble-Blue.png");

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
		Color current = game.batch.getColor();
		
		game.batch.draw(bg, 0, 0);
		Collection<Bubble> bubbles = board.getBubbles();
		int i = 0;
		for(Bubble b : bubbles) {
			Vector2 loc = getLoc(i);
			game.batch.setColor(b.getColor());
			game.batch.draw(fg, loc.x + 190, 480 - loc.y, 32, 32);
		}
		
		game.batch.setColor(current);
		game.batch.end();
	}
	
	/**
	 * Returns the bottom left xy-coordinates of a bubble
	 * @param idx	the index of the bubble on the board
	 * @return		{@link Vector2} representing xy-coordinates
	 */
	private Vector2 getLoc(int idx) {
		int width = board.getWidth();
		int x = (idx % (width * 2 - 1) % width) * 32 +
				((idx % (width * 2 - 1)) / width) * 16;
		int y = (idx / (width * 2 - 1)) * 56 +
				(idx % (width * 2 - 1)) / width * 28;

		// Correction for going from top left corner to bottom left corner
		return new Vector2(x, y + 32);
	}
}
