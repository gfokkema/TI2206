package nl.tudelft.ti2206.bubbleshooter.mode;

import java.time.Duration;

public class TimerCondition implements EndingCondition {
	private Duration gameLength;

	public TimerCondition(Duration gameLength) {
		this.gameLength = gameLength;
	}

	@Override
	public boolean check(BSMode mode) {
		// TODO Auto-generated method stub
		return false;
	}

}
