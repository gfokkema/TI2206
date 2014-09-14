package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import static org.junit.Assert.*;
import nl.tudelft.ti2206.bubbleshooter.Projectile;
import nl.tudelft.ti2206.bubbleshooter.SoundEffect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Tests for the {@link Cannon} class
 */
@RunWith(MockitoJUnitRunner.class)
public class CannonTest {
	@Mock Texture tex;
	@Mock SoundEffect sound;
	
	private Cannon cannon;
	
	/**
	 * Initialize our mock objects and the {@link Cannon}.
	 */
	@Before
	public void setUp(){
		Mockito.when(tex.getWidth()).thenReturn(20);
		Mockito.when(tex.getWidth()).thenReturn(100);
		
		this.cannon = new Cannon(tex, sound, 0, 0);
	}

	/**
	 * Tests creation of a {@link Cannon}.
	 */
	@Test
	public void testInitializeCannon() {
		assertEquals(tex, cannon.getTexture());
		assertEquals(new Vector2(-16, 0), cannon.getPointer().getOrigin());
	}
	
	/**
	 * Tests setting and getting the angle of a {@link Cannon}.
	 */
	@Test
	public void testAngle() {
		assertEquals(0,  cannon.getPointer().getAngle(), .001);
		
		cannon.setAngle(-45);
		assertEquals(-45, cannon.getPointer().getAngle(), .001);
		
		cannon.setAngle(45);
		assertEquals(45, cannon.getPointer().getAngle(), .001);
		
		cannon.setAngle(-60);
		assertEquals(-60, cannon.getPointer().getAngle(), .001);
		
		cannon.setAngle(60);
		assertEquals(60, cannon.getPointer().getAngle(), .001);
	}
	
	/**
	 * Tests setting the angle outside of the {@link Cannon} angle boundaries.
	 */
	@Test
	public void testAngleOutsideOfBoundaries() {
		cannon.setAngle(-70);
		assertEquals(-60, cannon.getPointer().getAngle(), .001);
		
		cannon.setAngle(70);
		assertEquals(60, cannon.getPointer().getAngle(), .001);
	}
	
	/**
	 * Tests projectile creation and location
	 */
	@Test
	public void testProjectile() {
		Projectile ammo = cannon.getProjectile();
		Vector2 pos = cannon.projectile.getPosition();
		assertEquals(cannon.getPointer().direction.add(0, 16), pos);
		
		
	}
	
	/**
	 * Tests for shooting a projectile
	 */
	@Test
	public void testShoot() {
		Projectile ammo, fired;
		
		ammo = cannon.projectile;
		assertEquals(ammo, cannon.projectile);
		
		fired = cannon.shoot();
		assertEquals(fired, ammo);
		assertNotEquals(fired, cannon.projectile);
	}

	/**
	 * Tests moving the projectile given it's velocity and direction
	 */
	@Test
	public void testMove() {
		Pointer pointer = cannon.getPointer();
		assertEquals(0, pointer.getAngle(), .001);
		
		cannon.left(0.1f);
		assertEquals(10, pointer.getAngle(), .001);
		
		cannon.left(0.5f);
		assertEquals(60, pointer.getAngle(), .001);
		
		cannon.left(1.0f);
		assertEquals(60, pointer.getAngle(), .001);
		
		cannon.right(1.0f);
		assertEquals(-40, pointer.getAngle(), .001);
		
		cannon.right(0.1f);
		assertEquals(-50, pointer.getAngle(), .001);
	}
}
