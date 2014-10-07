package nl.tudelft.ti2206.bubbleshooter.mode;

import java.time.Duration;
import java.time.ZonedDateTime;

public class TimerCondition extends EndingDecorator {
	private Duration gameLength;
	private ZonedDateTime epoch;

	public TimerCondition(EndingCondition end, Duration gameLength) {
		super(end);
		this.end = end;
		this.gameLength = gameLength;
		epoch = ZonedDateTime.now();
	}

	@Override
	public void check(BSMode mode) {
		Duration deltaTime = Duration.between(epoch, ZonedDateTime.now());
		if (gameLength.compareTo(deltaTime) < 0) this.lost();
		statsObs.drawTimer(gameLength.minus(deltaTime));
		super.check(mode);
	}

}
