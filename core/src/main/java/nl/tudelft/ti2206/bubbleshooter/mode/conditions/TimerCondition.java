package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import java.time.Duration;
import java.time.ZonedDateTime;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

/**
 * The {@link TimerCondition} checks whether a specified amount of time has
 * elapsed and notifies the {@link EndingObserver} when the game is won or lost.
 * The {@link TimerCondition} also notifies the {@link StatsObserver} when the
 * remaining amount of time has been updated.
 */
public class TimerCondition extends EndingDecorator {
	private static final long serialVersionUID = 5765501184371624635L;
	private Duration gameLength;
	private ZonedDateTime epoch;

	/**
	 * Creates a {@link TimerCondition} that decorates the specified {@link EndingCondition}.
	 * @param end			the specified {@link EndingCondition}
	 * @param gameLength	the duration of this {@link TimerCondition}
	 */
	public TimerCondition(EndingCondition end, Duration gameLength) {
		super(end);
		this.setDuration(gameLength);
	}

	/**
	 * Returns the amount of time that has elapsed.
	 * @return
	 */
	protected Duration getDeltaTime() {
		return Duration.between(epoch, ZonedDateTime.now());
	}

	/**
	 * Sets the duration of this {@link TimerCondition} to a new length.
	 * @param newGameLength	the new duration
	 */
	protected void setDuration(Duration newGameLength) {
		this.gameLength = newGameLength;
		this.epoch = ZonedDateTime.now();
	}

	/**
	 * Checks whether the specified duration has elapsed and notifies {@link EndingObserver}.
	 */
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
	
	/**
	 * When another {@link EndingCondition} has called the won method,
	 * we add a duration of 1 minute to this {@link TimerCondition}.
	 */
	@Override
	public void won() {
		gameLength = gameLength.plusMinutes(1);
		super.won();
	}
}
