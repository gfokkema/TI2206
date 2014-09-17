package nl.tudelft.ti2206.bubbleshooter.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;

public class getIP {

	public static String getLocalIP() throws UnknownHostException {
		String ip = Inet4Address.getLocalHost().getHostAddress().toString();
		System.out.println("Local IP =" + ip);
		return ip;

	}

	//Werd me teveel try catch blokjes, daarom de I/O
	public static String getExternIP() throws IOException {
		URL externIP = new URL("http://checkip.amazonaws.com");
		BufferedReader read = null;
		try {
			read = new BufferedReader(new InputStreamReader(
					externIP.openStream()));
			String ip = read.readLine();
			System.out.println("External IP = " + ip);
			return ip;
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					System.out.println("mislukt");
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			getLocalIP();
		} catch (UnknownHostException e) {

			System.out.println("mislukt");
		}
		try {
			getExternIP();
		} catch (Exception e) {
			System.out.println("mislukt");
		}

	}

}
