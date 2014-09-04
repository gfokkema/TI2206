package nl.tudelft.ti2206.bubbleshooter.html;

import nl.tudelft.ti2206.bubbleshooter.core.Bubbleshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class BubbleshooterHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Bubbleshooter();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
