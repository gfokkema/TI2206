package nl.tudelft.ti2206.bubbleshooter.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class represents a {@link Grid} that can be used as a playing field.
 * The {@link Grid} is made up of {@link GridCell}s, which can contain a {@link Bubble}.
 * {@link GridCell}s are linked to their neighbors for easy group finding. 
 */
public class Grid extends BSDrawable implements Serializable, Collidable, Observer {
	private static final long serialVersionUID = -3156876087711309439L;
	private int width = 8, height = 20;
	private String name;
	public HashMap<Integer, GridCell> cells;
	
	/**
	 * Constructs a {@link Grid} with all its {@link GridCell}s connected to one another.
	 * @param name		the name of this grid
	 * @param width		the width of the grid in bubbles
	 * @param height	the height of the grid in bubbles
	 */
	public Grid(String name, int width, int height) {
		this.width = width;
		this.height = height;
		this.name = name;
		
		cells = new HashMap<>(width * height - height / 2);
		for (int i = 0; i < width * height - height / 2; i++) {
			GridCell c = new GridCell(new Circle(getLoc(i), 16));
			c.addObserver(this);
			cells.put(i, c);
			for (Integer neighbor_idx : getAdjacent(i)) {
				c.connect(cells.get(neighbor_idx));
			}
		}
	}
	
	/**
	 * Add a {@link Bubble} to a {@link GridCell} on a specific {@link Grid} index.
	 * @param b		{@link Bubble} that has to be added
	 * @param idx	{@link Grid} index
	 * @return	the {@link GridCell} this {@link Bubble} was added to, or null if the {@link GridCell} was occupied.
	 */
	public GridCell add(Bubble b, Integer idx) {
		GridCell cell = cells.get(idx);
		if (cell.isOccupied()) return null;

		cell.setBubble(b);

		setChanged();
		notifyObservers("Grid: Bubble has been added to index " + idx + ".");
		return cell;
	}

	/**
	 * Add a {@link Bubble} to the {@link Grid} on a specific hex index.
	 * @param b	{@link Bubble} that has to be added
	 * @param i	hexagonal x-coordinate
	 * @param j	hexagonal y-coordinate
	 * @return	the {@link GridCell} this {@link Bubble} was added to, or null if the {@link GridCell} was occupied.
	 */
	public GridCell add(Bubble b, int i, int j) {
		return add(b, toIdx(i, j));
	}

	/**
	 * Adds a projectile to the board using it's current location.
	 * @param p	the {@link Projectile} that has to be added to the board
	 * @return	the {@link GridCell} this {@link Bubble} was added to, or null if the {@link GridCell} was occupied.
	 */
	public GridCell add(Projectile p) {
		return add(p, getIndex(p.getMidPoint()));
	}
	
	/**
	 * Checks whether there are {@link GridCell}s with {@link Bubble}s below the {@link Grid} losing line. Uses a
	 * default row.
	 * @return	true if there are {@link Bubble}s below the line, false otherwise
	 */
	public boolean bubbleBelowLine() {
		return bubbleBelowLine(getGridHeight() - 2);
	}

	/**
	 * Checks whether there are {@link GridCell}s with {@link Bubble}s below the specified losing line.
	 * @param lineRow	the specified losing line
	 * @return	true if there are {@link Bubble}s below the line, false otherwise
	 */
	protected boolean bubbleBelowLine(int lineRow) {
		int start = toIdx(0,lineRow);
		int lastRowWidth = getGridWidth() - (getGridHeight() % 2 - 1);
		int finish = toIdx(lastRowWidth - 1, getGridHeight() - 1);
		for(int i = start; i <= finish; i++) {
			if(cells.get(i).isOccupied()) return true;
		}
		return false;
	}
	
