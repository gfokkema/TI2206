package nl.tudelft.ti2206.bubbleshooter.score;

import java.io.Serializable;

/**
 * This class represents the level information needed together with a {@link HighScore}.
 * It stores the number and name of a level.
 */
public class Level implements Serializable {
	private static final long serialVersionUID = 245497120265763377L;
	private String name;
	private int level;
	
	/**
	 * Construct a new level information object.
	 * @param level	the number of the level
	 * @param name	the name of the level
	 */
	public Level(int level, String name) {
		this.name = name;
		this.level = level;
	}
	
	/**
	 * Returns the name of this level.
	 * @return	the name of the level
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the number of this level.
	 * @return	the number of this level
	 */
	public int getLevel() {
		return this.level;
	}
}
