package nl.tudelft.ti2206.bubbleshooter.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class DisjointSetTest {

	@Test
	public void connectedTest() {
		DisjointSet ds = new DisjointSet(10);
		ds.union(1, 2);
		ds.union(2, 3);
		ds.union(3, 4);
		ds.union(5, 6);
		ds.union(6, 7);
		assertTrue(ds.connected(1, 4));
		assertTrue(ds.connected(5, 7));
		assertFalse(ds.connected(1, 7));
	}

	@Test
	public void unionTest() {
		DisjointSet ds = new DisjointSet(2);
		ds.union(0, 1);
		assertTrue(ds.connected(0, 1));
	}

	@Test
	public void findTest() {
		DisjointSet ds = new DisjointSet(10);
		assertEquals(1, ds.find(1));
		ds.union(1, 2);
		ds.union(2, 3);
		ds.union(3, 4);
		ds.union(5, 6);
		ds.union(6, 7);
		assertTrue(ds.find(4) == ds.find(2));
		assertFalse(ds.find(6) == ds.find(3));
	}
}
