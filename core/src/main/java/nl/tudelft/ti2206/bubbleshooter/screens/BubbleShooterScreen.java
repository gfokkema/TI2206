package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.Launch;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * The bubble shooter screen is the screen where the actual game is being played.
 * The screen contains a board (on which bubbles are drawn), and a cannon.
 * @author group-15
 *
 */
public class BubbleShooterScreen extends ScreenAdapter {	
	/**
	 * Variable initialization.
	 * The defined textures are used for the bubble and the board.
	 */
	Launch game;
	Projectile projectile;
	Cannon cannon;
	Board board;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game	the parent {@link Launch} instance
	 */
	public BubbleShooterScreen(Launch game) {
		this.game = game;
		this.board = new Board(8, 15);
		this.cannon = new Cannon(game.assets.get(TextureID.CANNON), Gdx.graphics.getWidth() / 2, 15);
		
		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}
	}

	/**
	 * Render everything on the bubble shooter screen
	 */
	@Override
	public void render (float delta) {
		handle_input();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		draw(board);
		board.getBubbles().forEach((Integer k, Bubble v) -> draw(v));
		draw(cannon.getProjectile());
		cannon.draw(game.batch);
		
		if (projectile != null) {
			if (projectile.getBounds().x - 16 < 190 || projectile.getBounds().x + 16 > 190 + board.getBoardWidth() * 32) {
				Vector2 dir = projectile.getDirection();
				dir.x = -dir.x;
			}
			projectile.move();
			draw(projectile);
			
			if (board.collides(projectile) || projectile.getBounds().y + 16 > 480) {
				int new_idx = board.getIndex(projectile.getMidPoint());
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
		game.batch.end();
	}
	
	/**
	 * Handle the input given by the player.
	 */
	private void handle_input() {
		if (projectile == null && Gdx.input.isKeyPressed(Keys.SPACE) && !board.collides(cannon.getProjectile())) {
			projectile = cannon.shoot();
			game.engine.play(SoundID.CANNON);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			cannon.left(Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			cannon.right(Gdx.graphics.getDeltaTime());
		}
	}
	
	@Override
	public void show() {
		// game.engine.play(MusicID.GAME);
	}
	
	@Override
	public void hide() {
		// game.engine.pause();
	}
	
	public void draw(BSDrawable drawable) {
		Vector2 position = drawable.getPosition();
		Vector2 origin = drawable.getOrigin();
		
		game.batch.setColor(drawable.getColor());
		game.batch.draw(new TextureRegion(game.assets.get(drawable.getTexture())),
						position.x, position.y,
						origin.x, origin.y,
						drawable.getWidth(), drawable.getHeight(),
						1, 1,
						drawable.getRotation());
	}
}
