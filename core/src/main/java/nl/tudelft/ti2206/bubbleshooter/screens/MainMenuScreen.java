package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.core.Launch;
import nl.tudelft.ti2206.bubbleshooter.utils.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Rectangle;

/**
 * The actual menu screen, which shows the various buttons to choose from.
 * Such buttons include:
 * - Starting game
 * - Options menu
 * - Viewing high scores
 * @author group-15
 *
 */
public class MainMenuScreen extends ScreenAdapter {
	Button play;
	Launch game;
	
	/**
	 * The menu screen calls the {@link #create} method estabilishing the stage.
	 * @param game the current game session
	 */
	public MainMenuScreen( Launch game){
		this.game = game;
		this.play = new Button(
				new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 25, 200, 50),
				new Color(1, 0, 0, 1)
		);
	}
	
	/**
	 * Render the stage.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		update();
		game.batch.begin();
		play.draw(game.batch);
		game.batch.end();
	}

	private void update() {
        boolean left_down = Gdx.input.justTouched();
		if (left_down) {
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			if (play.hit(x, y)) {
				System.out.println("Hit!");
//				this.game.setScreen(new BubbleShooterScreen(game));
			}
		}
	}

}
