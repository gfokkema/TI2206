package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.Iterator;
import java.util.NavigableSet;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.util.HighScore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * The screen shown when joining a multi-player game.
 * @author group-15
 *
 */
public class HighScoreScreen extends AbstractScreen {
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public HighScoreScreen(BubbleShooter game) {
		super(game);
		
		Label label = new Label("These are the high scores for Arcade Mode.", labelStyle);
		table.add(label).expandX().center().row();
		
		NavigableSet<HighScore> scores = game.scores.loadScoreFile().descendingSet();
		Iterator<HighScore> it = scores.iterator();
		while(it.hasNext()) {
			HighScore score = it.next();
			Label l = new Label(score.getName() + ":   " + score.getScore() + ":   " + score.getLevel(), labelStyle);
			table.add(l).expandX().center().row();
		}
	}
	
	/**
	 * Implements the escape key on your keyboard for our UI which brings the user back to the main menu.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(new MainMenuScreen(game));
	}
}