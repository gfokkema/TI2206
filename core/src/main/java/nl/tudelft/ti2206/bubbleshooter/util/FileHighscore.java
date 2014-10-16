package nl.tudelft.ti2206.bubbleshooter.util;

import java.util.SortedSet;

public class FileHighscore {
	private SortedSet<HighScore> scores;

	public void addScore(String naam, int score){
		loadScoreFile();
		scores.add(new HighScore(naam,score));
		updateScoreFile();
	}
	
	private void updateScoreFile() {
		// TODO Auto-generated method stub
		
	}

	public void loadScoreFile() {
		// TODO Auto-generated method stub
		
	}
}