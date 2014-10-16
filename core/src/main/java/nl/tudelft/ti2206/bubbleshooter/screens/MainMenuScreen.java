package nl.tudelft.ti2206.bubbleshooter.screens;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.ArcadeBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SkinID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.TimerCondition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The actual menu screen, which shows the various buttons to choose from.
 * Such buttons include:
 * - Starting game
 * - Options menu
 * - Viewing high scores
 * @author group-15
 *
 */
public class MainMenuScreen extends AbstractScreen {
	/**
	 * Variable initialization.
	 * Set the title of the screen.
	 * Set the BGM used.
	 */
	public static final String title = "Bubble Shooter";

	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session.
	 */
	public MainMenuScreen(BubbleShooter game) {
		super(game);
		
		TextButtonStyle style = new TextButtonStyle(game.assets.get(SkinID.BUTTON),
												game.assets.get(SkinID.BUTTON),
													game.assets.get(SkinID.BUTTON),
												game.font);
	//	TextButtonStyle style = new TextButtonStyle();
	//	style.font = game.font;
		style.over = game.assets.get(SkinID.BUTTON);
		
		TextButton singleplay = new TextButton("Single player", style);
		singleplay.setSize(1, 1);
		TextButton multiplay = new TextButton("Multi player", style);
		multiplay.setScale(0,5);
		TextButton options = new TextButton("Options", style);
		options.setSize(1, 1);
		TextButton quit = new TextButton("Quit", style);
		quit.setSize(1, 1);
		
		singleplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				EndingCondition basic = new BasicCondition();
				EndingCondition belowLine = new BelowLineCondition(basic);
				EndingCondition timed = new TimerCondition(belowLine, Duration.ofMinutes(2));
				game.setScreen(new BubbleShooterScreen(game, new SinglePlayerMode(timed, new ArcadeBoardFactory())));
			}
		});
		multiplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.setScreen(new MultiPlayerScreen(game));
			}
		});
		options.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				game.setScreen(new OptionsScreen(game));
			}
		});
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				Gdx.app.exit();
			}
		});
		
		table.add(singleplay).expandX().center().row();
		table.add(multiplay).expandX().center().row();
		table.add(options).expandX().center().row();
		table.add(quit).expandX().center().row();
	}
}
