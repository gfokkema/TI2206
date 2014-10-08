package nl.tudelft.ti2206.bubbleshooter.mode;

import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;

public abstract class EndingDecorator extends EndingCondition {
	EndingCondition end;

	public EndingDecorator(EndingCondition end) {
		this.end = end;
	} 

	/**
	 * Called when the level was lost.
	 * Calls will be forwarded, until we reach a
	 * non-decorator EndingCondition.
	 */
	@Override
	public void lost() {
		end.lost();
	}

	/**
	 * Called when the level was won.
	 * Calls will be forwarded, until we reach a
	 * non-decorator EndingCondition.
	 */
	@Override
	public void won() {
		end.won();
	}

	@Override
	public void check(BSMode mode) {
		end.check(mode);
	}

	@Override
	public void addEndingObserver(EndingObserver o) {
		end.addEndingObserver(o);
	}

}
