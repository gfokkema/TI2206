package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements LogStrategy {
	private BufferedWriter bw;
	
	public FileLogger(String filename) {
		try {
			bw = new BufferedWriter(new FileWriter(filename, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(String msg) {
		try {
			bw.write(msg + "\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
