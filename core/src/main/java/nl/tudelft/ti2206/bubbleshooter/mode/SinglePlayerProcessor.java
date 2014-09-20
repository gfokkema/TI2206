package nl.tudelft.ti2206.bubbleshooter.mode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class SinglePlayerProcessor extends InputAdapter {
	ZenMode mode;

	public SinglePlayerProcessor(ZenMode zenMode) {
		this.mode = zenMode;
	}

	public boolean keyDown(int keyCode) {
		switch (keyCode) {
		case Keys.SPACE:
			mode.projectile = mode.cannon.shoot();
			return true;
		case Keys.LEFT:
			mode.cannon.left(Gdx.graphics.getDeltaTime());
			return true;
		case Keys.RIGHT:
			mode.cannon.right(Gdx.graphics.getDeltaTime());
			return true;
		default:
			return false;
		}
	}
}
