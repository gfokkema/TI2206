package nl.tudelft.ti2206.bubbleshooter.ui;

import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class GameUIBuilder {
	private BitmapFont font;
	private Table table;

	public GameUIBuilder(BitmapFont font) {
		this.font = font;
		this.table = new Table();
		table.setFillParent(true);
	}

	/**
	 * Add a {@link StatsBar}, and add it as {@link StatsObserver} to the 
	 * {@link EndingCondition} and {@link Score}.
	 * @param end	The {@link EndingCondition} for adding the {@link StatsObserver}
	 * @param score	The {@link Score} for adding the {@link StatsObserver}
	 */
	public void addSinglePlayerStatsBar(EndingCondition end, Score score) {
		StatsBar single = addStatsBar(true);
		end.addStatsObserver(single);
		score.addStatsObserver(single);
		single.updateScore(score);
	}

	/**
	 * Add two {@link StatsBar}s, and add them as {@link StatsObserver} to the 
	 * {@link EndingCondition} and {@link Score} and the opponent's {@link Score}.
	 * @param end		The {@link EndingCondition} for adding the {@link StatsObserver}
	 * @param score		The {@link Score} for adding the {@link StatsObserver}
	 * @param oppscore	The opponent's {@link Score} for adding the {@link StatsObserver}
	 */
	public void addMultiPlayerStatsBars(EndingCondition end, Score score, Score oppscore) {
		addSinglePlayerStatsBar(end, score);
		StatsBar multi = addStatsBar(false);
		oppscore.addStatsObserver(multi);
		multi.updateScore(oppscore);
	}

	/**
	 * Create a {@link StatsBar}, and add its {@link Label}s to the {@link Table}.
	 * @param left true or false, if you want it left or right respectively.
	 * @return	The created {@link StatsBar}.
	 */
	public StatsBar addStatsBar(boolean left) {
		VerticalGroup stats = new VerticalGroup();
		stats.align(Align.left);
		Label score = getLabel();
		Label timer = getLabel();
		stats.addActor(timer);
		stats.addActor(score);
		Cell<?> barcell = table.add(stats).expand().top();
		if (left)	barcell.left();
		else		barcell.right();
		return new StatsBar(timer, score);
	}

	/**
	 * Utility method to instantiate a {@link Label}
	 * @return	the {@link Label}
	 */
	private Label getLabel() {
		LabelStyle style = new LabelStyle(font, Color.WHITE);
		return new Label(null, style);
	}

	/**
	 * Build the {@link GameUI}.
	 * @return	the {@link GameUI} as a result of the build process.
	 */
	public GameUI build() {
		return new GameUI(table);
	}
}
