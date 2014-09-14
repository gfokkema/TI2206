package nl.tudelft.ti2206.bubbleshooter.html;

import nl.tudelft.ti2206.bubbleshooter.Launch;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class BubbleshooterHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Launch();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
