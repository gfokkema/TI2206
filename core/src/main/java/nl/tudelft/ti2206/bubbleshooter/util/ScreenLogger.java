package nl.tudelft.ti2206.bubbleshooter.util;

public class ScreenLogger implements LogStrategy {
	@Override
	public void write(String msg) {
		System.out.println(msg);	
	}
}
