package nl.tudelft.ti2206.bubbleshooter.util;


public class OpponentAdapter implements EndingObserver {
	private GameObserver wrappedObserver;

	public OpponentAdapter(GameObserver obs) {
		this.wrappedObserver = obs;
	}

	@Override
	public void lost() {
		wrappedObserver.switchToWonScreen();
	}

	@Override
	public void won() {
		wrappedObserver.switchToLostScreen();
	}

}
