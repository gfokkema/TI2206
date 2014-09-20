package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;

import com.badlogic.gdx.Gdx;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

public class ZenMode implements BSMode {
	BubbleShooter game;
	Board board;
	Cannon cannon;
	Projectile projectile;

	public ZenMode(BubbleShooter game) {
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		this.game = game;
		this.board = new Board(8, 15);
		this.cannon = new Cannon(130,15);
		
		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}
	}

	@Override
	public Collection<BSDrawable> getDrawables() {
		Collection<BSDrawable> drawables = board.getDrawables();
		drawables.add(cannon);
		drawables.add(cannon.getProjectile());
		if (projectile != null) drawables.add(projectile);
		return drawables;
	}

	@Override
	public void update(float deltaTime) {
		if (projectile == null) return;

		projectile.move();
		//NOTE: collides has side-effects!
		if (board.collides(projectile)) {
			int new_idx = board.add(projectile);
			projectile = null;

			if(new_idx == -1) return;

			Collection<Bubble> sameColors = board.getColorGroup(new_idx);
			if (sameColors.size() >= 3) {
				board.removeAll(sameColors);
				board.removeAll(board.getDisconnectedGroup());
			}
		}
	}
}
