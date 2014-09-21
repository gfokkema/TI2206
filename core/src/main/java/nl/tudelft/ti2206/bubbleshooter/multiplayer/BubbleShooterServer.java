package nl.tudelft.ti2206.bubbleshooter.multiplayer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import nl.tudelft.ti2206.bubbleshooter.core.Board;

public class BubbleShooterServer {
	private ServerSocket gameListener = null;
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private InputStream is = null;

	public BubbleShooterServer() {

	}

	public void serverSocket() {
		try {
			gameListener = new ServerSocket(getIP.getPort());
			socket = gameListener.accept();
			while (true) {
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Board br = (Board) ois.readObject();

				is.close();
				ois.close();
				socket.close();
				gameListener.close();
			}

		} catch (Exception ex) {

		}
	}

	public static void main(String[] args) throws IOException {
		BubbleShooterServer server = new BubbleShooterServer();
		server.serverSocket();
	
	}
}