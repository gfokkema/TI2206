package nl.tudelft.ti2206.bubbleshooter.screens;

<<<<<<< HEAD

import nl.tudelft.ti2206.bubbleshooter.Bubble;
import nl.tudelft.ti2206.bubbleshooter.Board;
import nl.tudelft.ti2206.bubbleshooter.Projectile;
import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Cannon;

=======
>>>>>>> master
import java.util.Collection;
import java.util.Map;

import nl.tudelft.ti2206.bubbleshooter.Board;
import nl.tudelft.ti2206.bubbleshooter.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;

public class BubbleShooterScreen extends ScreenAdapter {
	Launch game;
<<<<<<< HEAD
	Projectile projectile;
	Cannon cannon;
	Board board;
=======
	Board board;
	Bubble shot;
	float elapsed;
>>>>>>> master
	Texture bg = new Texture("back_one_player.png");
	Texture fg = new Texture("Bubble-Blue.png");
	boolean testperformed = false;

	public BubbleShooterScreen(Launch game) {
		this.game = game;
		this.board = new Board(8, 15);
<<<<<<< HEAD
		this.cannon = new Cannon(Gdx.graphics.getWidth() / 2,100);
=======
>>>>>>> master
	}

	@Override
	public void resize (int width, int height) {
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		handle_input();
		game.batch.draw(bg, 0, 0);
		
		// Draw all the bubbles
		Color current = game.batch.getColor();
		Map<Integer, Bubble> bubbles = board.getBubbles();
		bubbles.forEach((Integer k, Bubble v) -> {
			game.batch.setColor(v.getColor());
			
			// translate from the midpoint to the bottom left
			game.batch.draw(fg, v.getBounds().x - 16, v.getBounds().y - 16, 32, 32);
		});
		if (projectile != null) {
			projectile.move();
			if (projectile.getBounds().x - 16 < 190 || projectile.getBounds().x + 16 > 190 + board.getWidth() * 32) {
				Vector2 dir = projectile.getDirection();
				dir.x = -dir.x;
			}
			game.batch.setColor(projectile.getColor());
			game.batch.draw(fg, projectile.getBounds().x - 16, projectile.getBounds().y - 16, 32, 32);
			
			if (board.collides(projectile) || projectile.getBounds().y + 16 > 480) {
				int new_idx = board.getIndex(projectile.getPosition());
				if (board.add(projectile, new_idx)) {
					Collection<Bubble> sameColors = board.getColorGroup(new_idx);
					if (sameColors.size() >= 3) {
						board.removeAll(sameColors);
						board.removeAll(board.getDisconnectedGroup());
					}
				}
				projectile = null;
			}
		}
		game.batch.setColor(cannon.getBubble().getColor());
		game.batch.draw(fg, cannon.getBubble().getBounds().x - 16, cannon.getBubble().getBounds().y - 16, 32, 32);
		game.batch.setColor(current);
		game.batch.end();
	}
	
	private void handle_input() {
<<<<<<< HEAD
		if (projectile == null && Gdx.input.isKeyPressed(Keys.SPACE)) {
			projectile = cannon.shoot();
=======
		if(Gdx.input.justTouched()) {
			int idx = board.getIndex(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
			System.out.println(idx);
			board.add(new Bubble(), idx);
		}
		if(!testperformed && Gdx.input.isKeyPressed(Keys.R)) {
			testperformed = true;
			Collection<Bubble> remove = board.getColorGroup(0);
			if(remove.size() >= 3) {
				board.removeAll(remove);
				Collection<Bubble> disconnected = board.getDisconnectedGroup();
				board.removeAll(disconnected);
			}
>>>>>>> master
		}
	}
}
