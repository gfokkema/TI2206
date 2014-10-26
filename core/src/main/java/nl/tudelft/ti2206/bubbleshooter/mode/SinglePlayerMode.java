package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.Background;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Score;

import com.badlogic.gdx.math.Vector2;

/**
 * Single-Player mode.
 * This is one of the modes the user is able to play.
 * @author group-15
 *
 */
public class SinglePlayerMode extends GameMode {
	private Background bg;
	private Vector2 offset;
	
	/**
	 * Initialize the {@link Background} and the {@link Grid}'s offset.
	 * @param end		the used {@link EndingCondition}.
	 * @param grids		an {@link Iterator} to cycle through the {@link Grid}s of this game.
	 * @param score		the {@link Score} of the player.
	 */
	public SinglePlayerMode(EndingCondition end, Iterator<Grid> grids, Score score) {
		super(end, grids, score);
		this.bg = new Background();
		this.offset = new Vector2(140, 0);
	}

	/**
	 * Gets the {@link Drawable}s for rendering the game.
	 * @return a {@link HashMap} containing all the {@link Drawable}s, with their offsets as keys.
	 */
	@Override
	public HashMap<Vector2, Collection<BSDrawable>> getDrawables() {
		HashMap<Vector2, Collection<BSDrawable>> odraw = new HashMap<>();
		Collection<BSDrawable> drawablesbg = new ArrayList<>();
		drawablesbg.add(bg);
		odraw.put(new Vector2(0, 0), drawablesbg);
		
		Collection<BSDrawable> drawables = grid.getDrawables();
		drawables.add(cannon);
		drawables.add(cannon.getProjectile());
		if (projectile != null) drawables.add(projectile);
		odraw.put(offset, drawables);
		return odraw;
	}

	/**
	 * Go to the next level if there is won, otherwise change the screen to show that we've
	 * won.
	 */
	@Override
	public void won() {
		if(hasNext()) next();
		else super.won();
	}
}
