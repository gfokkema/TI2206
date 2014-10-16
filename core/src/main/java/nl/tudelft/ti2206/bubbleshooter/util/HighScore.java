package nl.tudelft.ti2206.bubbleshooter.util;

public class HighScore implements Comparable<HighScore> {
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

	@Override
	public int compareTo(HighScore o) {
		return Integer.compare(this.highscore, o.highscore);
	}
}
