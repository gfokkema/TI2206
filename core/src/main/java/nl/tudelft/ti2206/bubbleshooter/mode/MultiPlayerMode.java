package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.Background;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactory;
import nl.tudelft.ti2206.bubbleshooter.engine.MPBoardFactory;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.util.GameObserver;
import nl.tudelft.ti2206.bubbleshooter.util.OpponentAdapter;
import nl.tudelft.ti2206.bubbleshooter.util.Score;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

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
	private Cannon cannon2;
	private Grid grid2;
	private Projectile projectile2;
	private Vector2 offset1, offset2;
	private EndingCondition condition2;
	private Score oppScore;
	private StatsObserver opponentStatsObs;
	private OpponentAdapter opponentEndingObs;

	/**
	 * The Multiplayer mode constructor.
	 * @param end the {@link EndingCondition}.
	 * @param factory the used {@link BoardFactory} for the game.
	 * @param cannon the used {@link Cannon} for the game.
	 * @param in the {@link ObjectInputStream}.
	 * @param out the {@link ObjectOutputStream}.
	 */
	protected MultiPlayerMode(EndingCondition end, BoardFactory factory, Cannon cannon, ObjectInputStream in, ObjectOutputStream out) {
		super(end, factory, cannon);
		
		this.in = in;
		this.out = out;
		new Thread(this).start();

		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		bg = new Background();

		this.offset1 = new Vector2(0, 0);
		this.offset2 = new Vector2(320, 0);

		this.grid = factory.makeLevels().get(0);
		this.oppScore = new Score(0, "multi");

		writeCondition(end);
		writeDrawable(grid);
		writeDrawable(cannon);
		writeDrawable(cannon.getProjectile());
		writeScore(this.getScore());
		
		grid.addObserver(this);
		cannon.addObserver(this);
		getScore().addObserver(this);
	}

	/**
	 * The Multiplayer mode constructor with some default values.
	 * @param end the {@link EndingCondition}.
	 * @param in the {@link ObjectInputStream}.
	 * @param out the {@link ObjectOutputStream}.
	 */
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

		drawables1.addAll(grid.getDrawables());
		drawables1.add(cannon);
		drawables1.add(cannon.getProjectile());
		cannon.getProjectile().addObserver(this);
		if (projectile != cannon.getProjectile()) drawables1.add(projectile);
		odraw.put(offset1, drawables1);

		if (grid2 == null || cannon2 == null || projectile2 == null) return odraw;
		
		Collection<BSDrawable> drawables2 = grid2.getDrawables();
		drawables2.add(cannon2);
		drawables2.add(cannon2.getProjectile());
		if (projectile2 != cannon2.getProjectile()) drawables2.add(projectile2);
		odraw.put(offset2, drawables2);
		
		return odraw;
	}

	/**
	 * Updates every frame the cannon,board and projectile.
	 * @param deltaTime	the time that has elapsed
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (grid2 == null) return;
		condition2.check(this.grid2);
		this.writeCondition(end);
	}

	
	@Override
	public void addGameObserver(GameObserver obs) {
		super.addGameObserver(obs);
		this.opponentEndingObs = new OpponentAdapter(obs);
	}

	/**
	 * Setter for oppponent's cannon.
	 * @param cn	the {@link Cannon} of the opponent
	 */
	public synchronized void setCannonOpp(Cannon cn) {
		this.cannon2 = cn;
	}
	
	/**
	 * Setter for opponent's board.
	 * @param board	the {@link Board} of the opponent
	 */
	public synchronized void setGridOpp(Grid grid) {
		this.grid2 = grid;
		
		//this.opponentStatsObs.updateScore(new Score(111, grid2.getName()));
	}
	
	@Override
	public void setProjectile(Projectile projectile) {
		super.setProjectile(projectile);
		projectile.addObserver(this);
	}

	/**
	 * Setter for opponent projectile.
	 * @param pj	the {@link Projectile} of the opponent 
	 */
	public synchronized void setProjectileOpp(Projectile pj) {
		this.projectile2 = pj;
	}
	
	/**
	 * Setter for opponent projectile.
	 * @param pj	the {@link Projectile} of the opponent 
	 */
	public synchronized void setScoreOpp(Score s) {
		this.oppScore.update(s);
	}

	private void setConditionOpp(EndingCondition ec) {
		this.condition2 = ec;
		condition2.addEndingObserver(opponentEndingObs);
	}

	/**
	 * Writes the {@link EndingCondition} over the network.
	 * @param condition	the {@link EndingCondition} of the player
	 */
	public void writeCondition(EndingCondition condition) {
		try {
			out.writeObject(condition);
			out.flush();
			out.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes a {@link BSDrawable} over the network.
	 * @param d	the {@link BSDrawable} of the player
	 */
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
	 * Writes a {@link BSDrawable} over the network.
	 * @param d	the {@link BSDrawable} of the player
	 */
	public void writeScore(Score s) {
		try {
			out.writeObject(s);
			out.flush();
			out.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runnable that copes with input read from sockets.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Object o = in.readObject();
				if (o instanceof Grid) {
					setGridOpp((Grid) o);
				} else if (o instanceof Cannon) {
					setCannonOpp((Cannon) o);
				} else if (o instanceof Projectile) {
					setProjectileOpp((Projectile) o);
				} else if (o instanceof EndingCondition) {
					setConditionOpp((EndingCondition) o);
				} else if (o instanceof Score) {
					setScoreOpp((Score) o);
				}
			} catch (EOFException e) {
				
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof BSDrawable) writeDrawable((BSDrawable) o);
		else if (o instanceof Score) System.out.println("Score!");
	}

	public void addOpponentStatsObserver(StatsObserver multi) {
		opponentStatsObs = multi;
		condition2.addStatsObserver(opponentStatsObs);
	}
}
