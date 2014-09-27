package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import com.badlogic.gdx.math.Vector2;

/**
 * This class serves as the abstraction of all other game-modes. 
 * It contains the basic game-logic applicable to all other game-modes.
 * Furthermore it deals with drawing the score onto the playing-field.
 * @author group-15
 *
 */
public abstract class BSMode {
	protected Board board;
	protected Cannon cannon;
	protected Projectile projectile;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	private StatsObserver obs;
	private EndingCondition end;
	private int score;

	/**
	 * BSMode constructor containing a {@link Board}, {@link EndingCondition} and {@link Cannon}
	 * @param end the {@link EndingCondition} of the game.
	 * @param board the used {@link Board} for the game.
	 * @param cannon the {@link Cannon} the user will be using.
	 */
	public BSMode(EndingCondition end, Board board, Cannon cannon) {
		this.board = board;
		this.cannon = cannon;
		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}
		this.end = end;
		this.score = 0;
	}
	
	/**
	 * Secondary constructor which will be called in the various game-modes.
	 * Only giving a {@link EndingCondition} should be enough; 
	 * board and cannon are no different in different game-modes.
	 * @param end the {@link EndingCondition} to which the game will end.
	 */
	public BSMode(EndingCondition end) {
		this(end, new Board(8, 15), new Cannon(160,15));
	}

	/**
	 * The update method deals with the actual game-logic.
	 * Inside this method, the actual calls to rotating the cannon,
	 * moving the projectile, removing bubbles and updating the score is being taken care of.
	 * @param deltaTime
	 * @return boolean check if the game should end.
	 */
	public int update(float deltaTime) {
		if (cannonLeft) {
			cannon.left(deltaTime);
		}
		if (cannonRight) {
			cannon.right(deltaTime);
		}

		if (projectile != null) {
			projectile.move();
			//NOTE: collides has side-effects!
			if (board.collides(projectile)) {
				int new_idx = board.add(projectile);
				projectile = null;

				if(new_idx != -1) {
					Collection<Bubble> sameColors = board.getColorGroup(new_idx);
					if (sameColors.size() >= 3) {
						board.removeAll(sameColors);
						Collection<Bubble> disconnected = board.getDisconnectedGroup();
						board.removeAll(disconnected);

						score += 3 * disconnected.size() + 3 * sameColors.size() - 3;
						this.obs.drawScore(score);
					}
				}
			}
		}
		return end.check(this);
	}

	/**
	 * Add the {@link StatsObserver} to the {@link EndingCondition}.
	 * @param o the statsobserver.
	 */
	public void addStatsObserver(StatsObserver o) {
		this.obs = o;
		end.addStatsObserver(o);
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
		this.projectile = projectile;
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
}
