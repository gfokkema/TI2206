package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.GridCell;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.logger.Logger;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.score.Level;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;
import nl.tudelft.ti2206.bubbleshooter.util.GameObserver;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import com.badlogic.gdx.math.Vector2;

/**
 * This class serves as the abstraction of all other game-modes. 
 * It contains the basic game-logic applicable to all other game-modes.
 * It acts as an observable, calling a {@link StatsObserver} to update.
 */
public abstract class GameMode implements EndingObserver {
	protected Iterator<Grid> grids;
	protected Grid grid;
	protected Cannon cannon;
	protected Projectile projectile;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	protected GameObserver gameObs;

	protected EndingCondition end;
	protected Score score;

	/**
	 * {@link GameMode} constructor containing a {@link Grid}, {@link EndingCondition} and {@link Cannon}
	 * @param end		the {@link EndingCondition} of the game
	 * @param grids		the used {@link Grid}s for the game
	 * @param score		the {@link Score} the user will be using
	 */
	public GameMode(EndingCondition end, Iterator<Grid> grids, Score score) {
		this.grids = grids;
		this.grid = grids.next();
		this.cannon = new Cannon(160,15);
		this.end = end;
		this.score = score;
		score.setLevel(new Level(1, grid.getName()));
		setProjectile(cannon.getProjectile());
		
		this.cannon.addObserver(Logger.getLogger());
		this.end.addEndingObserver(this);
	}

	/**
	 * The update method deals with the actual game-logic.
	 * Inside this method, the calls to rotating the cannon,
	 * moving the projectile, removing bubbles and updating the score is being taken care of.
	 * @param deltaTime	the time that has elapsed since the last frame
	 */
	public void update(float deltaTime) {
		end.check(this.grid);
		
		if (cannonLeft) cannon.left(deltaTime);
		if (cannonRight) cannon.right(deltaTime);
		if (projectile == null || projectile == cannon.getProjectile()) return;
		
		projectile.move();
		GridCell g;
		if (grid.collides(projectile) && (g = grid.add(projectile)) != null) {
			setProjectile(cannon.getProjectile());
			// FIXME:	we manually move the projectile in order to
			//			trigger the network sync...
			projectile.move();
			
			// FIXME: Reenable this again when testing with insertrows is done
			score.add(g.remove() + g.triggerNeighbors() + grid.removeDisconnected());
			insertRows(0);
		}
	}
	
	public void insertRows(int row) {
		return;
	}

	/**
	 * Adds a game observer to this {@link GameMode}.
	 * @param obs	the {@link GameObserver} to add
	 */
	public void addGameObserver(GameObserver obs) {
		this.gameObs = obs;
	}

	/**
	 * Abstract method for getting all drawables.
	 * @return {@link HashMap} containing all {@link BSDrawable} objects and their offset
	 */
	public abstract HashMap<Vector2, Collection<BSDrawable>> getDrawables();

	/**
	 * Returns the {@link Cannon}.
	 * @return the {@link Cannon}.
	 */
	public Cannon getCannon() {
		return cannon;
	}
	
	/**
	 * Returns the {@link Grid}.
	 * @return the {@link Grid}.
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Returns the {@link Projectile}.
	 * @return the {@link Projectile}.
	 */
	public Projectile getProjectile() {
		return projectile;
	}

	/**
	 * Sets the {@link Projectile}.
	 * @param projectile the {@link Projectile}.
	 */
	public void setProjectile(Projectile projectile) {
		if (this.projectile != null) this.projectile.deleteObservers();
		this.projectile = projectile;
		this.projectile.addObserver(Logger.getLogger());
	}

	// FUGLY, doesn't belong here...
	public void cannonLeft(boolean left) {
		this.cannonLeft = left;
	}

	public void cannonRight(boolean right) {
		this.cannonRight = right;
	}
	
	/**
	 * Gets the {@link #score} of the game.
	 * @return the {@link #score}.
	 */
	public Score getScore() {
		return score;
	}
	
	/**
	 * Checks whether this is the last {@link Grid}.
	 * @return	boolean indicating whether this is the last {@link Grid}
	 */
	public boolean hasNext() {
		return grids.hasNext();
	}
	
	/**
	 * Switches {@link GameMode} to the next {@link Grid}.
	 */
	public void next() {
		this.grid.deleteObservers();
		this.grid = grids.next();
		score.setLevel(new Level(score.getLevel().getLevel() + 1, grid.getName()));
		this.grid.addObserver(Logger.getLogger());
	}

	@Override
	public void lost() {
		gameObs.switchToLostScreen();
	}

	@Override
	public void won() {
		gameObs.switchToWonScreen();
	}
}
