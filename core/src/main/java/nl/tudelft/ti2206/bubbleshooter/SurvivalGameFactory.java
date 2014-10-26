package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.SurvivalBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.ZenBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.SurvivalMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;

/**
 * This class is a concrete {@link GameFactory} class that creates a {@link SurvivalMode}.
 * It specializes the {@link SPGameFactory} by setting up all needed values for a {@link SurvivalMode} game.
 */
public class SurvivalGameFactory extends SPGameFactory {
	Score score;

	/**
	 * Instantiates a new {@link SurvivalGameFactory} using the given {@link BubbleShooter} instance.
	 * @param bs	{@link BubbleShooter} context for this {@link SurvivalGameFactory}
	 */
	public SurvivalGameFactory(BubbleShooter bs) {
		super(bs);
		end = getEndingCondition();
		score = new Score(0);
	}
	
	/**
	 * This method creates and returns a {@link SurvivalMode}.
	 * @return	{@link GameMode} that was created
	 */
	@Override
	public GameMode createMode() {
		SurvivalMode single = new SurvivalMode(end, getBoardFactory().makeLevels(), score);
		setInputProcessor(single);
		return single;
	}

	/**
	 * This method creates and returns a {@link ZenBoardFactory} that
	 * can be used for the {@link Grid} in a {@link SurvivalMode}.
	 * @return	{@link BoardFactory} that was created
	 */
	@Override
	protected BoardFactory getBoardFactory() {
		return new SurvivalBoardFactory();
	}

	/**
	 * This method creates and returns a {@link EndingCondition} that can be used in a {@link SurvivalMode} game.
	 * The {@link EndingCondition} consists of:
	 * - BelowLineCondition
	 * - EmptyGridCondition
	 * @return	{@link EndingCondition} that was created
	 */
	@Override
	protected EndingCondition getEndingCondition() {
		return new BelowLineCondition(new EmptyGridCondition(new BasicCondition()));
	}
	
	/**
	 * This method creates and returns a {@link GameUI} for {@link SurvivalMode} games.
	 * @return	{@link GameUI} that was created
	 */
	@Override
	public GameUI createUI() {
		gub.addSinglePlayerStatsBar(end, score);
		return gub.build();
	}
}
