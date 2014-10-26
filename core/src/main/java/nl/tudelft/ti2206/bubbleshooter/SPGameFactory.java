package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerMode;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;

/**
 * This is the abstract base class for all single player {@link GameFactory} classes.
 * It specializes the {@link GameFactory} by setting up some default values for single player games.
 */
public abstract class SPGameFactory extends GameFactory {
	private Score score;

	/**
	 * Instantiates a new {@link SPGameFactory} using the given {@link BubbleShooter} instance.
	 * @param bs	{@link BubbleShooter} context for this {@link SPGameFactory}
	 */
	public SPGameFactory(BubbleShooter bs) {
		super(bs);
		score = new Score(0);
	}

	/**
	 * This method creates and returns the default {@link SinglePlayerMode}.
	 * @return	{@link GameMode} that was created
	 */
	@Override
	public GameMode createMode() {
		end = getEndingCondition();
		SinglePlayerMode single = new SinglePlayerMode(end, getBoardFactory().makeLevels(), score);
		setInputProcessor(single);
		return single;
	}

	/**
	 * This method creates and returns a default {@link GameUI} for {@link SinglePlayerMode} games.
	 * @return	{@link GameUI} that was created
	 */
	@Override
	public GameUI createUI() {
		gub.addSinglePlayerStatsBar(end, score);
		return gub.build();
	}
}
