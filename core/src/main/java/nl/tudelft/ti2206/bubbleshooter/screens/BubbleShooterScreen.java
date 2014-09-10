package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.ArrayList;

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
		ArrayList<Bubble> bubbles = board.getBubbles();
		for (int i = 0; i < bubbles.size(); i++) {
			Vector2 loc = getLoc(i);
			game.batch.setColor(bubbles.get(i).getColor());
			game.batch.draw(fg, loc.x + 200, Gdx.graphics.getHeight() - 32 - loc.y, 32, 32);
		}
		
		game.batch.setColor(current);
		game.batch.end();
	}
	
	private Vector2 getLoc(int idx) {
		int x = (idx % 7 % 4) * 32 +
				((idx % 7) / 4) * 16;
		int y = (idx / 7) * 64 +
				(idx % 7) / 4 * 32;
		return new Vector2(x, y);
	}
}
