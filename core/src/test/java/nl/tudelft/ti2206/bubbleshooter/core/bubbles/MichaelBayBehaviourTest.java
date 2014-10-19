package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.MichaelBayBehaviour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MichaelBayBehaviourTest {

	/**
	 * Setup mocks
	 */
	@Mock Grid grid;
	MichaelBayBehaviour MBBehaviour;
	
	/**
	 * Setup intialization and stubbing.
	 */
	@Before
	public void setUp() {
		MBBehaviour = new MichaelBayBehaviour();
	}
	
	@Test
	public void test() {
		
	}
}
