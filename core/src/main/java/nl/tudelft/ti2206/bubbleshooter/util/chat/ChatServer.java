package nl.tudelft.ti2206.bubbleshooter.util.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//NIET NODIG
import nl.tudelft.ti2206.bubbleshooter.core.Grid;

public class ChatServer implements Runnable {

	private ObjectInputStream br = null;
	private ObjectOutputStream bw = null;

	@Override
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(15004);

			Socket socket = serverSocket.accept();

			bw = new ObjectOutputStream(socket.getOutputStream());
			bw.flush();
			br = new ObjectInputStream(socket.getInputStream());

			Object o = br.readObject();

			if (o instanceof String) {
				ChatMessage msg = new ChatMessage((String) o);
				System.out.println(msg);
			}

			System.out.println("CHATSERVER CONNECTED!");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
