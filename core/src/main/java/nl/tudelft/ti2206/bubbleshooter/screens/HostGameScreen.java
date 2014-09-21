package nl.tudelft.ti2206.bubbleshooter.screens;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.mode.MultiPlayerMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class HostGameScreen extends AbstractScreen implements Runnable {
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public HostGameScreen(BubbleShooter game) {
		super(game);
		
		LabelStyle style = new LabelStyle(game.font, Color.WHITE);
		Label label = new Label("Please wait for a player to connect", style);
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

			InputStreamReader is = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(is);
			
			OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
			bw = new BufferedWriter(os);
			System.out.println("SERVER CONNECTED!");
		} catch (Exception iox) {
			System.out.println(iox.getMessage());
			Gdx.app.exit();
		}
		game.setScreen(new BubbleShooterScreen(game, new MultiPlayerMode(br, bw)));
	}
}