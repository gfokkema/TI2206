package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * The screen shown when the game has ended: lose/win.
 * @author group-15
 *
 */
public class GameEndedScreen extends AbstractScreen {

	/**
	 * The Game ending screen.
	 * @param game the current {@link BubbleShooter} game.
	 * @param message the message shown.
	 * @param score the final score.
	 */
	public GameEndedScreen(BubbleShooter game, String message, int score) {
		super(game);
		
		Label messageLabel = new Label(message, labelStyle);
		messageLabel.setScale(5);
		Label scoreLabel = new Label("Your score: " + score, labelStyle);
		Label info = new Label("Press ESC to return", labelStyle);
		
		table.add(messageLabel).expandX().center().row();
		table.add(scoreLabel).expandX().center().row();
		table.add(info).expandX().center().row();
	}

	/**
	 * Render the things onto the screen.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}
