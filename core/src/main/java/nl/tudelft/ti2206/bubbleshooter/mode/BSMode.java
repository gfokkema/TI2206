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

public abstract class BSMode {
	protected Board board;
	protected Cannon cannon;
	protected Projectile projectile;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	public BSMode() {
		this.board = new Board(8, 15);
		this.cannon = new Cannon(160,15);
		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}
	}

	public void update(float deltaTime) {
		if (cannonLeft) {
			cannon.left(Gdx.graphics.getDeltaTime());
		}
		if (cannonRight) {
			cannon.right(Gdx.graphics.getDeltaTime());
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
						board.removeAll(board.getDisconnectedGroup());
					}
				}
			}
		}
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
