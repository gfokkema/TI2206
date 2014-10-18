package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Tests for the {@link Cannon} class
 */
@RunWith(MockitoJUnitRunner.class)
public class CannonTest {
	@Mock Texture tex;
	@Mock AssetManager loader;
	@Mock Board board;
	@Mock Sound sound;
	
	private Cannon cannon;
	private Assets assets;
	private ArrayList<Color> available;

	/**
	 * Initialize our mock objects and the {@link Cannon}.
	 */
	@Before
	public void setUp(){
		assets = Assets.getAssets();
		assets.setAssetManager(loader);
		available = new ArrayList<Color>();
		available.add(Color.BLUE);
		Mockito.when(board.getColoursAvailable()).thenReturn(available);
		Mockito.when(tex.getWidth()).thenReturn(20);
		Mockito.when(tex.getWidth()).thenReturn(100);
		Mockito.when(loader.get(anyString())).thenReturn(sound);
		this.cannon = new Cannon(0, 0);
	}

	/**
	 * Tests creation and initialization of default values of the {@link Cannon} class.
	 */
	@Test
	public void testInitializeCannon() {
		assertEquals(new Vector2(-16, 0), cannon.getPointer().getOrigin());
		assertEquals(100, cannon.getWidth());
		assertEquals(100, cannon.getHeight());
		assertEquals(TextureID.CANNON, cannon.getTexture());
		assertEquals(new Vector2(-50, 0), cannon.getPosition());
		assertEquals(new Vector2(50, 16), cannon.getOrigin());
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
		Vector2 pos = cannon.getProjectile().getMidPoint();
		assertEquals(cannon.getPointer().direction.cpy().scl(100).add(0, 16), pos);


	}

	/**
	 * Tests for shooting a projectile
	 */
	@Test
	public void testShoot() {
		Projectile ammo, fired;

		ammo = cannon.projectile;
		assertEquals(ammo, cannon.projectile);

		fired = cannon.shoot(board);
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
