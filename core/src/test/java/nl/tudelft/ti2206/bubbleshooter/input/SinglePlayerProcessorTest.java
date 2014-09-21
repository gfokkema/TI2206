package nl.tudelft.ti2206.bubbleshooter.input;

import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class SinglePlayerProcessorTest {
	private SinglePlayerProcessor input;
	
	@Mock BSMode mode;
	@Mock Cannon cannon;
	@Mock Projectile projectile;
	
	@Before
	public void setUp() {
		input = new SinglePlayerProcessor(mode);
		Mockito.when(mode.getCannon()).thenReturn(cannon);
	}

	@Test
	public void testShoot() {
		input.cannonShoot();
		Mockito.verify(mode).getCannon();
		Mockito.verify(cannon).shoot();
	}
	
	@Test
	public void testShootProjectileInPlay() {
		Mockito.when(mode.getProjectile()).thenReturn(projectile);
		input.cannonShoot();
		Mockito.verify(cannon, never()).shoot();
	}
	
	@Test
	public void testCannonLeft() {
		input.cannonLeft();
		Mockito.verify(mode).cannonLeft(true);
		
		input.cannonStopLeft();
		Mockito.verify(mode).cannonLeft(false);
	}
	
	@Test
	public void testCannonRight() {
		input.cannonRight();
		Mockito.verify(mode).cannonRight(true);
		
		input.cannonStopRight();
		Mockito.verify(mode).cannonRight(false);
	}
}
