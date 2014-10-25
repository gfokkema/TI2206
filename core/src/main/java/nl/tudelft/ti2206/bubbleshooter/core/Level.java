package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;

public class Level implements Serializable {
	private static final long serialVersionUID = 245497120265763377L;
	private String name;
	private int level;
	
	public Level(int level, String name) {
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return this.name;
	}
	public int getLevel() {
		return this.level;
	}
}
