package nl.tudelft.ti2206.bubbleshooter.mode;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

/**
 * The Single-Player {@link EndingCondition}.
 * @author group-15
 *
 */
public class SinglePlayerCondition implements EndingCondition {
	private Duration gameLength;
	private ZonedDateTime epoch;
	private StatsObserver obs;

	/**
	 * Constructor for the Single-Player {@link EndingCondition}.
	 * @param gameLength the maximum time given for the game.
	 */
	public SinglePlayerCondition(Duration gameLength) {
		this.gameLength = gameLength;
		epoch = ZonedDateTime.now();
		
	}

	/**
	 * The actual {@link EndingCondition} for the Single-Player mode.
	 */
	@Override
	public int check(BSMode mode) {
		HashMap<Integer, Bubble> bubbles = mode.board.getBubbles();
		
		int width = mode.board.getGrid().getWidth();
		int height = mode.board.getGrid().getHeight();
		
		int max = (int) ((2*width -1) * Math.floor(height/2));
		if (Math.floor(height/2) == height/2)
			width--;
		else
			max += width;

		for(int i = max-width; i <= max; i++)
			if(bubbles.containsKey(i)) return -1; 
				
		Duration deltaTime = Duration.between(epoch, ZonedDateTime.now());
		if (mode.board.isEmpty()) return 1;
		if (gameLength.compareTo(deltaTime) < 0) return -1;
		obs.drawTimer(gameLength.minus(deltaTime));
		
		return 0;
	}

	/**
	 * Adds a {@link StatsObserver}.
	 */
	@Override
	public void addStatsObserver(StatsObserver o) {
		this.obs = o;
	}

}
