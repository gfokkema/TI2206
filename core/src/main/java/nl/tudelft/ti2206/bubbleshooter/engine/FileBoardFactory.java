package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.BomBubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.BubbleColors;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.ColourBubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.MichaelBayBubble;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.StoneBubble;

public abstract class FileBoardFactory extends BoardFactory {	
	/**
	 * Create a Board list from a file
	 * @param res	internal path to the file
	 * @return		list of Boards
	 * @throws IOException	when the file could not be read
	 */
	public List<Board> parseFile(String res) throws IOException {
		InputStream in = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));
		InputStreamReader is = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(is);
		
		return parseFile(br);
	}
	
	/**
	 * Create a Board list from a {@link BufferedReader}
	 * @param br	{@link BufferedReader} containing 1 or more Boards
	 * @return		list of Boards
	 * @throws IOException	when the stream could not be read or is invalid
	 */
	public List<Board> parseFile(BufferedReader br) throws IOException {
		ArrayList<Board> boards = new ArrayList<>();
		
		String line;
		while ((line = br.readLine()) != null) {
			if (!line.matches("--- BEGIN --- .* ---")) throw new IOException();
			
			StringWriter bw = new StringWriter();
			while ((line = br.readLine()) != null && !line.matches("--- END --- .* ---"))
				bw.write(line + "\n");
			boards.add(parseLevel(bw.toString()));
		}
		
		return boards;
	}
	
	/**
	 * Parse a String containing the full specification of a single {@link Board}
	 * @param s		a String containing the full specification of 1 {@link Board}
	 * @return		list of Boards
	 * @throws IOException	when the stream could not be read or is invalid
	 */
	public Board parseLevel(String s) throws IOException {
		String[] lines = s.split("\\r?\\n");
		String[] dimensions = lines[0].split("x");
		int[] dim = { Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]) };
		
		Board board = new Board(dim[0], dim[1]);
		for (int y = 1; y < lines.length; y++) {
			lines[y] = lines[y].trim();
			String[] bubbles = lines[y].split("   ");
			
			for (int x = 0; x < bubbles.length && x < board.getWidth() - (y - 1) % 2; x++) {
				Bubble b = parseType(bubbles[x]);
				if (b != null) add(board, b, x, y - 1);
			}
		}
		return board;
	}
	
	protected Bubble parseType(String bubble) {
		if (bubble.equals("---")) return null;
		
		String type = bubble.substring(0, 1);
		int version = Integer.parseInt(bubble.substring(1,3));
		switch(type) {
			case "C":	return new ColourBubble(BubbleColors.values()[version].getColor());
			case "B":	return new BomBubble();
			case "S":	return new StoneBubble();
			case "M":	return new MichaelBayBubble();
			default:	return null;
		}	
	}
	
}
