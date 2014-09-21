package nl.tudelft.ti2206.bubbleshooter.screens;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.mode.MultiPlayerMode;
import nl.tudelft.ti2206.bubbleshooter.multiplayer.getIP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class HostGameScreen extends AbstractScreen implements Runnable {
	private ObjectInputStream br = null;
	private ObjectOutputStream bw = null;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public HostGameScreen(BubbleShooter game) {
		super(game);
		
		String extip = "unknown";
		String intip = "unknown";
		try {
			extip = getIP.getExternIP();
		} catch (Exception e) {}
		try {
			intip = getIP.getLocalIP();
		} catch (Exception e) {}
		
		LabelStyle style = new LabelStyle(game.font, Color.WHITE);
		
		Label extiplabel = new Label("External IP: " + extip, style);
		Label intiplabel = new Label("Internal IP: " + intip, style);
		Label label = new Label("Please wait for a player to connect", style);
		
		table.add(extiplabel).expandX().center().row();
		table.add(intiplabel).expandX().center().row();
		table.add(label).expandX().center().row();
		
		new Thread(this).start();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(8008);
			Socket socket = serverSocket.accept();

			br = new ObjectInputStream(socket.getInputStream());
			bw = new ObjectOutputStream(socket.getOutputStream());
			
			System.out.println("SERVER CONNECTED!");
		} catch (Exception iox) {
			System.out.println(iox.getMessage());
			Gdx.app.exit();
		}
		game.setScreen(new BubbleShooterScreen(game, new MultiPlayerMode(br, bw)));
	}
}