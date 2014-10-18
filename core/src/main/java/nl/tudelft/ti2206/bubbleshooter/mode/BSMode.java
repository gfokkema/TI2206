package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;
import nl.tudelft.ti2206.bubbleshooter.util.GameObserver;
import nl.tudelft.ti2206.bubbleshooter.util.Logger;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import com.badlogic.gdx.math.Vector2;

/**
 * This class serves as the abstraction of all other game-modes. 
 * It contains the basic game-logic applicable to all other game-modes.
 * It acts as an observable, calling a {@link StatsObserver} to update.
 */
public abstract class BSMode implements EndingObserver {
	protected Iterator<Board> boards;
	protected Board board;
	protected Cannon cannon;
	protected Projectile projectile;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	private StatsObserver statsObs;
	protected GameObserver gameObs;

	protected EndingCondition end;
	private int score= 0;

	/**
	 * BSMode constructor containing a {@link Board}, {@link EndingCondition} and {@link Cannon}
	 * @param end the {@link EndingCondition} of the game.
	 * @param factory the used {@link BoardFactory} for the game.
	 * @param cannon the {@link Cannon} the user will be using.
	 */
	public BSMode(EndingCondition end, BoardFactory factory, Cannon cannon) {
		this.boards = factory.makeLevels().iterator();
		this.board = boards.next();
		this.cannon = cannon;
		this.end = end;
		this.score = 0;
		
		setProjectile(cannon.getProjectile());
		
		this.board.addObserver(Logger.getLogger());
		this.cannon.addObserver(Logger.getLogger());
		this.end.addEndingObserver(this);
	}

	/**
	 * The update method deals with the actual game-logic.
	 * Inside this method, the actual calls to rotating the cannon,
	 * moving the projectile, removing bubbles and updating the score is being taken care of.
	 * @param deltaTime	the time that has elapsed
	 */
	public void update(float deltaTime) {
		end.check(this.board);
		
		if (cannonLeft) cannon.left(deltaTime);
		if (cannonRight) cannon.right(deltaTime);
		if (projectile == null || projectile == cannon.getProjectile()) return;
		
		projectile.move();
		if (board.collides(projectile)) {
			score += board.getGrid().add(projectile);
			statsObs.updateScore(score);
			
			setProjectile(cannon.getProjectile());
		}
	}

	/**
	 * Add the {@link StatsObserver} to the {@link EndingCondition}.
	 * @param obs the statsobserver.
	 */
	public void addStatsObserver(StatsObserver obs) {
		this.statsObs = obs;
		end.addStatsObserver(obs);
	}

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
	 * Returns the {@link Board}.
	 * @return the {@link Board}.
	 */
	public Board getBoard() {
		return board;
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
	public int getScore() {
		return score;
	}
	
	/**
	 * Checks whether this is the last {@link Board}.
	 * @return	boolean indicating whether this is the last {@link Board}
	 */
	public boolean hasNext() {
		return boards.hasNext();
	}
	
	/**
	 * Switches {@link BSMode} to the next {@link Board}.
	 */
	public void next() {
		this.board.deleteObservers();
		this.board = boards.next();
		this.board.addObserver(Logger.getLogger());
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
