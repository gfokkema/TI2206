package nl.tudelft.ti2206.bubbleshooter.mode;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;

import static org.mockito.Matchers.anyFloat;

@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerModeTest {
	private SinglePlayerMode mode;
	
	@Mock Board board;
	@Mock Cannon cannon;
	@Mock EndingCondition end;
	
	@Before
	public void setUp() {
		mode = new SinglePlayerMode(end, board, cannon);
		Mockito.when(end.check(mode)).thenReturn(false);
	}
	
	@Test
	public void testUpdate() {
		assertTrue(mode.update(.02f));
	}
	
	@Test
	public void testCannon() {
		mode.cannonLeft(true);
		mode.update(.02f);
		Mockito.verify(cannon).left(anyFloat());
	}

	@Test
	public void testDrawables() {
		
	}

}
