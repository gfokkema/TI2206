package nl.tudelft.ti2206.bubbleshooter.mode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Level;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.util.GameObserver;
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
	@Mock Level level;
	@Mock Grid grid;
	@Mock GridCell gridcell;
	@Mock Projectile bubble;
	@Mock Projectile cbubble;
	@Mock GameObserver gameobs;
	@Mock StatsObserver statsobs;
	
	@Before
	public void setUp() throws IOException {
		Mockito.when(cannon.getProjectile()).thenReturn(cbubble);
		Mockito.when(factory.makeLevels()).thenReturn(grid_it);
		Mockito.when(grid_it.next()).thenReturn(grid);
		Mockito.when(score.getLevel()).thenReturn(level);
		Mockito.when(level.getLevel()).thenReturn(1);
		
		mode = new SinglePlayerMode(end, factory.makeLevels(), score);
		mode.getScore().addStatsObserver(statsobs);
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
	public void testGrid() {
		assertEquals(grid, mode.getGrid());
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
		Mockito.when(grid.collides(bubble)).thenReturn(true);
		Mockito.when(grid.add(bubble)).thenReturn(gridcell);
		
		mode.setProjectile(bubble);
		mode.update(.02f);
		Mockito.verify(grid).add(bubble);
		Mockito.verify(cannon, times(2)).getProjectile();
		Mockito.verify(bubble).move();
	}
	
	@Test
	public void testCollideProjectileOccupied() {
		Mockito.when(grid.collides(bubble)).thenReturn(true);
		Mockito.when(grid.add(bubble)).thenReturn(null);
		
		mode.setProjectile(bubble);
		mode.update(.02f);
		
		Mockito.verify(cbubble, never()).move();
		assertNotEquals(cbubble, mode.getProjectile());
	}
	
	@Test
	public void testLostObserver() {
		mode.addGameObserver(gameobs);
		
		mode.lost();
		Mockito.verify(gameobs, times(1)).switchToLostScreen();
	}
	
	@Test
	public void testWonObserver() {
		mode.addGameObserver(gameobs);
		
		Mockito.when(grid_it.hasNext()).thenReturn(true);
		mode.won();
		Mockito.verify(gameobs, never()).switchToWonScreen();
		
		Mockito.when(grid_it.hasNext()).thenReturn(false);
		mode.won();
		Mockito.verify(gameobs, times(1)).switchToWonScreen();
	}
}
