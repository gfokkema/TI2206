package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public class ZenCondition implements EndingCondition {

	@Override
	public int check(BSMode mode) {
		return mode.board.isEmpty() ? 1 : 0;
	}

	@Override
	public void addStatsObserver(StatsObserver o) {
	}

}
