package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import nl.tudelft.ti2206.bubbleshooter.core.Background;
import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SinglePlayerMode extends BSMode {
	private Background bg;
	private Vector2 offset;
	
	public SinglePlayerMode(EndingCondition end, Board board, Cannon cannon) {
		super(end, board, cannon);
		this.bg = new Background();
		this.offset = new Vector2(140, 0);
	}
	
	public SinglePlayerMode(EndingCondition end) {
		super(end);
		this.bg = new Background();
		this.offset = new Vector2(140, 0);
		Gdx.input.setInputProcessor(new SinglePlayerProcessor(this));
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
}
