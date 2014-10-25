package nl.tudelft.ti2206.bubbleshooter;

import com.badlogic.gdx.Gdx;

import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUIBuilder;

public abstract class GameFactory {
	protected EndingCondition end;
	protected GameUIBuilder gub;

	public GameFactory(BubbleShooter bs) {
		this.gub = new GameUIBuilder(bs.font);
	}
	public abstract GameMode createMode();

	public abstract GameUI createUI();

	protected abstract BoardFactory getBoardFactory();

	protected abstract EndingCondition getEndingCondition();

	protected void setInputProcessor(GameMode mode) {
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(mode));
	}
}
