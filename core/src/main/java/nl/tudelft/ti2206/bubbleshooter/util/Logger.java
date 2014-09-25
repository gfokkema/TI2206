package nl.tudelft.ti2206.bubbleshooter.util;

import com.badlogic.gdx.Gdx;

public class Logger {

	public static void print(String tag, String message) {
		try {
			Gdx.app.log(tag, message);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
