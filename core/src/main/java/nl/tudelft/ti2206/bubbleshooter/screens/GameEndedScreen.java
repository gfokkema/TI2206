package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SkinID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;
import nl.tudelft.ti2206.bubbleshooter.score.HighScore;
import nl.tudelft.ti2206.bubbleshooter.score.Score;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
	public GameEndedScreen(BubbleShooter game, String message, Score score) {
		super(game);
		
		Label messageLabel = new Label(message, labelStyle);
		messageLabel.setScale(5);
		Label scoreLabel = new Label("Your score: " + score.getScore(), labelStyle);
		Label info = new Label("Press ESC to return", labelStyle);
		
		Label highscoreLabel = new Label("You've entered the hall of fame!", labelStyle);
		Label nameLabel = new Label("Please enter your name:", labelStyle);
		
		TextField nameField = new TextField("", textStyle);
		Table nameFieldTable = new Table();
		nameFieldTable.setBackground(Assets.getAssets().get(SkinID.TEXTFIELD));
		nameFieldTable.add(nameField).padLeft(20).padRight(20).center().row();
		
		TextButton submitButton = new TextButton("Submit", buttonStyle);
		
		table.add(messageLabel).expandX().center().row();
		table.add(scoreLabel).expandX().center().row();
		
		if (game.scores.isHighScore(score)) {
			submitButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					SoundEngine.getSoundEngine().play(SoundID.BUTTON);
					game.scores.addScore(new HighScore(score, nameField.getText()));
					game.setScreen(new HighScoreScreen(game));
				}
			});
			
			table.add(highscoreLabel).spaceTop(50).expandX().center().row();
			table.add(nameLabel).expandX().center().row();
			table.add(nameFieldTable).expandX().center().row();
			table.add(submitButton).expandX().center().row();
		} else {
			table.add(info).expandX().center().row();
		}
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
