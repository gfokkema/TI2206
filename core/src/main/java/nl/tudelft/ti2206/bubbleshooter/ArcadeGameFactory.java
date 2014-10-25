package nl.tudelft.ti2206.bubbleshooter;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.engine.ArcadeBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.TimerCondition;

public class ArcadeGameFactory extends SPGameFactory {
	private Duration gameLength;

	public ArcadeGameFactory(BubbleShooter bs, Duration gameLength) {
		super(bs);
		this.gameLength = gameLength;
	}

	@Override
	protected BoardFactory getBoardFactory() {
		return new ArcadeBoardFactory();
	}

	@Override
	protected EndingCondition getEndingCondition() {
		return new TimerCondition(new EmptyGridCondition(new BelowLineCondition(new BasicCondition())), gameLength);
	}
}
