package nl.tudelft.ti2206.bubbleshooter.ui;

import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;

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

	public void addSinglePlayerStatsBar(EndingCondition end, Score score) {
		StatsBar single = addStatsBar(true);
		end.addStatsObserver(single);
		score.addStatsObserver(single);
	}

	public void addMultiPlayerStatsBars(EndingCondition end, Score score, Score oppscore) {
		addSinglePlayerStatsBar(end, score);
		StatsBar multi = addStatsBar(false);
		oppscore.addStatsObserver(multi);
	}

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

	private Label getLabel() {
		LabelStyle style = new LabelStyle(font, Color.WHITE);
		return new Label(null, style);
	}

	public GameUI build() {
		return new GameUI(table);
	}
}
