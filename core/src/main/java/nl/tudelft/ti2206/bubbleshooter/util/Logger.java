package nl.tudelft.ti2206.bubbleshooter.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Logger implements BSModeObserver {


	@Override
	public void notifyScore(int score) {
		Gdx.app.log("Score changed:", Integer.toString(score));
		
	}

	@Override
	public void notifyCannonAngle(float angle) {
		Gdx.app.log("Cannon angle changed:", Float.toString(angle));
		
	}

	@Override
	public void notifyProjectilePosition(Vector2 position) {
		Gdx.app.log("Projectile position changed:", position.toString());
		
	}

}
