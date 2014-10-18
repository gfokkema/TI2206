package nl.tudelft.ti2206.bubbleshooter.input;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

/**
 * This class processes input from a game.
 * It will listen for input from the controls and call actions based on that.
 * A singleplayerprocessor or hotseatedprocessor should be derived from this class.
 */
public abstract class AbstractProcessor extends InputAdapter {
	private static Map<Integer, Function<AbstractProcessor, Boolean>> keyDownBindings
		= new HashMap<Integer, Function<AbstractProcessor, Boolean>>();
	private static Map<Integer, Function<AbstractProcessor, Boolean>> keyUpBindings
		= new HashMap<Integer, Function<AbstractProcessor, Boolean>>();

	// Initialize the keybindings.
	static {
		keyDownBindings.put(Keys.LEFT, AbstractProcessor::cannonLeft);
		keyDownBindings.put(Keys.RIGHT, AbstractProcessor::cannonRight);
		keyDownBindings.put(Keys.SPACE, AbstractProcessor::cannonShoot);
	
		keyUpBindings.put(Keys.LEFT, AbstractProcessor::cannonStopLeft);
		keyUpBindings.put(Keys.RIGHT, AbstractProcessor::cannonStopRight);
	}
	
	protected BSMode mode;

	/**
	 * Create a {@link AbstractProcessor} which will control the given {@link BSMode}.
	 * @param BSMode	the {@link BSMode} to control
	 */
	public AbstractProcessor(BSMode BSMode) {
		this.mode = BSMode;
	}

	/**
	 * This function is called when a key is pressed down.
	 * @param keyCode	integer indicating which key was pressed
	 * @return	boolean indicating whether the event has been handled
	 */
	@Override
	public boolean keyDown(int keyCode) {
		if (!keyDownBindings.containsKey(keyCode)) return false;
		return keyDownBindings.get(keyCode).apply(this);
	}

	/**
	 * This function is called when a key is let go.
	 * @param keyCode	integer indicating which key was pressed
	 * @return	boolean indicating whether the event has been handled
	 */
	@Override
	public boolean keyUp(int keyCode) {
		if (!keyUpBindings.containsKey(keyCode)) return false;
		return keyUpBindings.get(keyCode).apply(this);
	}

	/**
	 * This function will start rotating the cannon in the {@link BSMode} to the left.
	 * @return	boolean indicating whether the cannon has started rotating
	 */
	public abstract boolean cannonLeft();
	
	/**
	 * This function will start rotating the cannon in the {@link BSMode} to the right.
	 * @return	boolean indicating whether the cannon has started rotating
	 */
	public abstract boolean cannonRight();
	
	/**
	 * This function is called to shoot the cannon in the {@link BSMode} game.
	 * @return	boolean indicating whether the cannon could be fired
	 */
	public abstract boolean cannonShoot();
	
	/**
	 * This function will stop rotating the cannon in the {@link BSMode} to the left.
	 * @return	boolean indicating whether the cannon has stopped rotating
	 */
	public abstract boolean cannonStopLeft();
	
	/**
	 * This function will stop rotating the cannon in the {@link BSMode} to the right.
	 * @return	boolean indicating whether the cannon has stopped rotating
	 */
	public abstract boolean cannonStopRight();
}
