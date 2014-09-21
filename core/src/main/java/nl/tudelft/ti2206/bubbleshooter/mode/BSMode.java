package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

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

	public BSMode(EndingCondition end, Board board, Cannon cannon) {
		this.board = board;
		this.cannon = cannon;
		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}
		this.end = end;
		this.score = 0;
	}
	
	public BSMode(EndingCondition end) {
		this(end, new Board(8, 15), new Cannon(160,15));
	}

	public boolean update(float deltaTime) {
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
		return !end.check(this);
	}

	public void addStatsObserver(StatsObserver o) {
		this.obs = o;
		end.addStatsObserver(o);
	}

	public abstract HashMap<Vector2, Collection<BSDrawable>> getDrawables();

	public Cannon getCannon() {
		return cannon;
	}

	public Projectile getProjectile() {
		return projectile;
	}

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
}
