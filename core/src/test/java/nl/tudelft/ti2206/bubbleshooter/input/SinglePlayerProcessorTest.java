package nl.tudelft.ti2206.bubbleshooter.input;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.Input.Keys;

/**
 * Test input processing for Single Player keybindings
 */
@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerProcessorTest {
	private SinglePlayerProcessor input;
	
	@Mock GameMode mode;
	@Mock Cannon cannon;
	@Mock Grid grid;
	@Mock Projectile projectile;

	
	/**
	 * Initialize some common mocks and variables.
	 */
	@Before
	public void setUp() {
		input = new SinglePlayerProcessor(mode);
		Mockito.when(mode.getCannon()).thenReturn(cannon);
		Mockito.when(mode.getGrid()).thenReturn(grid);
	}

	/**
	 * Test shooting the cannon from our inputprocessor.
	 */
	@Test
	public void testShoot() {
		input.cannonShoot();
		Mockito.verify(mode, times(3)).getCannon();
//		Mockito.verify(cannon).shoot();
	}
	
	/**
	 * Test shooting the cannon from our inputprocessor while a projectile is already in play.
	 */
	@Test
	public void testShootProjectileInPlay() {
		Mockito.when(mode.getProjectile()).thenReturn(projectile);
		input.cannonShoot();
//		Mockito.verify(cannon, never()).shoot();
	}
	
	/**
	 * Test rotating the cannon to the left from our inputprocessor.
	 */
	@Test
	public void testCannonLeft() {
		input.cannonLeft();
		Mockito.verify(mode).cannonLeft(true);
		
		input.cannonStopLeft();
		Mockito.verify(mode).cannonLeft(false);
	}
	
	/**
	 * Test rotating the cannon to the right from our inputprocessor.
	 */
	@Test
	public void testCannonRight() {
		input.cannonRight();
		Mockito.verify(mode).cannonRight(true);
		
		input.cannonStopRight();
		Mockito.verify(mode).cannonRight(false);
	}
	
	/**
	 * Test the keybinding for shooting the cannon.
	 */
	@Test
	public void testSpaceDown() {
		input.keyDown(Keys.SPACE);
//		Mockito.verify(cannon).shoot();
	}
	
	/**
	 * Test the keybinding for rotating the cannon to the left.
	 */
	@Test
	public void testLeftDown() {
		input.keyDown(Keys.LEFT);
		Mockito.verify(mode).cannonLeft(true);
	}
	
	/**
	 * Test the keybinding for rotating the cannon to the right.
	 */
	@Test
	public void testRightDown() {
		input.keyDown(Keys.RIGHT);
		Mockito.verify(mode).cannonRight(true);
	}
	
	/**
	 * Test the keybinding for stopping rotating the cannon to the left.
	 */
	@Test
	public void testLeftUp() {
		input.keyUp(Keys.LEFT);
		Mockito.verify(mode).cannonLeft(false);
	}
	
	/**
	 * Test the keybinding for stopping rotating the cannon to the right.
	 */
	@Test
	public void testRightUp() {
		input.keyUp(Keys.RIGHT);
		Mockito.verify(mode).cannonRight(false);
	}
	
	/**
	 * Test an invalid keydown binding.
	 */
	@Test
	public void testInvalidKeyDown() {
		input.keyDown(Keys.BACKSLASH);
		Mockito.verify(mode, never()).cannonLeft(anyBoolean());
		Mockito.verify(mode, never()).cannonRight(anyBoolean());
//		Mockito.verify(cannon, never()).shoot();
	}
	
	/**
	 * Test an invalid keyup binding.
	 */
	@Test
	public void testInvalidKeyUp() {
		input.keyUp(Keys.BACKSLASH);
		Mockito.verify(mode, never()).cannonLeft(anyBoolean());
		Mockito.verify(mode, never()).cannonRight(anyBoolean());
//		Mockito.verify(cannon, never()).shoot();
	}
}
