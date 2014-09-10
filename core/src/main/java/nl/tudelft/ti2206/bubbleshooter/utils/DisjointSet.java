package nl.tudelft.ti2206.bubbleshooter.utils;

public class DisjointSet {
	private int[] id;
	private int[] sz;

	public DisjointSet(int size) {
		id = new int[size];
		sz = new int[size];
		for(int i=0; i<size; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	/**
	 * Find the root of the set, which represents
	 * the id of the set.
	 * @param i
	 * @return the id of the set which contains i.
	 */
	public int find(int i) {
		while(i != id[i]) {
			i = id[i];
		}
		return i;
	}

	/**
	 * True if i and j are in the same set.
	 * @param i
	 * @param j
	 * @return true if i and j are connected
	 */
	public boolean connected(int i, int j) {
		return find(i) == find(j);
	}

	/**
	 * Unify the set containing i with the set
	 * containing j.
	 * @param i
	 * @param j
	 */
	public void union(int i, int j) {
		int root_i = find(i);
		int root_j = find(j);
		if (root_i == root_j) return;

		if (sz[root_i] < sz[root_j]) {
			id[root_i] = root_j;
			sz[root_j] += sz[root_i];
		} else {
			id[root_j] = root_i;
			sz[root_i] += sz[root_j];
		}
	}

	public int sizeof(int i) {
		return sz[i];
	}
}
