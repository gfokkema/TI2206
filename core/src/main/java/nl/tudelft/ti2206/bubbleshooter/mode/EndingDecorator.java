package nl.tudelft.ti2206.bubbleshooter.mode;

public abstract class EndingDecorator extends EndingCondition {
	EndingCondition end;

	public EndingDecorator(EndingCondition end) {
		this.end = end;
	} 

	@Override
	public int check(BSMode mode) {
		return end.check(mode);
	}

}