	/**
	 * Checks the {@link Projectile} that gets shot for collisions with all the other bubbles.
	 * @param p	{@link Projectile} that has been shot
	 * @return	true if there's a collision, false otherwise
	 */
	public boolean collides(Projectile p) {
		for (GridCell c : cells.values()) {
			if (c.isOccupied() && c.collides(p))
				return true;
		}
		if (p.getBounds().y + 16 > 480) return true;
		if (p.getBounds().x - 16 < 32
				|| p.getBounds().x - 16 > getGridWidth() * 32 - 1) {
			Vector2 dir = p.getDirection();
			dir.x = -dir.x;
		}
		return false;
	}
	
	/**
	 * This method returns the list of {@link Color}s that are still in the playing field.
	 * @return	a list of {@link Color}s
	 */
	public ArrayList<Color> getColoursAvailable() {
		HashSet<Color> colours = new HashSet<Color>();
		for(GridCell c: getFilledGridCells()) {
			Color color = c.getBubble().getColor();
			// If the color of a bubble is WHITE, then it is a special bubble
			if(color != Color.WHITE) colours.add(color);
		}
		return new ArrayList<Color>(colours);
	}
	
	/**
	 * This method inserts 2 rows of {@link Bubble}s starting from the given row index.
	 * @param row	the row index
	 */
	public void insertRows(int row) {
		for (int i = cells.size() - 1; i >= toIdx(0, row); i--) {
			GridCell curCell = cells.get(i);
			int new_idx = i - toIdx(0, 2);
			if (!curCell.isOccupied() && new_idx >= 0) {
				GridCell newCell = cells.get(new_idx);
				curCell.setBubble(newCell.getBubble());
				newCell.removeBubble();
			}
		}
		
		setChanged();
	}

	/**
	 * This method starts the {@link Bubble} removal of {@link Bubble}s that aren't
	 * connected to the ceiling anymore.
	 * @return	The number of {@link Bubble}s that were removed.
	 */
	public int removeDisconnected() {
		Collection<GridCell> connected = new ArrayList<GridCell>();
		for (int i = 0; i < width; i++) {
			cells.get(i).depthFirst(connected);
		}
		Collection<GridCell> disconnected = new HashSet<GridCell>(getFilledGridCells());
		disconnected.removeAll(connected);
		disconnected.forEach((GridCell gc) -> gc.removeBubble());
		setChanged();
		notifyObservers("Grid: Removing " + disconnected.size() + "disconnected bubbles.");
		return disconnected.size();
	}

	/**
	 * This method returns the number of {@link Bubble}s in the grid.
	 * @return	the number of {@link Bubble}s in the grid.
	 */
	public int size() {
		return getFilledGridCells().size();
	}

	/**
	 * This method returns a {@link Collection} of {@link GridCell}s that have a {@link Bubble}.
	 * @return	collection of {@link GridCell}s
	 */
	public Collection<GridCell> getFilledGridCells() {
		return cells.values().stream()
							 .filter(gc -> gc.isOccupied())
							 .collect(Collectors.toList());
	}

	/**
	 * Returns the width of the {@link Grid}.
	 * @return	width in bubbles
	 */
	public int getGridWidth() {
		return width;
	}
	
	/**
	 * Returns the height of the {@link Grid}.
	 * @return	height in bubbles
	 */
	public int getGridHeight() {
		return height;
	}
	
	/**
	 * Returns the name of this {@link Grid}.
	 * @return	the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method checks whether the board is empty.
	 * @return	true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * This method returns all {@link BSDrawable}s that this {@link Grid} contains.
	 * @return	collection of {@link BSDrawable}
	 */
	public Collection<BSDrawable> getDrawables() {
		Collection<BSDrawable> drawables = new ArrayList<BSDrawable>();
		drawables.add(this);
		getFilledGridCells().forEach(c -> drawables.add(c));
		return drawables;
	}

