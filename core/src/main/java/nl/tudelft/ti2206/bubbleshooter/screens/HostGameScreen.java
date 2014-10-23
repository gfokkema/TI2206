package nl.tudelft.ti2206.bubbleshooter.screens;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.mode.MultiPlayerMode;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BasicCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.EndingCondition;
import nl.tudelft.ti2206.bubbleshooter.ui.GameUIBuilder;
import nl.tudelft.ti2206.bubbleshooter.util.Score;
import nl.tudelft.ti2206.bubbleshooter.util.getIP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * The screen shown when hosting a multi-player game.
 * @author group-15
 *
 */
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
		
		Label extiplabel = new Label("External IP: " + extip, labelStyle);
		Label intiplabel = new Label("Internal IP: " + intip, labelStyle);
		Label label = new Label("Please wait for a player to connect", labelStyle);
		
		table.add(extiplabel).expandX().center().row();
		table.add(intiplabel).expandX().center().row();
		table.add(label).expandX().center().row();
		
		new Thread(this).start();
	}
	
	/**
	 *  Implements the escape key on your keyboard for our UI which brings the user back to the main menu.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}

	/**
	 * Implements our server socket with datastreams.
	 */
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(8008);
			Socket socket = serverSocket.accept();

			bw = new ObjectOutputStream(socket.getOutputStream());
			bw.flush();
			br = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("SERVER CONNECTED!");
		} catch (Exception iox) {
			System.out.println(iox.getMessage());
			Gdx.app.exit();
		}
		Gdx.app.postRunnable(() -> {
			EndingCondition basic = new BasicCondition();
			EndingCondition belowLine = new BelowLineCondition(basic);
			GameUIBuilder gub = new GameUIBuilder(game.font);
			MultiPlayerMode multi = new MultiPlayerMode(belowLine, br, bw);
			gub.addMultiPlayerStatsBars(belowLine, multi.getScore(), new Score(0, "multi"));
			game.setScreen(new BubbleShooterScreen(game, multi, gub.build()));
		});
	}
}