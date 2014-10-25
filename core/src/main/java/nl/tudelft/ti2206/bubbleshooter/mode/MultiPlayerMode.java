package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import nl.tudelft.ti2206.bubbleshooter.core.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.core.Background;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.OpponentAdapter;
import nl.tudelft.ti2206.bubbleshooter.score.Level;
import nl.tudelft.ti2206.bubbleshooter.score.Score;
import nl.tudelft.ti2206.bubbleshooter.util.GameObserver;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import com.badlogic.gdx.math.Vector2;
/**
 * Multiplayer mode for playing with your friends!
 * This mode allows an user to player across the network to play to each other.
 * @author group-15
 *
 */
public class MultiPlayerMode extends GameMode implements Runnable, Observer {
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Background bg;
	private Cannon cannon2;
	private Grid grid2;
	private Projectile projectile2;
	private Vector2 offset1, offset2;
	private EndingCondition condition2;
	private StatsObserver opponentStatsObs;
	private OpponentAdapter opponentEndingObs;
	private Score opponentScore;

	/**
	 * The Multiplayer mode constructor.
	 * @param end the {@link EndingCondition}.
	 * @param grids the {@link Grid} {@link Iterator}
	 * @param score the {@link Score} of the local player.
	 * @param oppScore the opponents {@link Score}
	 * @param in the {@link ObjectInputStream}.
	 * @param out the {@link ObjectOutputStream}.
	 */
	public MultiPlayerMode(EndingCondition end, Iterator<Grid> grids, Score score, Score oppScore, ObjectInputStream in, ObjectOutputStream out) {
		super(end, grids, score);
		this.opponentScore = oppScore;
		oppScore.setLevel(new Level(1, grid.getName()));
		
		this.in = in;
		this.out = out;
		new Thread(this).start();

		bg = new Background();

		this.offset1 = new Vector2(0, 0);
		this.offset2 = new Vector2(320, 0);

		write(end);
		write(score);
		write(grid);
		write(cannon);
		write(cannon.getProjectile());
		
		grid.addObserver(this);
		cannon.addObserver(this);
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
		this.write(end);
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

	private void setConditionOpp(EndingCondition ec) {
		this.condition2 = ec;
		condition2.addEndingObserver(opponentEndingObs);
	}

	private void setScoreOpp(Score score) {
		opponentScore.update(score);
	}
	
	public void write(Object o) {
		try {
			out.writeObject(o);
			out.flush();
			out.reset();
		} catch (IOException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runnable that copes with input read from sockets.
	 */
	@Override
	public void run() {
		try {
			while (true) {
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
			}
		} catch (IOException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof BSDrawable) write(o);
	}

	public void addOpponentStatsObserver(StatsObserver multi) {
		opponentStatsObs = multi;
		condition2.addStatsObserver(opponentStatsObs);
	}
	
	public void disconnect() {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
		} finally {
			in = null;
			out = null;
		}
	}
	
	@Override
	public void lost() {
		super.lost();
		disconnect();
	}

	@Override
	public void won() {
		super.won();
		disconnect();
	}
}
