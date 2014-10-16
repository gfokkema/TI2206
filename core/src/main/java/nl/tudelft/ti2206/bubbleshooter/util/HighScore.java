package nl.tudelft.ti2206.bubbleshooter.util;

import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;

/**
 * 
 * @author group-15
 *
 */
public class HighScore {
	private int highscore;
	private String name;
	
	public HighScore(String name, int highscore) {
		this.highscore = highscore;
		this.name = name;
	}

	public int getHighScore() {
		return highscore;
	}

	public String getName() {
		return name;
	}



}
