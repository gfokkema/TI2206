package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class PointerTest {
	Pointer pointer;
	@Before
	public void setUp() {
		this.pointer = new Pointer(new Vector2(0, 0));
	}

	@Test
	public void testPointer() {
		assertEquals(new Vector2(0, 0), pointer.getOrigin());
		assertEquals(0, pointer.getAngle(), .001);
	}
	
	@Test
	public void testOrigin() {
		pointer.setOrigin(new Vector2(10, 50));
		assertEquals(new Vector2(10, 50), pointer.getOrigin());
		
		pointer.setOrigin(new Vector2(2, 10));
		assertEquals(new Vector2(2, 10), pointer.getOrigin());
	}
	
	@Test
	public void testAngle() {
		pointer.setAngle(0);
		assertEquals(0, pointer.getAngle(), .001);
		
		pointer.setAngle(-30);
		assertEquals(-30, pointer.getAngle(), .001);
		
		pointer.setAngle(30);
		assertEquals(30, pointer.getAngle(), .001);
		
		pointer.setAngle(-70);
		assertEquals(-70, pointer.getAngle(), .001);
		
		pointer.setAngle(70);
		assertEquals(70, pointer.getAngle(), .001);
	}
	
	@Test
	public void testDirection() {
		pointer.setDirection(new Vector2(0, 1));
		assertEquals(0, pointer.getAngle(), .001);
		assertEquals(new Vector2(0, 1), pointer.getDirection());
		
		pointer.setDirection(new Vector2(-1, 0));
		assertEquals(90, pointer.getAngle(), .001);
		assertEquals(new Vector2(-1, 0), pointer.getDirection());
		
		pointer.setDirection(new Vector2(-1, 1));
		assertEquals(45, pointer.getAngle(), .001);
		assertEquals(new Vector2(-1, 1).nor(), pointer.getDirection());
	}
	
	@Test
	public void testToString() {
		assertEquals("Pointer(Direction: [0.0:1.0], Origin:[0.0:0.0])", pointer.toString());
		pointer.setOrigin(new Vector2(10, 40));
		assertEquals("Pointer(Direction: [0.0:1.0], Origin:[10.0:40.0])", pointer.toString());
		pointer.setDirection(new Vector2(1, 0));
		assertEquals("Pointer(Direction: [1.0:0.0], Origin:[10.0:40.0])", pointer.toString());
		pointer.setOrigin(new Vector2(0, -20));
		assertEquals("Pointer(Direction: [1.0:0.0], Origin:[0.0:-20.0])", pointer.toString());
		pointer.setDirection(new Vector2(-20, 0));
		assertEquals("Pointer(Direction: [-1.0:0.0], Origin:[0.0:-20.0])", pointer.toString());
	}
}
