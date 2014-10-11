package nl.tudelft.ti2206.bubbleshooter.input;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * Test input processing for Single Player keybindings
 */
@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerProcessorTest {
	private SinglePlayerProcessor input;
	
	@Mock BSMode mode;
	@Mock Cannon cannon;
	@Mock Projectile projectile;
	@Mock Board board;
	@Mock AssetManager loader;
	@Mock Sound sound;
	private Assets assets;
	
	/**
	 * Initialize some common mocks and variables.
	 */
	@Before
	public void setUp() {
		assets = Assets.getAssets();
		assets.setAssetManager(loader);
		input = new SinglePlayerProcessor(mode);
		Mockito.when(loader.get(anyString())).thenReturn(sound);
		Mockito.when(mode.getCannon()).thenReturn(cannon);
		Mockito.when(mode.getBoard()).thenReturn(board);
	}

	/**
	 * Test shooting the cannon from our inputprocessor.
	 */
	@Test
	public void testShoot() {
		input.cannonShoot();
		Mockito.verify(mode, times(3)).getCannon();
		Mockito.verify(cannon).shoot();
	}
	
	/**
	 * Test shooting the cannon from our inputprocessor while a projectile is already in play.
	 */
	@Test
	public void testShootProjectileInPlay() {
		Mockito.when(mode.getProjectile()).thenReturn(projectile);
		input.cannonShoot();
		Mockito.verify(cannon, never()).shoot();
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
		Mockito.verify(cannon).shoot();
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
		Mockito.verify(cannon, never()).shoot();
	}
	
	/**
	 * Test an invalid keyup binding.
	 */
	@Test
	public void testInvalidKeyUp() {
		input.keyUp(Keys.BACKSLASH);
		Mockito.verify(mode, never()).cannonLeft(anyBoolean());
		Mockito.verify(mode, never()).cannonRight(anyBoolean());
		Mockito.verify(cannon, never()).shoot();
	}
}
