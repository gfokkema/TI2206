package nl.tudelft.ti2206.bubbleshooter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.MPBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.GameMode;
import nl.tudelft.ti2206.bubbleshooter.mode.MultiPlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EmptyGridCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUI;

public class MPGameFactory extends GameFactory {
	EndingCondition end;
	Score ownScore;
	Score oppScore;
	ObjectInputStream in;
	ObjectOutputStream out;

	public MPGameFactory(BubbleShooter bs, ObjectInputStream in, ObjectOutputStream out) {
		super(bs);
		this.end = getEndingCondition();
		this.ownScore = new Score(0);
		this.oppScore = new Score(0);
		this.in = in;
		this.out = out;
	}

	@Override
	public GameMode createMode() {
		MultiPlayerMode multi = new MultiPlayerMode(end, getBoardFactory().makeLevels(), ownScore, oppScore, in, out);
		setInputProcessor(multi);
		return multi;
	}

	@Override
	public GameUI createUI() {
		gub.addMultiPlayerStatsBars(end, ownScore, oppScore);
		gub.addChatWindow();
		return gub.build();
	}

	@Override
	protected BoardFactory getBoardFactory() {
		return new MPBoardFactory();
	}

	@Override
	protected EndingCondition getEndingCondition() {
		return new EmptyGridCondition(new BelowLineCondition(new BasicCondition()));
	}

}
