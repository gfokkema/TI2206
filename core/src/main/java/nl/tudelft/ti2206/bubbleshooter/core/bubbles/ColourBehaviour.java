package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import java.util.Collection;
import java.util.HashSet;

import com.badlogic.gdx.graphics.Color;

import nl.tudelft.ti2206.bubbleshooter.core.GridCell;

public class ColourBehaviour implements BubbleBehaviour {
	/**
	 * We use a field instead of a local variable, because Java
	 * doesn't support local variable closures.
	 */
	int size = 0;

	@Override
	public int chain(GridCell cell) {
		cell.removeBubble();
		return 1;
	}
	
	/**
	 * Remove the {@link Bubble}s of the same {@link Color} that are connected
	 * to this {@link GridCell}, but only if it's a group of 3 or more {@link Bubble}s.
	 * @param cell	The {@link GridCell} from where the search is started.
	 * @return The amount of {@link Bubble}s that got removed.
	 */
	@Override
	public int remove(GridCell cell) {
		Collection<GridCell> sameColors = new HashSet<GridCell>();
		sameColors.add(cell);
		Bubble base = cell.getBubble();
		cell.forEachNeighbor(g -> g.depthFirst(sameColors, (Bubble b) -> compareColors(base, b), true));
		size = sameColors.size();
		if (size >= 3) {
			sameColors.forEach((GridCell g) -> {
				g.removeBubble();
				size++;
			});
		} else {
			size = 0;
		}
		return size;
	}

	/**
	 * Compare the {@link Color}s of two {@link Bubble}s.
	 * @param a	The first {@link Bubble}.
	 * @param b The second {@link Bubble}.
	 * @return	true if the {@link Bubble}s are of the same {@link Color}
	 */
	public boolean compareColors(Bubble a, Bubble b) {
		return a.getColor().equals(b.getColor());
	}

	/**
	 * Does nothing, as it's not a {@link BubbleBehaviour} that gets triggered.
	 */
	@Override
	public int trigger(GridCell cell) {
		return 0;
	}
}
