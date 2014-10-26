package nl.tudelft.ti2206.bubbleshooter;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.engine.ArcadeBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.TimerCondition;

/**
 * This class is a concrete {@link GameFactory} class that creates a {@link SinglePlayerMode} game.
 * It specializes the {@link SPGameFactory} by setting up all needed values and using the {@link ArcadeBoardFactory}.
 */
public class ArcadeGameFactory extends SPGameFactory {
	private Duration gameLength;

	/**
	 * Instantiates a new {@link ZenGameFactory} using the given {@link BubbleShooter} instance.
	 * @param bs			{@link BubbleShooter} context for this {@link ZenGameFactory}
	 * @param gameLength	the time limit for this game
	 */
	public ArcadeGameFactory(BubbleShooter bs, Duration gameLength) {
		super(bs);
		this.gameLength = gameLength;
	}

	/**
	 * This method creates and returns a {@link ArcadeBoardFactory} that
	 * can be used for the {@link Grid} in a {@link SinglePlayerMode} game.
	 * @return	{@link BoardFactory} that was created
	 */
	@Override
	protected BoardFactory getBoardFactory() {
		return new ArcadeBoardFactory();
	}

	/**
	 * This method creates and returns a {@link EndingCondition} that can be used in a {@link SinglePlayerMode} game.
	 * The {@link EndingCondition} consists of:
	 * - {@link BelowLineCondition}
	 * - {@link EmptyGridCondition}
	 * - {@link TimerCondition}
	 * @return	{@link EndingCondition} that was created
	 */
	@Override
	protected EndingCondition getEndingCondition() {
		return new EmptyGridCondition(new TimerCondition(new BelowLineCondition(new BasicCondition()), gameLength));
	}
}
