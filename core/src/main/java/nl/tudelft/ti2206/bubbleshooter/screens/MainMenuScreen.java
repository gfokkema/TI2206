package nl.tudelft.ti2206.bubbleshooter.screens;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.ArcadeBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;
import nl.tudelft.ti2206.bubbleshooter.engine.ZenBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.TimerCondition;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUIBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
		
		TextButton zenplay = new TextButton("Zen", buttonStyle);
		TextButton singleplay = new TextButton("Single player", buttonStyle);
		TextButton multiplay = new TextButton("Multi player", buttonStyle);
		TextButton highscore = new TextButton("Highscores", buttonStyle);
		TextButton options = new TextButton("Options", buttonStyle);
		TextButton quit = new TextButton("Quit", buttonStyle);

		zenplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				
				EndingCondition basic = new BasicCondition();
				EndingCondition belowLine = new BelowLineCondition(basic);
				BSMode single = new SinglePlayerMode(belowLine, new ZenBoardFactory());
				
				GameUIBuilder gub = new GameUIBuilder(game.font);
				gub.addSinglePlayerStatsBar(belowLine, single.getScore());
				game.setScreen(new BubbleShooterScreen(game, single, gub.build()));
			}
		});
		singleplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				
				EndingCondition basic = new BasicCondition();
				EndingCondition belowLine = new BelowLineCondition(basic);
				EndingCondition timed = new TimerCondition(belowLine, Duration.ofMinutes(2));
				BSMode single = new SinglePlayerMode(timed, new ArcadeBoardFactory());
				
				GameUIBuilder gub = new GameUIBuilder(game.font);
				gub.addSinglePlayerStatsBar(timed, single.getScore());
				game.setScreen(new BubbleShooterScreen(game, single, gub.build()));
			}
		});
		multiplay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				game.setScreen(new MultiPlayerScreen(game));
			}
		});
		highscore.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				game.setScreen(new HighScoreScreen(game));
			}
		});
		options.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				game.setScreen(new OptionsScreen(game));
			}
		});
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				Gdx.app.exit();
			}
		});

		table.add(zenplay).expandX().center().row();
		table.add(singleplay).expandX().center().row();
		table.add(multiplay).expandX().center().row();
		table.add(highscore).expandX().center().row();
		table.add(options).expandX().center().row();
		table.add(quit).expandX().center().row();
	}
}
