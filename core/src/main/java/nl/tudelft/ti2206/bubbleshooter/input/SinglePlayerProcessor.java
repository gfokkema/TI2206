package nl.tudelft.ti2206.bubbleshooter.input;

import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;

/**
 * This class processes input from a single player game.
 * It will listen for input from the controls and call actions based on that.
 */
public class SinglePlayerProcessor extends AbstractProcessor {
	/**
	 * Create a {@link SinglePlayerProcessor} which will control the given {@link BSMode}.
	 * @param BSMode	the {@link BSMode} to control
	 */
	public SinglePlayerProcessor(BSMode BSMode) {
		super(BSMode);
	}

	/**
	 * This function is called to shoot the cannon in the {@link BSMode} game.
	 * @return	boolean indicating whether the cannon could be fired
	 */
	@Override
	public boolean cannonShoot() {
		if (mode.getProjectile() == mode.getCannon().getProjectile() && !mode.getBoard().getGrid().collides(mode.getCannon().getProjectile())) {
			mode.setProjectile(mode.getCannon().shoot(mode.getBoard()));
			return true;
		}
		return false;
	}

	/**
	 * This function will start rotating the cannon in the {@link BSMode} to the left.
	 * @return	boolean indicating whether the cannon has started rotating
	 */
	@Override
	public boolean cannonLeft() {
		mode.cannonLeft(true);
		return true;
	}

	/**
	 * This function will start rotating the cannon in the {@link BSMode} to the right.
	 * @return	boolean indicating whether the cannon has started rotating
	 */
	@Override
	public boolean cannonRight() {
		mode.cannonRight(true);
		return true;
	}

	/**
	 * This function will stop rotating the cannon in the {@link BSMode} to the left.
	 * @return	boolean indicating whether the cannon has stopped rotating
	 */
	@Override
	public boolean cannonStopLeft() {
		mode.cannonLeft(false);
		return true;
	}

	/**
	 * This function will stop rotating the cannon in the {@link BSMode} to the right.
	 * @return	boolean indicating whether the cannon has stopped rotating
	 */
	@Override
	public boolean cannonStopRight() {
		mode.cannonRight(false);
		return true;
	}
}
