package nl.tudelft.ti2206.bubbleshooter.java;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;

/**
 * Bubbleshooter Desktop contain the specifications needed for launching Bubble Shooter on PC.
 * @author group-15
 *
 */
public class BubbleshooterDesktop {
	
	/**
	 * Setting up some basic settings.
	 * @param args	command line arguments
	 */
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 480;
		
		// fullscreen
		config.fullscreen = false;
		config.title = "Bubble Shooter";
		config.addIcon("logo/logo16.png", FileType.Internal);
		config.addIcon("logo/logo32.png", FileType.Internal);
		config.addIcon("logo/logo128.png", FileType.Internal);
		// temporarily start directly to the main menu --> splash art should come first...
		new LwjglApplication(new BubbleShooter(), config);
	}
}
