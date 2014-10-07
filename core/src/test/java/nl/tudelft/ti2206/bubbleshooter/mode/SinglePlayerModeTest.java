package nl.tudelft.ti2206.bubbleshooter.mode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
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
	
	@Mock ArrayList<Board> boards;
	@Mock Board board;
	@Mock BoardFactory factory;
	@Mock Cannon cannon;
	@Mock Collection<Bubble> colorgroup;
	@Mock EndingCondition end;
	@Mock Projectile bubble;
	@Mock Projectile cbubble;
	@Mock StatsObserver obs;
	
	@Before
	public void setUp() throws IOException {
		Mockito.when(boards.get(0)).thenReturn(board);
		Mockito.when(cannon.getProjectile()).thenReturn(cbubble);
		Mockito.when(end.check(mode)).thenReturn(-1);
		
		Mockito.when(factory.parseFile(any(String.class))).thenReturn(boards);
		Mockito.when(factory.parseLevel(any())).thenReturn(board);
		
		mode = new SinglePlayerMode(end, factory, cannon);
		mode.addStatsObserver(obs);
	}
	
	@Test
	public void testUpdate() {
		assertEquals(mode.update(.02f), 0);
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
		
		mode.setProjectile(bubble);
		mode.update(.02f);
		Mockito.verify(board).add(bubble);
		Mockito.verify(board).removeAll(colorgroup);
	}
	
	@Test
	public void testEndCondition() {
		Mockito.when(end.check(mode)).thenReturn(-1);
		
		assertEquals(-1, mode.update(.02f));
	}

	@Test
	public void testDrawables() {
		
	}
}
