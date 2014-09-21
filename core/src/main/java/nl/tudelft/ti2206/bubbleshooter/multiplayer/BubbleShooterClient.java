package nl.tudelft.ti2206.bubbleshooter.multiplayer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import nl.tudelft.ti2206.bubbleshooter.core.Board;

public class BubbleShooterClient extends Thread {

	public BubbleShooterClient() {

	}

	public void clientSocket() {

		OutputStream s;
		try {
			Socket ClientSocket = new Socket(getIP.getLocalIP(),
					getIP.getPort());
			s = ClientSocket.getOutputStream();

			ObjectOutputStream os = new ObjectOutputStream(s);
			Board b = new Board(2, 6);

			os.writeObject(b);

			os.flush();
			s.close();
			os.close();
			ClientSocket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		BubbleShooterClient client = new BubbleShooterClient();
		client.clientSocket();
	}
}
