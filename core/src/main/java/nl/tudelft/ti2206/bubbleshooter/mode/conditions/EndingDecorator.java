package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;

public abstract class EndingDecorator extends EndingCondition {
	private static final long serialVersionUID = -7461642744446812369L;
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
	public void check(Board board) {
		end.check(board);
	}

	@Override
	public void addEndingObserver(EndingObserver o) {
		end.addEndingObserver(o);
	}
}
