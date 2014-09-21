package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class MultiPlayerMode extends BSMode implements Runnable {
	private BufferedReader in;
	private BufferedWriter out;
	
	private Background bg;
	private Board board2;
	private Cannon cannon2;
	private Projectile projectile2;
	private Vector2 offset1, offset2;
	// FUGLY, doesn't belong here...
	protected boolean cannonLeft;
	protected boolean cannonRight;

	public MultiPlayerMode(BufferedReader in, BufferedWriter out) {
		super();
		this.in = in;
		this.out = out;
		new Thread(this).start();
		
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
		bg = new Background();
		
		this.offset1 = new Vector2(0, 0);
		
		this.offset2 = new Vector2(320, 0);
		this.board2 = new Board(8, 15);
		this.cannon2 = new Cannon(160, 15);
		
		for (int i = 0; i < 40; i++) {
			board2.add(new Bubble(), i);
		}
	}

	@Override
	public HashMap<Vector2, Collection<BSDrawable>> getDrawables() {
		HashMap<Vector2, Collection<BSDrawable>> odraw = new HashMap<>();
		ArrayList<BSDrawable> drawables1 = new ArrayList<>();
		drawables1.add(bg);
		
		drawables1.addAll(board.getDrawables());
		drawables1.add(cannon);
		drawables1.add(cannon.getProjectile());
		if (projectile != null) drawables1.add(projectile);
		odraw.put(offset1, drawables1);
		
		Collection<BSDrawable> drawables2 = board2.getDrawables();
		drawables2.add(cannon2);
		drawables2.add(cannon2.getProjectile());
		if (projectile2 != null) drawables2.add(projectile2);
		odraw.put(offset2, drawables2);
		return odraw;
	}

	@Override
	public void run() {
		while (true) {
			try {
				out.write("TESTING\r\n");
				out.flush();
				System.out.println("DEBUG: wrote something");
				System.out.println(in.readLine());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
