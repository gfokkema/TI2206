package nl.tudelft.ti2206.bubbleshooter.mode;

import static nl.tudelft.ti2206.bubbleshooter.BubbleShooter.keyDownBindings;
import static nl.tudelft.ti2206.bubbleshooter.BubbleShooter.keyUpBindings;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class SinglePlayerProcessor extends InputAdapter {
	private BSMode mode;

	public SinglePlayerProcessor(BSMode BSMode) {
		this.mode = BSMode;
		keyDownBindings.put(Keys.LEFT, SinglePlayerProcessor::cannonLeft);
		keyDownBindings.put(Keys.RIGHT, SinglePlayerProcessor::cannonRight);
		keyDownBindings.put(Keys.SPACE, SinglePlayerProcessor::cannonShoot);

		keyUpBindings.put(Keys.LEFT, SinglePlayerProcessor::cannonStopMoving);
		keyUpBindings.put(Keys.RIGHT, SinglePlayerProcessor::cannonStopMoving);
	}

	@Override
	public boolean keyDown(int keyCode) {
		if (!keyDownBindings.containsKey(keyCode)) return false;
		return keyDownBindings.get(keyCode).apply(this);
	}

	@Override
	public boolean keyUp(int keyCode) {
		if (!keyUpBindings.containsKey(keyCode)) return false;
		return keyUpBindings.get(keyCode).apply(this);
	}

	public boolean cannonShoot() {
		if (mode.getProjectile() == null) {
			mode.setProjectile(mode.getCannon().shoot());
			return true;
		}
		return false;
	}

	public boolean cannonLeft() {
		mode.cannonLeft(true);
		return true;
	}

	public boolean cannonRight() {
		mode.cannonRight(true);
		return true;
	}

	public boolean cannonStopMoving() {
		mode.cannonLeft(false);
		mode.cannonRight(false);
		return true;
	}
}
