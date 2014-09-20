package nl.tudelft.ti2206.bubbleshooter.screens;



import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The bubble shooter screen is the screen where the actual game is being played.
 * The screen contains a board (on which bubbles are drawn), and a cannon.
 * @author group-15
 *
 */
public class BubbleShooterScreen extends ScreenAdapter {	
	BubbleShooter game;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game	the parent {@link BubbleShooter} instance
	 */
	public BubbleShooterScreen(BubbleShooter game) {
		this.game = game;
	}

	/**
	 * Render everything on the bubble shooter screen
	 */
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(game.assets.get(TextureID.BACKGROUND), 0, 0);
		
		// Draw all game sprites.
		game.getDrawables().forEach((Sprite s) -> {
			s.draw(game.batch);
		});

		game.batch.end();
	}
	
	@Override
	public void show() {
		// game.engine.play(MusicID.GAME);
	}
	
	@Override
	public void hide() {
		// game.engine.pause();
	}
}
