package nl.tudelft.ti2206.bubbleshooter.mode;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;

import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

public class SinglePlayerCondition implements EndingCondition {
	private Duration gameLength;
	private ZonedDateTime epoch;
	private StatsObserver obs;

	public SinglePlayerCondition(Duration gameLength) {
		this.gameLength = gameLength;
		epoch = ZonedDateTime.now();
	}

	@Override
	public int check(BSMode mode) {
		HashMap<Integer, Bubble> bubbles = mode.board.getBubbles();
		int max = (mode.board.getGrid().getWidth()-1)* mode.board.getGrid().getHeight() - 1;
		
		for(int i = max-mode.board.getGrid().getWidth(); i <= max; i++)
			if(bubbles.containsKey(i)) return -1; 
		
		Duration deltaTime = Duration.between(epoch, ZonedDateTime.now());
		
		if (mode.board.isEmpty()) return 1;
		if (gameLength.compareTo(deltaTime) < 0) return -1;
		obs.drawTimer(gameLength.minus(deltaTime));
		return 0;
	}

	@Override
	public void addStatsObserver(StatsObserver o) {
		this.obs = o;
	}

}
