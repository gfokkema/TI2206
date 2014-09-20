package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

public class ZenMode implements BSMode {
	BubbleShooter game;
	Board board;
	Cannon cannon;
	Projectile projectile;

	public ZenMode(BubbleShooter game) {
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		this.game = game;
		this.board = new Board(8, 15);
		this.cannon = new Cannon(game.assets.get(TextureID.CANNON), Gdx.graphics.getWidth() / 2, 15);
		
		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}
	}

	@Override
	public Collection<Sprite> getDrawables() {
		//TODO
		return null;
	}

	@Override
	public void update(float deltaTime) {
		if (projectile != null) {
			if (projectile.getBounds().x - 16 < 190 || projectile.getBounds().x + 16 > 190 + board.getWidth() * 32) {
				Vector2 dir = projectile.getDirection();
				dir.x = -dir.x;
			}
			if (board.collides(projectile) || projectile.getBounds().y + 16 > 480) {
				int new_idx = board.getIndex(projectile.getPosition());
				if (board.add(projectile, new_idx)) {
					Collection<Bubble> sameColors = board.getColorGroup(new_idx);
					if (sameColors.size() >= 3) {
						board.removeAll(sameColors);
						board.removeAll(board.getDisconnectedGroup());
					}
				}
				projectile = null;
			}
		}
	}
}
