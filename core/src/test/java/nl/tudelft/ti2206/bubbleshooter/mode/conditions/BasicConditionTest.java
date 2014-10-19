package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasicConditionTest {
	@Mock EndingObserver obs;
	@Mock BSMode mode;
	@Mock Grid grid;
	public EndingCondition basic;

	@Before
	public void setUp() {
		basic = new BasicCondition();
		basic.addEndingObserver(obs);
	}

	@Test
	public void testWinningCondition() {
		when(grid.isEmpty()).thenReturn(true);
		basic.check(grid);
		verify(obs).won();
		verify(obs, never()).lost();
	}

	@Test
	public void testContinueCondition() {
		when(grid.isEmpty()).thenReturn(false);
		basic.check(grid);
		verify(obs, never()).lost();
		verify(obs, never()).won();
	}
}
