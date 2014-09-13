package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import static org.junit.Assert.*;
import nl.tudelft.ti2206.bubbleshooter.Projectile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

@RunWith(MockitoJUnitRunner.class)
public class CannonTest {
	@Mock Texture tex;
	
	private Cannon cannon;
	
	@Before
	public void setUp() throws InterruptedException {
		Mockito.when(tex.getWidth()).thenReturn(20);
		Mockito.when(tex.getWidth()).thenReturn(100);
		
		this.cannon = new Cannon(tex, 0, 0);
	}

	@Test
	public void testInitializeCannon() {
		assertEquals(tex, cannon.getTexture());
		assertEquals(new Vector2(-16, 0), cannon.getPointer().getOrigin());
	}
	
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
	
	@Test
	public void testAngleOutsideOfBoundaries() {
		cannon.setAngle(-70);
		assertEquals(-60, cannon.getPointer().getAngle(), .001);
		
		cannon.setAngle(70);
		assertEquals(60, cannon.getPointer().getAngle(), .001);
	}
	
	@Test
	public void testProjectile() {
		Projectile ammo = cannon.getProjectile();
		Vector2 pos = cannon.projectile.getPosition();
		assertEquals(cannon.getPointer().direction.add(0, 16), pos);
		
		
	}
	
	@Test
	public void testShoot() {
		Projectile ammo, fired;
		
		ammo = cannon.projectile;
		assertEquals(ammo, cannon.projectile);
		
		fired = cannon.shoot();
		assertEquals(fired, ammo);
		assertNotEquals(fired, cannon.projectile);
	}

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
