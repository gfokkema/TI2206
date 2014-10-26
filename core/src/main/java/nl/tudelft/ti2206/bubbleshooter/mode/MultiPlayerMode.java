package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Multiplayer mode for playing with your friends!
 * This mode allows an user to player across the network to play to each other.
 * @author group-15
 *
 */
public class MultiPlayerMode extends GameMode implements Runnable, StatsObserver, Observer {
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Background bg;
	private Cannon cannon2;
	private Grid grid2;
	private Projectile projectile2;
	private Vector2 offset1, offset2;
	private EndingCondition condition2;
	private OpponentAdapter opponentEndingObs;
	private Score opponentScore;

	/**
	 * Start a new {@link Thread} to read the {@link ObjectInputStream}, and write
	 * the local fields to the opponent using the {@link ObjectOutputStream}.
	 * @param end 		the {@link EndingCondition}.
	 * @param grids 	the {@link Grid} {@link Iterator} for cycling through levels.
	 * @param score 	the {@link Score} of the local player.
	 * @param oppScore	the opponent's {@link Score}.
	 * @param in		the {@link ObjectInputStream} for writing {@link Object}s to the opponent.
	 * @param out		the {@link ObjectOutputStream} for receiving {@link Object} of the opponent.
	 */
	public MultiPlayerMode(EndingCondition end, Iterator<Grid> grids, Score score, Score oppScore, ObjectInputStream in, ObjectOutputStream out) {
		super(end, grids, score);
		this.score.addStatsObserver(this);
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
	 * Gets the {@link Drawable}s for rendering the game.
	 * @return a {@link HashMap} containing all the {@link Drawable}s, with their offsets as keys.
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
	 * Aside from updating the local state, also check the opponent's
	 * {@link EndingCondition}, and write our own {@link EndingCondition} so
	 * that the opponent can check ours.
	 * @param deltaTime	the time that has elapsed since the last frame
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
	 * @param grid	the {@link Grid} of the opponent
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
	 * Setter for opponent's {@link Projectile}.
	 * @param pj	the {@link Projectile} of the opponent 
	 */
	public synchronized void setProjectileOpp(Projectile pj) {
		this.projectile2 = pj;
	}

	/**
	 * Set the opponent's {@link EndingCondition}.
	 * @param ec	the {@link EndingCondition} of the opponent.
	 */
	private void setConditionOpp(EndingCondition ec) {
		this.condition2 = ec;
		condition2.addEndingObserver(opponentEndingObs);
	}

	/**
	 * Update the opponentScore object with the new {@link Score} object
	 * sent by the opponent.
	 * @param score	the opponent's {@link Score}.
	 */
	private void setScoreOpp(Score score) {
		opponentScore.update(score);
	}
	
	/**
	 * Write an {@link Object} to the {@link ObjectOutputStream}
	 * @param o	the {@link Object} to be written.
	 */
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
	
	/**
	 * Disconnect from the opponent.
	 */
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
	
	/**
	 * Called by the {@link EndingCondition} when we've lost.
	 */
	@Override
	public void lost() {
		super.lost();
		disconnect();
	}

	/**
	 * Called by the {@link EndingCondition} when we've won.
	 */
	@Override
	public void won() {
		super.won();
		disconnect();
	}

	@Override
	public void updateTimer(Duration duration) {}

	/**
	 * Write our own {@link Score} to the {@link ObjectOutputStream}, because
	 * it has changed.
	 */
	@Override
	public void updateScore(Score score) {
		write(score);
	}
}
