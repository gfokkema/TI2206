package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import nl.tudelft.ti2206.bubbleshooter.core.Background;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.MPBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Multiplayer mode for playing with your friends!
 * This mode allows an user to player across the network to play to each other.
 * @author group-15
 *
 */
public class MultiPlayerMode extends BSMode implements Runnable, Observer {
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Background bg;
	private Board board2;
	private Cannon cannon2;
	private Projectile projectile2;
	private Vector2 offset1, offset2;
	private int condition2;

	/**
	 * The Multiplayer mode constructor.
	 * @param end the {@link EndingCondition}.
	 * @param in the {@link ObjectInputStream}.
	 * @param out the {@link ObjectOutputStream}.
	 */
	public MultiPlayerMode(EndingCondition end, BoardFactory factory, Cannon cannon, ObjectInputStream in, ObjectOutputStream out) {
		super(end, factory, cannon);
		
		this.in = in;
		this.out = out;
		new Thread(this).start();

		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		bg = new Background();

		this.offset1 = new Vector2(0, 0);
		this.offset2 = new Vector2(320, 0);
		
		this.condition2 = 0;
		
		try {
			board = factory.parseFile("levels/mpboard.txt").get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		writeDrawable(board);
		writeDrawable(cannon);
		writeDrawable(cannon.getProjectile());
		
		board.addObserver(this);
		cannon.addObserver(this);
	}
	
	public MultiPlayerMode(EndingCondition end, ObjectInputStream in, ObjectOutputStream out) {
		this(end, new MPBoardFactory(), new Cannon(160,15), in, out);
	}

	/**
	 * Gets the drawables for the multiplayer-game.
	 * @return a hash-map containing all the drawables.
	 */
	@Override
	public HashMap<Vector2, Collection<BSDrawable>> getDrawables() {
		HashMap<Vector2, Collection<BSDrawable>> odraw = new HashMap<>();
		ArrayList<BSDrawable> drawables1 = new ArrayList<>();
		drawables1.add(bg);

		drawables1.addAll(board.getDrawables());
		drawables1.add(cannon);
		drawables1.add(cannon.getProjectile());
		cannon.getProjectile().addObserver(this);
		if (projectile != cannon.getProjectile()) drawables1.add(projectile);
		odraw.put(offset1, drawables1);

		if (board2 == null || cannon2 == null || projectile2 == null) return odraw;
		
		Collection<BSDrawable> drawables2 = board2.getDrawables();
		drawables2.add(cannon2);
		drawables2.add(cannon2.getProjectile());
		if (projectile2 != cannon2.getProjectile()) drawables2.add(projectile2);
		odraw.put(offset2, drawables2);
		
		return odraw;
	}

	/**
	 * Updates every frame the cannon,board and projectile.
	 */
	@Override
	public int update(float deltaTime) {
		//if (super.update(deltaTime) != 0) writeCondition(super.update(deltaTime));
		return super.update(deltaTime) + condition2;
	}

	/**
	 * Setter for opponent's board.
	 * @param board
	 */
	public synchronized void setBoardOpp(Board board) {
		this.board2 = board;
	}

	/**
	 * Setter for oppponent's cannon.
	 * @param cn
	 */
	public synchronized void setCannonOpp(Cannon cn) {
		this.cannon2 = cn;
	}
	
	@Override
	public void setProjectile(Projectile projectile) {
		super.setProjectile(projectile);
		projectile.addObserver(this);
	}

	/**
	 * Setter for opponent projectile.
	 * @param pj
	 */
	public synchronized void setProjectileOpp(Projectile pj) {
		this.projectile2 = pj;
	}
	
	public synchronized void setConditionOpp(int condition) {
		this.condition2 = condition;
	}

	/**
	 * Writes the condition.
	 * @param condition
	 */
	public void writeCondition(int condition) {
		try {
			Gdx.app.log("condition", "" + condition);
			out.writeInt(-1*condition);
			out.flush();
			out.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeDrawable(BSDrawable d) {
		try {
			out.writeObject(d);
			out.flush();
			out.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runnable that copes with read input by sockets.
	 */
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
				}

				setConditionOpp(in.readInt());
				
			} catch (EOFException e) {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof BSDrawable) writeDrawable((BSDrawable) o);
	}
}
