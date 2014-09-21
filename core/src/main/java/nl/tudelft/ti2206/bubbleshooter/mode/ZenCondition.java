package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public class ZenCondition implements EndingCondition {

	@Override
	public boolean check(BSMode mode) {
		return mode.board.isEmpty();
	}

	@Override
	public void addStatsObserver(StatsObserver o) {
	}

}
