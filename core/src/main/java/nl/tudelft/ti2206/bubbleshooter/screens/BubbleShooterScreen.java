package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

import com.badlogic.gdx.Gdx;
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
		
		// Draw all game sprites.
		game.getDrawables().forEach((BSDrawable b) -> draw(new Vector2(190,0), b));

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
