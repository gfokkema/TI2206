package nl.tudelft.ti2206.bubbleshooter.mode;

public class ZenCondition implements EndingCondition {

	@Override
	public boolean check(BSMode mode) {
		return mode.board.isEmpty();
	}

}
