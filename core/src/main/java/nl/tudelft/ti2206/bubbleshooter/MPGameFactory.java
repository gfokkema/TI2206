package nl.tudelft.ti2206.bubbleshooter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.MPBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.MultiPlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;
import nl.tudelft.ti2206.bubbleshooter.ui.StatsBar;

/**
 * This class is a concrete {@link GameFactory} class that creates a {@link MultiPlayerMode} game.
 * It specializes the {@link GameFactory} by setting up all needed values for a {@link MultiPlayerMode} game with:
 * - 2 {@link Grid}s
 * - 2 {@link Cannon}s
 * - 2 {@link Projectile}s
 * - 2 {@link StatsBar}s
 * - networking streams
 */
public class MPGameFactory extends GameFactory {
	Score ownScore;
	Score oppScore;
	ObjectInputStream in;
	ObjectOutputStream out;

	/**
	 * Instantiates a new {@link MPGameFactory} using the given {@link BubbleShooter} instance and
	 * the specified networking streams for in- and output.
	 * @param bs	{@link BubbleShooter} context for this {@link ZenGameFactory}
	 * @param in	networking stream - in
	 * @param out	networking stream - out
	 */
	public MPGameFactory(BubbleShooter bs, ObjectInputStream in, ObjectOutputStream out) {
		super(bs);
		this.end = getEndingCondition();
		this.ownScore = new Score(0);
		this.oppScore = new Score(0);
		this.in = in;
		this.out = out;
	}

	/**
	 * This method creates and returns a {@link MultiPlayerMode}.
	 * @return	{@link GameMode} that was created
	 */
	@Override
	public GameMode createMode() {
		MultiPlayerMode multi = new MultiPlayerMode(end, getBoardFactory().makeLevels(), ownScore, oppScore, in, out);
		setInputProcessor(multi);
		return multi;
	}

	/**
	 * This method creates and returns a {@link GameUI} for {@link MultiPlayerMode} games. It shows stats of the
	 * opponent as well as the local player.
	 * @return	{@link GameUI} that was created
	 */
	@Override
	public GameUI createUI() {
		gub.addMultiPlayerStatsBars(end, ownScore, oppScore);
		return gub.build();
	}

	/**
	 * This method creates and returns a {@link MPBoardFactory} that
	 * can be used for the {@link Grid} in a {@link MultiPlayerMode}.
	 * @return	{@link BoardFactory} that was created
	 */
	@Override
	protected BoardFactory getBoardFactory() {
		return new MPBoardFactory();
	}

	/**
	 * This method creates and returns a {@link EndingCondition} that can be used in a {@link MultiPlayerMode} game.
	 * The {@link EndingCondition} consists of:
	 * - {@link BelowLineCondition}
	 * - {@link EmptyGridCondition}
	 * @return	{@link EndingCondition} that was created
	 */
	@Override
	protected EndingCondition getEndingCondition() {
		return new EmptyGridCondition(new BelowLineCondition(new BasicCondition()));
	}
}
