package nl.tudelft.ti2206.bubbleshooter.input;

import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;

/**
 * This class processes input from a single player game.
 * It will listen for input from the controls and call actions based on that.
 */
public class SinglePlayerProcessor extends AbstractProcessor {
	/**
	 * Create a {@link SinglePlayerProcessor} which will control the given {@link GameMode}.
	 * @param mode	the {@link GameMode} to control
	 */
	public SinglePlayerProcessor(GameMode mode) {
		super(mode);
	}

	/**
	 * This function is called to shoot the cannon in the {@link GameMode} game.
	 * @return	boolean indicating whether the cannon could be fired
	 */
	@Override
	public boolean cannonShoot() {
		if (mode.getProjectile() == mode.getCannon().getProjectile() && !mode.getGrid().collides(mode.getCannon().getProjectile())) {
			mode.setProjectile(mode.getCannon().shoot(mode.getGrid().getColoursAvailable()));
			return true;
		}
		return false;
	}

	/**
	 * This function will start rotating the cannon in the {@link GameMode} to the left.
	 * @return	boolean indicating whether the cannon has started rotating
	 */
	@Override
	public boolean cannonLeft() {
		mode.cannonLeft(true);
		return true;
	}

	/**
	 * This function will start rotating the cannon in the {@link GameMode} to the right.
	 * @return	boolean indicating whether the cannon has started rotating
	 */
	@Override
	public boolean cannonRight() {
		mode.cannonRight(true);
		return true;
	}

	/**
	 * This function will stop rotating the cannon in the {@link GameMode} to the left.
	 * @return	boolean indicating whether the cannon has stopped rotating
	 */
	@Override
	public boolean cannonStopLeft() {
		mode.cannonLeft(false);
		return true;
	}

	/**
	 * This function will stop rotating the cannon in the {@link GameMode} to the right.
	 * @return	boolean indicating whether the cannon has stopped rotating
	 */
	@Override
	public boolean cannonStopRight() {
		mode.cannonRight(false);
		return true;
	}
}
