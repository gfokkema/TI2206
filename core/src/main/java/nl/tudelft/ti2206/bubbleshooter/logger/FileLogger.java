package nl.tudelft.ti2206.bubbleshooter.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The file logger.
 * Responsible for logging to the file.
 * @author group-15
 *
 */
public class FileLogger implements LogStrategy {
	private BufferedWriter bw;
	
	/**
	 * Creates a {@link FileLogger} using the specified buffered writer.
	 * @param bw	output writer
	 */
	public FileLogger(BufferedWriter bw) {
		this.bw = bw;
	}
	
	/**
	 * Constructor for FileLogger
	 * @param filename the filename
	 */
	public FileLogger(String filename) {
		try {
			bw = new BufferedWriter(new FileWriter(filename, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Write to the file.
	 */
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