	/**
	 * Checks whether two {@link Grid} indices are adjacent to each other.
	 * @param a		index a
	 * @param b		index b
	 * @return		true if a and b are adjacent, false otherwise
	 */
	public boolean adjacent(int a, int b) {
		if (a > b) { int temp = a; a = b; b = temp; }
		if (a < 0)	return false;
		if (b >= this.height * this.width - this.height / 2) return false;

		Vector2 xy_a = toXY(a);
		Vector2 xy_b = toXY(b);
		
		return	xy_a.y == xy_b.y && b - a == 1 ||
				xy_b.y - xy_a.y == 1 && b - a > 0 &&
					((b - a) == width || (b - a) == width - 1);
	}
	
	/**
	 * Returns all adjacent fields for a specified index.
	 * @param idx	the index of the starting bubble
	 * @return		list of adjacent indices
	 */
	public ArrayList<Integer> getAdjacent(int idx) {
		ArrayList<Integer> adjacent = new ArrayList<>();
		for (Orientation o : Orientation.values()) {
			int new_idx = o.fromIndex(idx, getGridWidth());
			if (this.adjacent(idx, new_idx)) {
				adjacent.add(new_idx);
			}
		}
		return adjacent;
	}

	/**
	 * Converts a given hexagonal xy-coordinate into an index value
	 * @param x		hexagonal x-coordinate
	 * @param y		hexagonal y-coordinate
	 * @return		the board index
	 */
	public int toIdx(int x, int y) {
		if (x < 0 || y < 0 || x >= width - y % 2 || y >= height)
			throw new IndexOutOfBoundsException();
		
		return y * width - y / 2 + x;
	}

	/**
	 * Converts a given board index into a hexagonal xy-coordinate
	 * @param idx	the board index
	 * @return		{@link Vector2} representing the hexagonal xy-coordinates
	 */
	public Vector2 toXY(int idx) {
		int rowset = (width * 2 - 1);
		
		int y_even = idx / rowset;
		int y = y_even * 2 + (idx - y_even * rowset) / width;
		int x = idx - toIdx(0, y);
		
		if (idx < 0 || y >= height) throw new IndexOutOfBoundsException();
		return new Vector2(x, y);
	}
	
	/**
	 * Returns the topleft xy-coordinates of a bubble
	 * @param idx	the index of the bubble on the board
	 * @return		{@link Vector2} representing xy-coordinates
	 */
	public Vector2 getLoc(int idx) {
		int odd = (idx % (width * 2 - 1)) / width;
		Vector2 xy = toXY(idx);
		
		int x = (int)xy.x * 32	+ odd * 16 + 32;
		int y = (int)xy.y * 28;
		
		// offset the game field with 190 px and correct from left -> mid
		x = x + 16;
		// flip the coordinate system and correct from top -> mid
		y = 480 - (y + 16);

		return new Vector2(x, y);
	}

	/**
	 * Get the index of the grid cell corresponding to the given
	 * {@link Vector2}.
	 * @param loc The (x,y)-coordinate on the grid.
	 * @return The index of the grid containing the given
	 *         coordinate.
	 */
	public int getIndex(Vector2 loc) {
		int x = (int)loc.x;
		int y = (int)loc.y;
		x = x - 34;
		y = 480 - y;
		int y_id = y / 28;
		int x_id = x - (y_id & 1) * 16;
		x_id /= 32;
		
		return toIdx(x_id, y_id);
	}

	/**
	 * Returns the {@link TextureID} for the grid border that has to be drawn.
	 * @return	{@link TextureID} for the grid border
	 */
	@Override
	public TextureID getTexture() {
		return TextureID.BORDER;
	}

	/**
	 * Returns the width of this {@link Grid}.
	 * @return	width in pixels
	 */
	@Override
	public int getWidth() {
		return 320;
	}

	/**
	 * Returns the height of this {@link Grid}.
	 * @return	height in pixels
	 */
	@Override
	public int getHeight() {
		return 480;
	}

	/**
	 * This method is used to notify {@link Observer}s of changes.
	 */
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}
}
