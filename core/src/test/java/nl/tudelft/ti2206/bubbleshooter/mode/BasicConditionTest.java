package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasicConditionTest {
	@Mock EndingObserver obs;
	@Mock Board board;
	@Mock BSMode mode;
	public EndingCondition basic;

	@Before
	public void setUp() {
		basic = new BasicCondition();
		basic.addEndingObserver(obs);
		mode.board = board;
	}

	@Test
	public void testWinningCondition() {
		when(board.isEmpty()).thenReturn(true);
		basic.check(this.board);
		verify(obs).won();
		verify(obs, never()).lost();
	}

	@Test
	public void testContinueCondition() {
		when(board.isEmpty()).thenReturn(false);
		basic.check(this.board);
		verify(obs, never()).lost();
		verify(obs, never()).won();
	}
}
