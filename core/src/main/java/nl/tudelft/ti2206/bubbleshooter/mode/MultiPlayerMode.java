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
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class MultiPlayerMode extends BSMode implements Runnable {
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Background bg;
	private Board board2;
	private Cannon cannon2;
	private Projectile projectile2;
	private Vector2 offset1, offset2;

	public MultiPlayerMode(EndingCondition end, ObjectInputStream in,
			ObjectOutputStream out) {
		super(end);
		this.in = in;
		this.out = out;
		new Thread(this).start();

		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		bg = new Background();

		this.offset1 = new Vector2(0, 0);
		this.offset2 = new Vector2(320, 0);

		for (int i = 0; i < 40; i++) {
			board.add(new Bubble(), i);
		}

		writeBoard(board);
		writeCannon(cannon);
	}

	@Override
	public HashMap<Vector2, Collection<BSDrawable>> getDrawables() {
		HashMap<Vector2, Collection<BSDrawable>> odraw = new HashMap<>();
		ArrayList<BSDrawable> drawables1 = new ArrayList<>();
		drawables1.add(bg);

		drawables1.addAll(board.getDrawables());
		drawables1.add(cannon);
		drawables1.add(cannon.getProjectile());
		if (projectile != null)
			drawables1.add(projectile);
		odraw.put(offset1, drawables1);

		if (board2 != null && cannon2 != null) {
			Collection<BSDrawable> drawables2 = board2.getDrawables();
			drawables2.add(cannon2);
			drawables2.add(cannon2.getProjectile());
			if (projectile2 != null)
				drawables2.add(projectile2);
			odraw.put(offset2, drawables2);
		}
		return odraw;
	}

	@Override
	public int update(float deltaTime) {
		// FIXME: do not send board every frame
		writeBoard(board);
		writeCannon(cannon);
		writeProjectile(projectile);
		return super.update(deltaTime);
	}

	public synchronized void setBoardOpp(Board board) {
		this.board2 = board;
	}
	
	public synchronized void setCannonOpp(Cannon cn) {
		this.cannon2 = cn;
	}
	
	public synchronized void setProjectileOpp(Projectile pj){
		this.projectile2 = pj;
	}

	public void writeBoard(Board board) {
		try {
			out.writeObject(board);
			out.flush();
			out.reset();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void writeCannon(Cannon cn) {
		try {
			out.writeObject(cn);
			out.flush();
			out.reset();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void writeProjectile(Projectile pj){
		try{
			if (projectile == null)
				out.writeObject("reset");
			else
				out.writeObject(pj);
			out.flush();
			out.reset();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object o = in.readObject();
				if (o instanceof Board) {
					setBoardOpp((Board) o);
				} else if (o instanceof Cannon) {
					setCannonOpp((Cannon) o);
				} else if (o instanceof Projectile) {
					setProjectileOpp((Projectile) o);
				} else if (o instanceof String && ((String)o).equals("reset")) {
					setProjectileOpp(null);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
