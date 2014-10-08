package nl.tudelft.ti2206.bubbleshooter.mode;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TimerConditionTest {
	@Mock StatsObserver statsObs;
	@Mock EndingCondition wrappedCondition;
	TimerCondition timerBasic;

	@Before
	public void setUp() {
		timerBasic = new TimerCondition(wrappedCondition, Duration.ofMillis(1000));
		timerBasic.addStatsObserver(statsObs);
	}

	/**
	 * Test whether the time that's drawn is above zero.
	 */
	@Test
	public void testStatsObserverDrawTimer() {
		timerBasic.check(any());
		verify(statsObs).drawTimer(gt(Duration.ofMillis(0)));
	}

	/**
	 * Test whether the time is not drawn when it's below zero.
	 */
	@Test
	public void testStatsObserverDrawTimerAboveZero() {
		timerBasic.setDuration(Duration.ofMillis(-1));
		timerBasic.check(null);
		verify(statsObs, never()).drawTimer(any());
	}

	@Test
	public void testContinueCondition() {
		timerBasic.check(any());
		verify(wrappedCondition, never()).won();
		verify(wrappedCondition, never()).lost();
	}

	@Test
	public void testLosingCondition() throws InterruptedException {
		timerBasic.setDuration(Duration.ofMillis(-1));
		timerBasic.check(null);
		verify(wrappedCondition).lost();
		verify(wrappedCondition, never()).won();
	}

}
