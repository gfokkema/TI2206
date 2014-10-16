package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
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
 * @author group-15
 *
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
	 * @param board the used {@link Board} for the game.
	 * @param cannon the {@link Cannon} the user will be using.
	 */
	public BSMode(EndingCondition end, BoardFactory factory, Cannon cannon) {
		this.boards = factory.makeLevels().iterator();
		this.board = boards.next();
		
		board.addObserver(Logger.getLogger());
		
		this.cannon = cannon;
		cannon.addObserver(Logger.getLogger());
		setProjectile(cannon.getProjectile());

		this.end = end;
		end.addEndingObserver(this);
		this.score = 0;
	}

	/**
	 * The update method deals with the actual game-logic.
	 * Inside this method, the actual calls to rotating the cannon,
	 * moving the projectile, removing bubbles and updating the score is being taken care of.
	 * @param deltaTime
	 * @return boolean check if the game should end.
	 */
	public void update(float deltaTime) {
		if (cannonLeft) {
			cannon.left(deltaTime);
			//Logger.print("Cannon rotation left", "" + cannon.getRotation());
		}
		if (cannonRight) {
			cannon.right(deltaTime);
			//Logger.print("Cannon rotation right", "" + cannon.getRotation());
		}
		
		if (projectile == null || projectile == cannon.getProjectile()) {
			end.check(this.board);
			return;
		}

		projectile.move();
		//NOTE: collides has side-effects!
		if (board.collides(projectile)) {
			int new_idx = board.add(projectile);
			setProjectile(cannon.getProjectile());
			projectile.move();

			if(new_idx != -1) {			
				score += 3 * board.removeAll(board.getGroup(new_idx));

				for(Entry<Integer, Bubble> b: board.getPowerUps().entrySet()) {
					score += 3 * board.removeAll(b.getValue().getBehaviour().remove(board, b.getKey(), new_idx));
				}

				Collection<Bubble> disconnected = board.getDisconnectedGroup();
				board.removeAll(disconnected);

				score += 3 * disconnected.size();
				this.statsObs.drawScore(score);				
			}
		}
		end.check(this.board);
	}

	/**
	 * Add the {@link StatsObserver} to the {@link EndingCondition}.
	 * @param o the statsobserver.
	 */
	public void addStatsObserver(StatsObserver obs) {
		this.statsObs = obs;
		end.addStatsObserver(obs);
	}

	public void addGameObserver(GameObserver obs) {
		this.gameObs = obs;
	}

	/**
	 * Abstraction of getting the drawables.
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
	
	public boolean hasNext() {
		return boards.hasNext();
	}
	
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
