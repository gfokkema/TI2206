package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BelowLineConditionTest {
	@Mock EndingCondition wrappedCondition;
	@Mock Board board;
	BelowLineCondition below;

	@Before
	public void setUp() {
		below = new BelowLineCondition(wrappedCondition);
	}

	@Test
	public void testLosingCondition() {
		when(board.bubbleBelowLine()).thenReturn(true);
		below.check(board);
		verify(wrappedCondition).lost();
		verify(wrappedCondition, never()).won();
	}

	@Test
	public void testContinueCondition() {
		when(board.bubbleBelowLine()).thenReturn(false);
		below.check(board);
		verify(wrappedCondition, never()).lost();
		verify(wrappedCondition, never()).won();
	}
}
