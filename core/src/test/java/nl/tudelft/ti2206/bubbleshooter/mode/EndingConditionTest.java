package nl.tudelft.ti2206.bubbleshooter.mode;

import java.time.Duration;

import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;

import org.junit.Before;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

/**
 * Test certain requirements which should hold for all kinds of
 * EndingConditions.
 * @author group-15
 *
 */
@RunWith(Theories.class)
public class EndingConditionTest {
	private EndingObserver obs;

	@DataPoint
	public static EndingCondition basic = new BasicCondition();
	@DataPoint
	public static EndingCondition belowBasic = new BelowLineCondition(new BasicCondition());
	@DataPoint
	public static EndingCondition timerBasic = new TimerCondition(new BasicCondition(), Duration.ofSeconds(1));
	@DataPoint
	public static EndingCondition timerBelowBasic = new TimerCondition(new BelowLineCondition(new BasicCondition()), Duration.ofSeconds(1));
	@DataPoint
	public static EndingCondition belowTimerBasic = new BelowLineCondition(new TimerCondition(new BasicCondition(), Duration.ofSeconds(1)));

	@Before
	public void setUp() {
		obs = mock(EndingObserver.class);
	}

	@Theory
	public void testLost(EndingCondition end) {
		end.addEndingObserver(obs);
		end.lost();
		verify(obs).lost();
	}

	@Theory
	public void testWon(EndingCondition end) {
		end.addEndingObserver(obs);
		end.won();
		verify(obs).won();
	}
}
