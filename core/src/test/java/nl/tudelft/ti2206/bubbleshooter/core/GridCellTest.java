package nl.tudelft.ti2206.bubbleshooter.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;

import java.util.HashSet;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.BubbleBehaviour;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

@RunWith(MockitoJUnitRunner.class)
public class GridCellTest {
	@Mock BubbleBehaviour behaviour;
	@Mock Bubble bubble;
	
	private Circle c;
	private GridCell cell;
	
	@Before
	public void setUp() {
		c = new Circle(new Vector2(0, 0), 20);
		cell = new GridCell(c);
		
		Mockito.when(bubble.getBehaviour()).thenReturn(behaviour);
		Mockito.when(behaviour.chain(any())).thenReturn(3);
		Mockito.when(behaviour.remove(any())).thenReturn(3);
		Mockito.when(behaviour.trigger(any())).thenReturn(3);
	}
	
	@Test
	public void testCreate() {
		assertEquals(new Vector2(0, 0), cell.getOrigin());
		assertEquals(new HashSet<GridCell>(), cell.getNeighbors());
	}
	
	@Test
	public void testConnect() {
		HashSet<GridCell> expected = new HashSet<>();
		HashSet<GridCell> expectedNeighbor = new HashSet<>();
		
		cell.connect(null);
		assertEquals(expected, cell.getNeighbors());
		
		GridCell neighbor = new GridCell(c);
		cell.connect(neighbor);
		expected.add(neighbor);
		expectedNeighbor.add(cell);
		assertEquals(expected, cell.getNeighbors());
		assertEquals(expectedNeighbor, neighbor.getNeighbors());
	}
	
	@Test
	public void testSetBubble() {
		Bubble b = new ColourBubble();
		
		assertFalse(cell.isOccupied());
		assertNull(cell.getBubble());
		
		cell.setBubble(b);
		assertTrue(cell.isOccupied());
		assertEquals(b, cell.getBubble());
	}

	@Test
	public void testRemoveBubble() {
		Bubble b = new ColourBubble();
		cell.setBubble(b);
		
		cell.removeBubble();
		assertFalse(cell.isOccupied());
		assertNull(cell.getBubble());
	}
	
	@Test
	public void testRemove() {
		assertEquals(0, cell.remove());
		Mockito.verify(bubble, never()).getBehaviour();
		Mockito.verify(behaviour, never()).remove(cell);
		
		cell.setBubble(bubble);
		assertEquals(3, cell.remove());
		
		Mockito.verify(bubble).getBehaviour();
		Mockito.verify(behaviour).remove(cell);
	}
	
	@Test
	public void testChain() {
		assertEquals(0, cell.chain());
		Mockito.verify(bubble, never()).getBehaviour();
		Mockito.verify(behaviour, never()).chain(cell);
		
		cell.setBubble(bubble);
		assertEquals(3, cell.chain());
		
		Mockito.verify(bubble).getBehaviour();
		Mockito.verify(behaviour).chain(cell);
	}
	
	@Test
	public void testTrigger() {
		assertEquals(0, cell.trigger());
		Mockito.verify(bubble, never()).getBehaviour();
		Mockito.verify(behaviour, never()).trigger(cell);
		
		cell.setBubble(bubble);
		assertEquals(3, cell.trigger());
		
		Mockito.verify(bubble).getBehaviour();
		Mockito.verify(behaviour).trigger(cell);
	}
}
