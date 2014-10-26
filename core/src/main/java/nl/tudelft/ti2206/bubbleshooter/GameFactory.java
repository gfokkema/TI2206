package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUIBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * This is the abstract base class for all {@link GameFactory} classes.
 * These {@link GameFactory} classes are responsible for creating a {@link GameMode} class,
 * initialized with a {@link Score} object, {@link BoardFactory} and {@link GameUI}.
 */
public abstract class GameFactory {
	protected EndingCondition end;
	protected GameUIBuilder gub;

	/**
	 * Instantiates a new {@link GameFactory} using the given {@link BubbleShooter} instance.
	 * @param bs	{@link BubbleShooter} context for this {@link GameFactory}
	 */
	public GameFactory(BubbleShooter bs) {
		this.gub = new GameUIBuilder(bs.font);
	}
	
	/**
	 * This method creates and returns the {@link GameMode}.
	 * @return	{@link GameMode} that was created
	 */
	public abstract GameMode createMode();

	/**
	 * Create and return a {@link GameUI} for drawing the UI elements of the game.
	 * @return	{@link GameUI} that was created
	 */
	public abstract GameUI createUI();

	/**
	 * This method creates and returns a {@link BoardFactory} that
	 * can be used for the {@link Grid} in a {@link GameMode}.
	 * @return	{@link BoardFactory} that was created
	 */
	protected abstract BoardFactory getBoardFactory();

	/**
	 * This method creates and returns a {@link EndingCondition} that can be used in a {@link GameMode}.
	 * @return	{@link EndingCondition} that was created
	 */
	protected abstract EndingCondition getEndingCondition();

	/**
	 * This method sets the {@link InputProcessor} for a specific {@link GameMode}.
	 * @param mode	the {@link GameMode} that we want to set a processor for
	 */
	protected void setInputProcessor(GameMode mode) {
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(mode));
	}
}
