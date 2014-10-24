package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class ProjectileTest {
	private Projectile projectile;

	@Before
	public void setUp() {
		Circle c = new Circle(0, 0, 20);
		Vector2 d = new Vector2(0, 1);
		this.projectile = new Projectile(c, d, 2);
	}

	@Test
	public void testProjectile() {
		Circle c = new Circle(0, 0, 20);
		assertEquals(c.x, projectile.getBounds().x, .001);
		assertEquals(c.y, projectile.getBounds().y, .001);
		assertEquals(c.radius, projectile.getBounds().radius, .001);

		assertEquals(new Vector2(0, 1), projectile.getDirection());

		assertEquals(2, projectile.getVelocity());
	}

	@Test
	public void testVelocity() {
		projectile.setVelocity(10);
		assertEquals(10, projectile.getVelocity());

		projectile.setVelocity(20);
		assertEquals(20, projectile.getVelocity());

		projectile.setVelocity(40);
		assertEquals(40, projectile.getVelocity());
	}

	@Test
	public void testDirection() {
		projectile.setDirection(new Vector2(1, 0));
		assertEquals(new Vector2(1, 0), projectile.getDirection());

		projectile.setDirection(new Vector2(0, -1));
		assertEquals(new Vector2(0, -1), projectile.getDirection());

		projectile.setDirection(new Vector2(1, 1));
		assertEquals(new Vector2(1, 1), projectile.getDirection());
	}

	@Test
	public void testMove() {
		int velo = projectile.getVelocity();
		Vector2 dir = new Vector2(projectile.getDirection()).scl(velo);
		Vector2 pos = new Vector2(projectile.getPosition());

		for (int i = 0; i < 10; i++) {
			projectile.move();
			assertEquals(pos.add(dir), projectile.getPosition());
		}
	}
	
	/**
	 * Checked whether the circles are initiated with the given boundaries.
	 *
	 */
	@Test
	public void testBounds() {
		Circle c = new Circle(0, 0, 10);

		projectile.setBounds(c);
		assertEquals(c, projectile.getBounds());
	}
}
