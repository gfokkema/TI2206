package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble.BubbleColors;

import com.badlogic.gdx.Gdx;

public abstract class BoardFactory {
	public List<Board> parseFile(String res) throws IOException {
		InputStream in = Gdx.files.internal(res).read();
		InputStreamReader is = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(is);
		
		return parseFile(br);
	}
	
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
	
	public Board parseLevel(String s) throws IOException {
		String[] lines = s.split("\\r?\\n");
		String[] dimensions = lines[0].split("x");
		int[] dim = { Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]) };
		
		Board board = new Board(dim[0], dim[1]);
		BubbleColors[] colors = Bubble.BubbleColors.values();
		
		for (int y = 1; y < lines.length; y++) {
			String[] bubbles = lines[y].split("  ");
			for (int x = 0 + (y - 1) % 2; x < bubbles.length && x < board.getWidth(); x++) {
				int colorvalue = Integer.parseInt(bubbles[x]);
				board.add(new Bubble(colors[colorvalue].getColor()), x - (y - 1) % 2, y - 1);
			}
		}
		return board;
	}
}
