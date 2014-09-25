package nl.tudelft.ti2206.bubbleshooter.util;

import com.badlogic.gdx.Gdx;

public class Logger implements BSModeObserver {


	@Override
	public void notifyScore(int score) {
		Gdx.app.log("Score changed:", Integer.toString(score));
		
	}

}
