package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.ZenBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;

/**
 * This class is a concrete {@link GameFactory} class that creates a {@link SinglePlayerMode} game.
 * It specializes the {@link SPGameFactory} by setting up all needed values and using the {@link ZenBoardFactory}.
 */
public class ZenGameFactory extends SPGameFactory {
	/**
	 * Instantiates a new {@link ZenGameFactory} using the given {@link BubbleShooter} instance.
	 * @param bs	{@link BubbleShooter} context for this {@link ZenGameFactory}
	 */
	public ZenGameFactory(BubbleShooter bs) {
		super(bs);
	}

	/**
	 * This method creates and returns a {@link ZenBoardFactory} that
	 * can be used for the {@link Grid} in a {@link SinglePlayerMode} game.
	 * @return	{@link BoardFactory} that was created
	 */
	@Override
	protected BoardFactory getBoardFactory() {
		return new ZenBoardFactory();
	}

	/**
	 * This method creates and returns a {@link EndingCondition} that can be used in a {@link SinglePlayerMode} game.
	 * The {@link EndingCondition} consists of:
	 * - BelowLineCondition
	 * - EmptyGridCondition
	 * @return	{@link EndingCondition} that was created
	 */
	@Override
	protected EndingCondition getEndingCondition() {
		return new BelowLineCondition(new EmptyGridCondition(new BasicCondition()));
	}
}
