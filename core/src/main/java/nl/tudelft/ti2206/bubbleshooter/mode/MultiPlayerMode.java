package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class MultiPlayerMode implements BSMode, Runnable {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private Background bg;
	private Board board1, board2;
	private Cannon cannon1, cannon2;
	private Projectile projectile1, projectile2;
	private Vector2 offset1, offset2;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	public MultiPlayerMode(ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		this.out = out;
		new Thread(this).start();
		
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		bg = new Background();
		
		this.offset1 = new Vector2(0, 0);
		this.board1 = new Board(8, 15);
		this.cannon1 = new Cannon(160, 15);
		
		this.offset2 = new Vector2(320, 0);
		this.board2 = new Board(8, 15);
		this.cannon2 = new Cannon(160, 15);
		
		for (int i = 0; i < 40; i++) {
			board1.add(new Bubble(), i);
			board2.add(new Bubble(), i);
		}
	}

	@Override
	public HashMap<Vector2, Collection<BSDrawable>> getDrawables() {
		HashMap<Vector2, Collection<BSDrawable>> odraw = new HashMap<>();
		ArrayList<BSDrawable> drawables1 = new ArrayList<>();
		drawables1.add(bg);
		
		drawables1.addAll(board1.getDrawables());
		drawables1.add(cannon1);
		drawables1.add(cannon1.getProjectile());
		if (projectile1 != null) drawables1.add(projectile1);
		odraw.put(offset1, drawables1);
		
		Collection<BSDrawable> drawables2 = board2.getDrawables();
		drawables2.add(cannon2);
		drawables2.add(cannon2.getProjectile());
		if (projectile2 != null) drawables2.add(projectile2);
		odraw.put(offset2, drawables2);
		return odraw;
	}

	@Override
	public void update(float deltaTime) {
		if (cannonLeft) {
			cannon1.left(Gdx.graphics.getDeltaTime());
		}
		if (cannonRight) {
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
		try {
			out.writeObject(board1);
		} catch (Exception e) {}
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
	
	@Override
	public void run() {
		while (true) {
			try {
				Object o = in.readObject();
				if (o instanceof Board)
					board2 = (Board)o;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
