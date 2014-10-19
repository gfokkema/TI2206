package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BelowLineConditionTest {
	@Mock EndingCondition wrappedCondition;
	@Mock Grid grid;
	BelowLineCondition below;

	@Before
	public void setUp() {
		below = new BelowLineCondition(wrappedCondition);
	}

	@Test
	public void testLosingCondition() {
		when(grid.bubbleBelowLine()).thenReturn(true);
		below.check(grid);
		verify(wrappedCondition).lost();
		verify(wrappedCondition, never()).won();
	}

	@Test
	public void testContinueCondition() {
		when(grid.bubbleBelowLine()).thenReturn(false);
		below.check(grid);
		verify(wrappedCondition, never()).lost();
		verify(wrappedCondition, never()).won();
	}
}
