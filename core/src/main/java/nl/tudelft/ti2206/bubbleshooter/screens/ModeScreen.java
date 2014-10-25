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
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.TimerCondition;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUIBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ModeScreen extends AbstractScreen {

	public ModeScreen(BubbleShooter game) {
		super(game);
		
		Label message = new Label("Choose your mode!", labelStyle);
		TextButton zenplay = new TextButton("Zen", buttonStyle);
		TextButton arcade = new TextButton("Arcade", buttonStyle);
		
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
		
		arcade.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundEngine.getSoundEngine().play(SoundID.BUTTON);
				
				EndingCondition basic = new BasicCondition();
				EndingCondition belowLine = new BelowLineCondition(basic);
				EndingCondition timed = new TimerCondition(belowLine, Duration.ofMinutes(2));
				EndingCondition gridEmpty = new EmptyGridCondition(timed);
				BSMode single = new SinglePlayerMode(gridEmpty, new ArcadeBoardFactory());
				
				GameUIBuilder gub = new GameUIBuilder(game.font);
				gub.addSinglePlayerStatsBar(timed, single.getScore());
				game.setScreen(new BubbleShooterScreen(game, single, gub.build()));
			}
		});
		
		table.add(message).expandX().center().row();
		table.add(zenplay).expandX().center().row();
		table.add(arcade).expandX().center().row();
	}
	
	/**
	 * Render the things onto the screen.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(new MainMenuScreen(game));
	}
}
