package nl.tudelft.ti2206.bubbleshooter.mode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyFloat;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
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
	
	@Mock BoardFactory factory;
	@Mock Cannon cannon;
	@Mock Collection<Bubble> colorgroup;
	@Mock EndingCondition end;
	@Mock Iterator<Grid> grid_it;
	@Mock Score score;
	@Mock Grid grid;
	@Mock Projectile bubble;
	@Mock Projectile cbubble;
	@Mock StatsObserver obs;
	
	@Before
	public void setUp() throws IOException {
		Mockito.when(cannon.getProjectile()).thenReturn(cbubble);
		Mockito.when(factory.makeLevels()).thenReturn(grid_it);
		Mockito.when(grid_it.next()).thenReturn(grid);
		
		mode = new SinglePlayerMode(end, factory.makeLevels(), score);
		mode.getScore().addStatsObserver(obs);
		mode.cannon = cannon;
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
	
	/*
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
	
	*/

	@Test
	public void testDrawables() {
		
	}
}
