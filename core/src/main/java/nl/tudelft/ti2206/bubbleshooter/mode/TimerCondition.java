package nl.tudelft.ti2206.bubbleshooter.mode;

import java.time.Duration;
import java.time.ZonedDateTime;

public class TimerCondition extends EndingCondition {
	private Duration gameLength;
	private ZonedDateTime epoch;

	public TimerCondition(Duration gameLength) {
		this.gameLength = gameLength;
		epoch = ZonedDateTime.now();
	}

	@Override
	public int check(BSMode mode) {
		Duration deltaTime = Duration.between(epoch, ZonedDateTime.now());
		if (mode.board.isEmpty()) return 1;
		if (gameLength.compareTo(deltaTime) < 0) return -1;
		obs.drawTimer(gameLength.minus(deltaTime));
		return 0;
	}

}
