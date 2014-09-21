package nl.tudelft.ti2206.bubbleshooter.multiplayer;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

public class BubbleShooterClient extends Thread {

	public static void main(String[] args) {
		try {

			Socket ClientSocket = new Socket(getIP.getLocalIP(),
					getIP.getPort());

			// ipv getLocalIP, kan je ook getExternIP doen

			OutputStream s = ClientSocket.getOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(s);
			Board b = new Board(2, 6);

			os.writeObject(b);

			//
			// PrintWriter output = new PrintWriter(
			// ClientSocket.getOutputStream(), true);
			// output.println("Connection succeeded");

			// hier komt de precieze input voor klasse board die serialized word

			os.flush();
			s.close();
			os.close();
			ClientSocket.close();

		} catch (Exception iox) {
			System.out.println("Connection horribly failed.");
		}
	}

}
