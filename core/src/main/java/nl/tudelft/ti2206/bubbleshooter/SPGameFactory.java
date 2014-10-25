package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;

public abstract class SPGameFactory extends GameFactory {
	private Score score;
	private EndingCondition end;

	public SPGameFactory(BubbleShooter bs) {
		super(bs);
		score = new Score(0);
	}

	@Override
	public GameMode createMode() {
		end = getEndingCondition();
		SinglePlayerMode single = new SinglePlayerMode(end, getBoardFactory().makeLevels(), score);
		setInputProcessor(single);
		return single;
	}

	@Override
	public GameUI createUI() {
		gub.addSinglePlayerStatsBar(end, score);
		return gub.build();
	}
}
