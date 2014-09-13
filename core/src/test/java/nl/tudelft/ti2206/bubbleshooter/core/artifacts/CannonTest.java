package nl.tudelft.ti2206.bubbleshooter.core.artifacts;

import static org.junit.Assert.*;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;

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
	public void testPointer() throws InterruptedException {
		assertEquals(new Vector2(-16, 0), cannon.getPointer().getOrigin());
	}

}
