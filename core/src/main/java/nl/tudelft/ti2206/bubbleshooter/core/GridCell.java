package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.ArrayList;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;

public class GridCell {
	private ArrayList<GridCell> neighbors;
	private Bubble bubble;
	
	public GridCell() {
		neighbors = new ArrayList<>();
	}
	
	public Bubble getBubble() {
		return this.bubble;
	}
}
