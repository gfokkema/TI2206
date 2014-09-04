package nl.tudelft.ti2206.bubbleshooter.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import nl.tudelft.ti2206.bubbleshooter.core.Bubbleshooter;

public class BubbleshooterDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Bubbleshooter(), config);
	}
}
