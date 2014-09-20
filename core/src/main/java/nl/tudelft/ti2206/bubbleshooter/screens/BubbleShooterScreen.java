package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.Launch;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
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
	HashMap<Vector2, Board> boards;
	Board board;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game	the parent {@link Launch} instance
	 */
	public BubbleShooterScreen(Launch game) {
		this.game = game;
		this.board = new Board(8, 15);
		
		this.boards = new HashMap<Vector2, Board>();
		this.boards.put(new Vector2(30, 0), board);
		this.boards.put(new Vector2(350, 0), new Board(8, 15));
		this.boards.values().forEach((Board b) -> {
			for (int i = 0; i < 40; i++) {
				b.add(new Bubble(), i);
			}
		});
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
		boards.forEach((Vector2 offset, Board board) -> {
			board.update();
			board.getDrawables().forEach((BSDrawable d) -> {
				draw(offset, d);
			});
		});
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
	
	public void draw(Vector2 offset, BSDrawable drawable) {
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
