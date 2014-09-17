package nl.tudelft.ti2206.bubbleshooter.multiplayer;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Host {

	public void returnIP() throws UnknownHostException {
		System.out.println(Inet4Address.getLocalHost().getHostAddress());
	}

	public void main(String[] args) {
		try {
			returnIP();
		} catch (UnknownHostException e) {

			System.out.println("mislukt");
		}
	}

}
