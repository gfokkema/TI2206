package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;
import nl.tudelft.ti2206.bubbleshooter.util.GameObserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * The bubble shooter screen is the screen where the actual game is being played.
 * @author group-15
 *
 */
public class BubbleShooterScreen extends ScreenAdapter implements GameObserver {
	BubbleShooter game;
	GameMode game_mode;
	GameUI ui;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game		the parent {@link BubbleShooter} instance
	 * @param game_mode	the {@link GameMode} that will be used
	 * @param ui		the {@link GameUI} that will be used
	 */
	public BubbleShooterScreen(BubbleShooter game, GameMode game_mode, GameUI ui) {
		this.game = game;
		this.game_mode = game_mode;
		this.ui = ui;
		game_mode.addGameObserver(this);
	}

	/**
	 * Render everything on the bubble shooter screen
	 * @param delta	the time that has passed
	 */
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		game_mode.update(delta);
		game.batch.begin();
		// Draw all game sprites.
		game_mode.getDrawables().forEach((Vector2 o, Collection<BSDrawable> c) -> {
			c.forEach((BSDrawable d) -> draw(o, d));
		});
		game.batch.end();
		ui.draw();
	}
	
	/**
	 * Show is being called upon entering the screen.
	 */
	@Override
	public void show() {
		SoundEngine.getSoundEngine().play(MusicID.GAME);
	}
	
	/**
	 * Hide is being called when exiting the screen.
	 */
	@Override
	public void hide() {
		// game.engine.pause();
	}
	
	/**
	 * Draw the various elements.
	 * @param offset an offset to which something is drawn.
	 * @param drawable the {@link BSDrawable}.
	 */
	public void draw(Vector2 offset, BSDrawable drawable) {
		Vector2 position = drawable.getPosition();
		Vector2 origin = drawable.getOrigin();
		
		game.batch.setColor(drawable.getColor());
		game.batch.draw(new TextureRegion(Assets.getAssets().get(drawable.getTexture())),
						offset.x + position.x, offset.y + position.y,
						origin.x, origin.y,
						drawable.getWidth(), drawable.getHeight(),
						1, 1,
						drawable.getRotation());
	}

	@Override
	public void switchToLostScreen() {
		game.setScreen(new GameEndedScreen(game, "YOU LOST!", game_mode.getScore()));
	}

	@Override
	public void switchToWonScreen() {
		game.setScreen(new GameEndedScreen(game, "YOU WON!", game_mode.getScore()));
	}
}
