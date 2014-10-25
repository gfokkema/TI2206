package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.ZenBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.SurvivalMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;

public class SurvivalGameFactory extends SPGameFactory {
	Score score;

	public SurvivalGameFactory(BubbleShooter bs) {
		super(bs);
		end = getEndingCondition();
		score = new Score(0);
	}
	
	@Override
	public GameMode createMode() {
		GameMode single = new SurvivalMode(end, getBoardFactory().makeLevels(), score);
		setInputProcessor(single);
		return single;
	}

	@Override
	protected BoardFactory getBoardFactory() {
		return new ZenBoardFactory();
	}

	@Override
	protected EndingCondition getEndingCondition() {
		return new BelowLineCondition(new EmptyGridCondition(new BasicCondition()));
	}
	
	@Override
	public GameUI createUI() {
		gub.addSinglePlayerStatsBar(end, score);
		return gub.build();
	}
}
