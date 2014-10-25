package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.ZenBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;

public class ZenGameFactory extends SPGameFactory {

	public ZenGameFactory(BubbleShooter bs) {
		super(bs);
	}

	@Override
	protected BoardFactory getBoardFactory() {
		return new ZenBoardFactory();
	}

	@Override
	protected EndingCondition getEndingCondition() {
		return new BelowLineCondition(new EmptyGridCondition(new BasicCondition()));
	}
}
