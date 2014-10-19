package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.Background;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Single-Player mode.
 * This is one of the modes the user is able to play.
 * @author group-15
 *
 */
public class SinglePlayerMode extends BSMode {
	private Background bg;
	private Vector2 offset;
	
	/**
	 * Constructor for the Single-Player mode.
	 * @param end the used {@link EndingCondition}.
	 * @param factory the used {@link BoardFactory} for the game.
	 * @param cannon the used {@link Cannon}.
	 */
	public SinglePlayerMode(EndingCondition end, BoardFactory factory, Cannon cannon) {
		super(end, factory, cannon);
		this.bg = new Background();
		this.offset = new Vector2(140, 0);
	}
	
	/**
	 * Constructor for the Single-Player mode with some default values.
	 * @param end the used {@link EndingCondition}.
	 * @param factory the used {@link BoardFactory} for the game.
	 */
	public SinglePlayerMode(EndingCondition end, BoardFactory factory) {
		this(end, factory, new Cannon(160,15));
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
	}

	/**
	 * Gets the drawables for the multiplayer-game.
	 * @return a hash-map containing all the drawables.
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

	@Override
	public void won() {
		if(hasNext()) next();
		else gameObs.switchToWonScreen();
	}
}
