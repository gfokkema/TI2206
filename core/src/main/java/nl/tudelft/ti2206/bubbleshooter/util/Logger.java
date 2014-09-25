package nl.tudelft.ti2206.bubbleshooter.util;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.Gdx;

public class Logger implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		Gdx.app.log("Something changed", "" + arg);
		
	}

}
