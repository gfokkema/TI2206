package nl.tudelft.ti2206.bubbleshooter.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import nl.tudelft.ti2206.bubbleshooter.core.Launch;

/**
 * Bubbleshooter Desktop contain the specifications needed for launching Bubble Shooter on PC.
 * @author group-15
 *
 */
public class BubbleshooterDesktop {
	
	/**
	 * Setting up some basic settings.
	 * @param args
	 */
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 480;
		
		// fullscreen
		config.fullscreen = false;
		config.title = "Bubble Shooter";
		// temporarily start directly to the main menu --> splash art should come first...
		new LwjglApplication(new Launch(), config);
	}
}
