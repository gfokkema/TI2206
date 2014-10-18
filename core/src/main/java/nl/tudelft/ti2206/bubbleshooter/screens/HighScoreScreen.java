package nl.tudelft.ti2206.bubbleshooter.screens;

import java.util.Iterator;
import java.util.SortedSet;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.util.FileHighscore;
import nl.tudelft.ti2206.bubbleshooter.util.HighScore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

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
		
		LabelStyle labelstyle = new LabelStyle(game.font, Color.WHITE);
		Label label = new Label("These are the high scores for Arcade Mode.", labelstyle);
		
		table.add(label).expandX().center().row();
		
		FileHighscore filescores = new FileHighscore();
		filescores.addScore("Gerlof", 20);
		filescores.addScore("Adam", 100);
		filescores.addScore("Skip", 200);
		SortedSet<HighScore> scores = filescores.loadScoreFile();
		Iterator<HighScore> it = scores.iterator();
		while(it.hasNext()) {
			HighScore score = it.next();
			Label l = new Label(score.getName() + ":   " + score.getHighScore(), labelstyle);
			table.add(l).expandX().center().row();
		}
	}
	
	/**
	 * Implements the escape key on your keyboard for our UI which brings the user back to the main menu.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}