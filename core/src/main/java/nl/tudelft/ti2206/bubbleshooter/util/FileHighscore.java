package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * 
 *
 */
public class FileHighscore {
	private ArrayList<HighScore> scores;
	
	
		public void addScore(String naam, int score){
			loadScoreFile();
			scores.add(new HighScore(naam,score));
			updateScoreFile();
		}

		@SuppressWarnings("unchecked")
		public void sort(){
			HighScoreComparator comparator = new HighScoreComparator();
			scores.sort(comparator);
		}
		private void updateScoreFile() {
			// TODO Auto-generated method stub
			
		}

		public void loadScoreFile() {
			// TODO Auto-generated method stub
			
		}

}