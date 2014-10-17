package nl.tudelft.ti2206.bubbleshooter.screens;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.SortedSet;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.mode.MultiPlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.util.FileHighscore;
import nl.tudelft.ti2206.bubbleshooter.util.HighScore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The screen shown when joining a multi-player game.
 * @author group-15
 *
 */
public class HighScoreScreen extends AbstractScreen {
	private ObjectInputStream br = null;
	private ObjectOutputStream bw = null;
	
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
	/**
	 * Implements our client socket wih datastreams.
	 * @param ip
	 */
	public void connect(String ip) {
		try {
			Socket socket = new Socket(ip, 8008);

			bw = new ObjectOutputStream(socket.getOutputStream());
			bw.flush();
			br = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("CLIENT CONNECTED!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Gdx.app.exit();
		}
		EndingCondition basic = new BasicCondition();
		EndingCondition belowLine = new BelowLineCondition(basic);
		game.setScreen(new BubbleShooterScreen(game, new MultiPlayerMode(belowLine, br, bw)));
	}
}