package nl.tudelft.ti2206.bubbleshooter.multiplayer;

import java.io.PrintWriter;
import java.net.Socket;

public class BubbleShooterClient extends Thread {

	public static void main(String[] args) {
		try {

			Socket ClientSocket = new Socket(getIP.getLocalIP(),
					getIP.getPort());
			// ipv getLocalIP, kan je ook getExternIP doen
			PrintWriter output = new PrintWriter(
					ClientSocket.getOutputStream(), true);
			output.println("Connection succeeded");

			// hier komt de precieze input voor klasse board die serialized word

			output.flush();

			output.close();
			ClientSocket.close();

		} catch (Exception iox) {
			System.out.println("Connection horribly failed.");
		}
	}

}
