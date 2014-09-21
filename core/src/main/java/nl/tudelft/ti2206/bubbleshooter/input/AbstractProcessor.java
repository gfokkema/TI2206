package nl.tudelft.ti2206.bubbleshooter.input;

import static nl.tudelft.ti2206.bubbleshooter.BubbleShooter.keyDownBindings;
import static nl.tudelft.ti2206.bubbleshooter.BubbleShooter.keyUpBindings;
import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;

import com.badlogic.gdx.InputAdapter;

public abstract class AbstractProcessor extends InputAdapter {
	protected BSMode mode;

	public AbstractProcessor(BSMode BSMode) {
		this.mode = BSMode;
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

	public abstract boolean cannonLeft();
	public abstract boolean cannonRight();
	public abstract boolean cannonShoot();
	public abstract boolean cannonStopLeft();
	public abstract boolean cannonStopRight();
}
