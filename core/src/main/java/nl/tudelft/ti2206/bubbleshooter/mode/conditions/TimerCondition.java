package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import java.time.Duration;
import java.time.ZonedDateTime;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public class TimerCondition extends EndingDecorator {
	private static final long serialVersionUID = 5765501184371624635L;
	private Duration gameLength;
	private ZonedDateTime epoch;

	public TimerCondition(EndingCondition end, Duration gameLength) {
		super(end);
		this.setDuration(gameLength);
	}

	protected Duration getDeltaTime() {
		return Duration.between(epoch, ZonedDateTime.now());
	}

	protected void setDuration(Duration newGameLength) {
		this.gameLength = newGameLength;
		this.epoch = ZonedDateTime.now();
	}

	@Override
	public void check(Grid grid) {
		Duration deltaTime = getDeltaTime();
		if (gameLength.compareTo(deltaTime) < 0) {
			this.lost();
			return;
		}
		statsObs.updateTimer(gameLength.minus(deltaTime));
		super.check(grid);
	}
}
