package nl.tudelft.ti2206.bubbleshooter.mode;

public class OpponentCondition extends EndingDecorator {
	private static final long serialVersionUID = 6637649578736506963L;

	public OpponentCondition(EndingCondition end) {
		super(end);
	}

	/**
	 * When the Opponent lost, we have won
	 */
	@Override
	public void lost() {
		end.won();
	}

	/**
	 * When the Opponent won, we have lost.
	 */
	@Override
	public void won() {
		end.lost();
	}
}
