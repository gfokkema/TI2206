package nl.tudelft.ti2206.bubbleshooter.ui;

import java.time.Duration;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import nl.tudelft.ti2206.bubbleshooter.util.Score;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public class StatsBar implements StatsObserver {
	protected Label timerField;
	protected Label scoreField;
	protected static String format = "Time left: %02d:%02d";

	protected StatsBar(Label timer, Label score) {
		this.timerField = timer;
		this.scoreField = score;
	}

	@Override
	public void updateTimer(Duration duration) {
		String timeString = String.format(format, duration.toMinutes(), duration.getSeconds()%60);
		timerField.setText(timeString);
	}

	@Override
	public void updateScore(Score score) {
		scoreField.setText("Score:" + score.getScore());
	}
	
}
