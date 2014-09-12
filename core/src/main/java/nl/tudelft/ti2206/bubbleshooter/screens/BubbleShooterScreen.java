package nl.tudelft.ti2206.bubbleshooter.screens;


import nl.tudelft.ti2206.bubbleshooter.Bubble;
import nl.tudelft.ti2206.bubbleshooter.Board;
import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Projectile;

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
	Projectile projectile;
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
		cannon = new Cannon(Gdx.graphics.getWidth()/2,100, board);
		projectile = cannon.getProjectile();
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
		
		//
		// PROTOTYPE
		//
		// if (board.collides(shot)) board.add(b, toIdx);

		game.batch.begin();
		Color current = game.batch.getColor();
		
		handle_input();
		handleInputCannon();
		updateCannon();
		game.batch.draw(bg, 0, 0);
		Map<Integer, Bubble> bubbles = board.getBubbles();
		bubbles.forEach((Integer k, Bubble v) -> {
			game.batch.setColor(v.getColor());
			
			// translate from the midpoint to the bottom left
			game.batch.draw(fg, v.getBounds().x - 16, v.getBounds().y - 16, 32, 32);
		});
		
		cannon.draw(game.batch);
		game.batch.setColor(current);
		
		game.batch.end();
	}
	
	/**
	 * Handle the input given by the player.
	 */
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
	 * Update attributes which are able to move
	 */
	private void updateCannon() {		
		// if fired, check if the projectile hits the wall
		// perform shoot
		if(fired) {
			if(board.collides(projectile) || projectile.getCircle().y > 480) {
				Circle c = projectile.getBounds();
				int idx = board.getIndex(new Vector2(c.x, c.y));
				board.add(projectile, idx);
				projectile = cannon.CreateProjectile();
				fired = false;
			}
			
			if(projectile.getCircle().x < 190 || projectile.getCircle().x > Gdx.graphics.getWidth() - 190 - fg.getWidth()/2)
				projectile.getDirection().setAngle(180 -projectile.getDirection().angle());
			
			projectile.move();
		}
		// else draw projectile on cannon
		else {
			projectile.setPosition(new Vector2(cannon.getPointer().getOrigin()).add(new Vector2(cannon.getPointer().getDirection()).scl(100)));
			projectile.setDirection(cannon.getPointer().getDirection());
		}
	}
	
	/**
	 * Controls handleInput
	 */
	public void handleInputCannon() {
		//if pressed left, turn cannon to the left
		if(Gdx.input.isKeyPressed(Keys.LEFT) && !fired) {
			float angle = cannon.getAngle() + cannon.getSensitivity()*Gdx.graphics.getDeltaTime(); 
			Gdx.app.log("angle", "" + cannon.getAngle());
			cannon.setAngle(angle); 
		}
		
		//if pressed right, turn cannon to the right
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && !fired) {
			float angle = cannon.getAngle() - cannon.getSensitivity()*Gdx.graphics.getDeltaTime(); 
			cannon.setAngle(angle); 
		}
		
		//if pressed space, trigger shoot
		if(Gdx.input.isKeyPressed(Keys.SPACE) && !board.collides(projectile)) {
			projectile.setVelocity(cannon.getVelocity());
			fired = true;	
		}		
	}

}
