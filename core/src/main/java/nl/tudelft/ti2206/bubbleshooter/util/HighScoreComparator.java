package nl.tudelft.ti2206.bubbleshooter.util;

import java.util.Comparator;

public class HighScoreComparator implements Comparator {

	@Override
	public int compare(Object score1, Object score2) {
		int sc1 = ((HighScore) score1).getHighScore();
		int sc2 = ((HighScore) score2).getHighScore();

		if (sc1 > sc2) {
			return -1;
		} else if (sc1 < sc2) {
			return +1;
		} else {
			return 0;
		}
	}

}
