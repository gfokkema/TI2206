package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;

import com.badlogic.gdx.Gdx;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

public class MultiPlayerMode implements BSMode {
	private BubbleShooter game;
	private Board board1, board2;
	private Cannon cannon1, cannon2;
	private Projectile projectile1, projectile2;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	public MultiPlayerMode(BubbleShooter game) {
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		this.game = game;
		this.board1 = new Board(8, 15);
		this.board2 = new Board(8, 15);
		this.cannon1 = new Cannon(130, 15);
		this.cannon2 = new Cannon(380, 15);
		
		for (int i = 0; i < 40; i++) {
			board1.add(new Bubble(), i);
			board2.add(new Bubble(), i);
		}
	}

	@Override
	public Collection<BSDrawable> getDrawables() {
		Collection<BSDrawable> drawables = board1.getDrawables();
		drawables.add(cannon1);
		drawables.add(cannon1.getProjectile());
		if (projectile1 != null) drawables.add(projectile1);
		
		drawables.addAll(board2.getDrawables());
		drawables.add(cannon2);
		drawables.add(cannon2.getProjectile());
		if (projectile2 != null) drawables.add(projectile2);
		return drawables;
	}

	@Override
	public void update(float deltaTime) {
		if (cannonLeft) {
			cannon1.left(Gdx.graphics.getDeltaTime());
		} else if (cannonRight) {
			cannon1.right(Gdx.graphics.getDeltaTime());
		}

		if (projectile1 == null) return;

		projectile1.move();
		//NOTE: collides has side-effects!
		if (board1.collides(projectile1)) {
			int new_idx = board1.add(projectile1);
			projectile1 = null;

			if(new_idx == -1) return;

			Collection<Bubble> sameColors = board1.getColorGroup(new_idx);
			if (sameColors.size() >= 3) {
				board1.removeAll(sameColors);
				board1.removeAll(board1.getDisconnectedGroup());
			}
		}
	}
	
	@Override
	public Cannon getCannon() {
		return cannon1;
	}

	@Override
	public Projectile getProjectile() {
		return projectile1;
	}
	@Override
	public void setProjectile(Projectile projectile) {
		this.projectile1 = projectile;
	}

	@Override
	public void cannonLeft(boolean left) {
		this.cannonLeft = left;
	}

	@Override
	public void cannonRight(boolean right) {
		this.cannonRight = right;
	}
}
