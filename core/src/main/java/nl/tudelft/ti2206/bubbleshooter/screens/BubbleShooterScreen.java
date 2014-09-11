package nl.tudelft.ti2206.bubbleshooter.screens;


import nl.tudelft.ti2206.bubbleshooter.Bubble;
import nl.tudelft.ti2206.bubbleshooter.Board;
import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Cannon;

import java.util.Collection;
import java.util.Map;

import nl.tudelft.ti2206.bubbleshooter.core.Launch;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

public class BubbleShooterScreen extends ScreenAdapter {
	Launch game;
	Bubble projectile;
	Circle pCircle;
	Cannon cannon;
	Board board;
	int frame_count;
	boolean fired;
	Texture bg = new Texture("back_one_player.png");
	Texture fg = new Texture("Bubble-Blue.png");
	boolean testperformed = false;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game
	 */
	public BubbleShooterScreen(Launch game) {
		this.game = game;
		this.board = new Board(8, 15);
		cannon = new Cannon(Gdx.graphics.getWidth()/2,100);
//		projectile = new Bubble();
//		projectile.setCircle(cannon.getBCPosition().x, cannon.getBCPosition().y, fg.getHeight()/4);
//		pCircle = projectile.getCircle();
//		Gdx.app.log("Create BCPos", "" + cannon.getBCPosition()); 
		frame_count = 0;
		fired = false;
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
		Color current = game.batch.getColor();
		handle_input();
		game.batch.draw(bg, 0, 0);
		Map<Integer, Bubble> bubbles = board.getBubbles();
		bubbles.forEach((Integer k, Bubble v) -> {
			Vector2 loc = getLoc(k);
			game.batch.setColor(v.getColor());
			game.batch.draw(fg, loc.x + 190, 480 - loc.y, 32, 32);
		});
		game.batch.setColor(current);
		
		// cannon must not be drawn when space has been pressed... (CHANGE!)
		if(!fired) 		cannon.draw(game.batch);
		// draw the projectile
		cannon.drawBubble(game.batch);
		// increment frame counter
		if(fired)		frame_count++;
		Gdx.app.log("Count is", "" + frame_count);
		// frame count
		if(frame_count == 50){
			fired = false;
			frame_count = 0;
		}
		
		game.batch.end();
	}
	
	/**
	 * Handle the input given by the player.
	 */
	private void handle_input() {
		// remove bubble test --> Key = R
		if(!testperformed && Gdx.input.isKeyPressed(Keys.R)) {
			testperformed = true;
			Collection<Bubble> remove = board.getColorGroup(0);
			if(remove.size() >= 3) {
				board.removeAll(remove);
				Collection<Bubble> disconnected = board.getDisconnectedGroup();
				board.removeAll(disconnected);
			}
		}
		
		// put pressed on true when Spacebar was hit
		if(Gdx.input.isKeyPressed(Keys.SPACE))	fired = true;
		// if space was pressed then shoot must be called for X frames.
		if(fired)	cannon.shoot();
		
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
