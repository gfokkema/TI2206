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
	Vector2 offset = new Vector2(190,0);
	
	/**
	 * Variable initialization.
	 * The defined textures are used for the bubble and the board.
	 */
	Launch game;
	Board board;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game	the parent {@link Launch} instance
	 */
	public BubbleShooterScreen(Launch game) {
		this.game = game;
		this.board = new Board(8, 15);
		
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
		draw(board.getCannon().getProjectile());
		draw(board.getCannon());
		if (board.getProjectile() != null) {
			draw(board.getProjectile());
			board.move();
		}
		game.batch.end();
	}
	
	/**
	 * Handle the input given by the player.
	 */
	private void handle_input() {
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			if (board.shoot()) game.engine.play(SoundID.CANNON);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			board.getCannon().left(Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			board.getCannon().right(Gdx.graphics.getDeltaTime());
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
						offset.x + position.x, offset.y + position.y,
						origin.x, origin.y,
						drawable.getWidth(), drawable.getHeight(),
						1, 1,
						drawable.getRotation());
	}
}
