package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;

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

		private void updateScoreFile() {
			// TODO Auto-generated method stub
			
		}

		public void loadScoreFile() {
			// TODO Auto-generated method stub
			
		}

}