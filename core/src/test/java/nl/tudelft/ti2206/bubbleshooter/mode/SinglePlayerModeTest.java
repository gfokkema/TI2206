package nl.tudelft.ti2206.bubbleshooter.mode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerModeTest {
	private SinglePlayerMode mode;
	
	@Mock Board board;
	@Mock Cannon cannon;
	@Mock Collection<Bubble> colorgroup;
	@Mock EndingCondition end;
	@Mock Projectile bubble;
	@Mock StatsObserver obs;
	
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
		assertEquals(cannon, mode.getCannon());
	}
	
	@Test
	public void testCannonLeft() {
		mode.cannonLeft(true);
		mode.update(.02f);
		Mockito.verify(cannon).left(anyFloat());
	}
	
	@Test
	public void testCannonRight() {
		mode.cannonRight(true);
		mode.update(.02f);
		Mockito.verify(cannon).right(anyFloat());
	}
	
	@Test
	public void testSetProjectile() {
		mode.setProjectile(bubble);
		assertEquals(bubble, mode.getProjectile());
		mode.update(.02f);
		Mockito.verify(bubble).move();
	}
	
	@Test
	public void testCollideProjectile() {
		Mockito.when(board.collides(bubble)).thenReturn(true);
		
		mode.setProjectile(bubble);
		mode.update(.02f);
		Mockito.verify(board).add(bubble);
	}
	
	@Test
	public void testCollideProjectileOccupied() {
		Mockito.when(board.collides(bubble)).thenReturn(true);
		Mockito.when(board.add(bubble)).thenReturn(-1);
		
		mode.setProjectile(bubble);
		mode.update(.02f);
		
		Mockito.verify(board, never()).getColorGroup(anyInt());
		Mockito.verify(board, never()).getDisconnectedGroup();
		Mockito.verify(board, never()).removeAll(any());
	}
	
	@Test
	public void testColorGroup() {
		Mockito.when(board.collides(bubble)).thenReturn(true);
		Mockito.when(colorgroup.size()).thenReturn(5);
		Mockito.when(board.getColorGroup(anyInt())).thenReturn(colorgroup);
		
		mode.addStatsObserver(obs);
		mode.setProjectile(bubble);
		mode.update(.02f);
		Mockito.verify(board).add(bubble);
		Mockito.verify(board).removeAll(colorgroup);
	}
	
	@Test
	public void testEndCondition() {
		Mockito.when(end.check(mode)).thenReturn(true);
		
		assertFalse(mode.update(.02f));
	}

	@Test
	public void testDrawables() {
		
	}
}
