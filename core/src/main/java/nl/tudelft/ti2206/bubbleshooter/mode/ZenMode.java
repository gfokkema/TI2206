package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Background;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ZenMode implements BSMode {
	private Background bg;
	private Board board;
	private Cannon cannon;
	private Projectile projectile;
	private Vector2 offset;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	public ZenMode() {
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		bg = new Background();
		this.board = new Board(8, 15);
		this.cannon = new Cannon(160,15);
		this.offset = new Vector2(140, 0);
		
		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}
	}

	@Override
	public HashMap<Vector2, Collection<BSDrawable>> getDrawables() {
		HashMap<Vector2, Collection<BSDrawable>> odraw = new HashMap<>();
		Collection<BSDrawable> drawablesbg = new ArrayList<>();
		drawablesbg.add(bg);
		odraw.put(new Vector2(0, 0), drawablesbg);
		
		Collection<BSDrawable> drawables = board.getDrawables();
		drawables.add(cannon);
		drawables.add(cannon.getProjectile());
		if (projectile != null) drawables.add(projectile);
		odraw.put(offset, drawables);
		return odraw;
	}

	@Override
	public void update(float deltaTime) {
		if (cannonLeft) {
			cannon.left(Gdx.graphics.getDeltaTime());
		} else if (cannonRight) {
			cannon.right(Gdx.graphics.getDeltaTime());
		}

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
	
	@Override
	public Cannon getCannon() {
		return cannon;
	}

	@Override
	public Projectile getProjectile() {
		return projectile;
	}
	@Override
	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
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
