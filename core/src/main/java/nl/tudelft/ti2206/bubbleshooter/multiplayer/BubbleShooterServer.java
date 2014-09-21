package nl.tudelft.ti2206.bubbleshooter.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BubbleShooterServer {

	public static void main(String[] args) throws IOException {

		ServerSocket gameListener = new ServerSocket(getIP.getPort());
		ArrayList<Socket> socketList;

		try {
			socketList = new ArrayList<Socket>();
			while (true) {
				Socket socket = gameListener.accept();
				socketList.add(socket);
				try {
					Socket socket1 = gameListener.accept();

					InputStreamReader is = new InputStreamReader(
							socket1.getInputStream());
					BufferedReader br = new BufferedReader(is);

					br.close();
					is.close();
					socket1.close();
				} catch (Exception iox) {
					System.out
							.println("It isn't working, maybe later it will, maybe it will not. *For Windows-users: ERROR: PRESS OK");
				}

			}
		} catch (Exception iox) {
			System.out.println("werkt niet");
		}
	}
}

// game BS = new launch();
// BS.board board1 = BS.new Board(listener.accept(), ' key
// voor ->');
// board1(listener.accept(),'key voor <-');
// board1(listener.accept().'key voor space');

// BS.board board2 = BS.new Player(listener.accept(), ' key
// voor ->');
// board(listener.accept(),'key voor <-');
// board(listener.accept().'key voor space');

// BS.setOpponent(board1,board2);
// board1.launch();
// board2.launch();

// OF en wss wat we gaan doen
//
// board als raw data doorheen sluizen
// en steeds opnieuw drawen
//
//
