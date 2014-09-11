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
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;

public class BubbleShooterScreen extends ScreenAdapter {
	Launch game;
	Bubble projectile;
	Circle pCircle;
	Cannon cannon;
	Board board;
	float elapsed;
	int count;
	boolean pressed;
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
		projectile = new Bubble();
		projectile.setCircle(cannon.getBCPosition().x, cannon.getBCPosition().y, fg.getHeight()/4);
		pCircle = projectile.getCircle();
		Gdx.app.log("Create BCPos", "" + cannon.getBCPosition()); 
		count = 0;
		pressed = false;
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
		Gdx.app.log("Count is", "" + count);
		
		// cannon must not be drawn when space has been pressed...
		if(!pressed){
		cannon.draw(game.batch);
		}

		// put pressed on true when space was hit
		if(Gdx.input.isKeyPressed(Keys.SPACE)){	
			pressed = true;
		}
		
		// draw if space was pressed <---- THIS IS "SHOOT"...
		if(pressed){
			cannon.getBCPosition().x += cannon.getPointer().getDirection().x *5;
			cannon.getBCPosition().y += cannon.getPointer().getDirection().y *5;
			
			
			projectile.setCircle(cannon.getBCPosition().x, cannon.getBCPosition().y, fg.getHeight()/4);
			// bubble stays within screen bounds
			if(pCircle.x < 190) pCircle.x = 190;
			if(pCircle.x > Gdx.graphics.getWidth() - 190 - fg.getWidth()/2) pCircle.x = Gdx.graphics.getWidth() - 190 - fg.getWidth()/2;
			if(pCircle.y > Gdx.graphics.getHeight() - fg.getHeight()/2) pCircle.y = Gdx.graphics.getHeight() - fg.getHeight()/2;
			
			Gdx.app.log("Circle Pos", "X= " + projectile.getCircle().x + " Y= " + projectile.getCircle().y);
			game.batch.draw(fg, projectile.getCircle().x, projectile.getCircle().y, 32, 32);
			count++;
		
		}
		
		// frane count
		if(count == 40){
			pressed = false;
			count = 0;
		}
		//cannon.shoot();
		
		// bubble on cannon draw
		Gdx.app.log("Circle Pos", "X= " + projectile.getCircle().x + " Y= " + projectile.getCircle().y);
		
		// only draw bubble on cannon if we didn't shoot yet
		if(!pressed){
			projectile.setCircle(cannon.getBCPosition().x, cannon.getBCPosition().y, fg.getHeight()/4);
			
			// boundary for moving bubble <--- not necessary anymore actually...
	//		if(pCircle.x < 190) pCircle.x = 190;
	//		if(pCircle.x > Gdx.graphics.getWidth() - 190 - fg.getWidth()/2) pCircle.x = Gdx.graphics.getWidth() - 190 - fg.getWidth()/2;
	//		if(pCircle.y > Gdx.graphics.getHeight() - fg.getHeight()/2) pCircle.y = Gdx.graphics.getHeight() - fg.getHeight()/2;
			game.batch.draw(fg, projectile.getCircle().x, projectile.getCircle().y, 32, 32);
		}
		game.batch.end();
	}
	
	private void handle_input() {
		if(!testperformed && Gdx.input.isKeyPressed(Keys.R)) {
			testperformed = true;
			Collection<Bubble> remove = board.getColorGroup(0);
			if(remove.size() >= 3) {
				board.removeAll(remove);
				Collection<Bubble> disconnected = board.getDisconnectedGroup();
				board.removeAll(disconnected);
			}
		}
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
