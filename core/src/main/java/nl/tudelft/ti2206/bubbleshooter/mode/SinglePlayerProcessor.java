package nl.tudelft.ti2206.bubbleshooter.mode;

public class SinglePlayerProcessor extends AbstractProcessor {

	public SinglePlayerProcessor(BSMode BSMode) {
		super(BSMode);
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

	public boolean cannonStopLeft() {
		mode.cannonLeft(false);
		return true;
	}

	public boolean cannonStopRight() {
		mode.cannonRight(false);
		return true;
	}
}
