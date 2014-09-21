package nl.tudelft.ti2206.bubbleshooter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * This is a utility class that can be used to query the system for information
 * about your local an external IP addresses.
 */
public class getIP {
	/**
	 * This method retrieves your local IP address using Java.
	 * @return						a String with your local IP address
	 * @throws UnknownHostException	when this method could not determine your IP
	 */
	public static String getLocalIP() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress().toString();
	}

	/**
	 * This method retrieves your external IP by using an amazon service.
	 * @return				a String with your external IP address
	 * @throws IOException	when this method could not determine your IP
	 */
	public static String getExternIP() throws IOException {
		URL externIP = new URL("http://checkip.amazonaws.com");
		BufferedReader read = new BufferedReader(new InputStreamReader(externIP.openStream()));
		return read.readLine();
	}
}
