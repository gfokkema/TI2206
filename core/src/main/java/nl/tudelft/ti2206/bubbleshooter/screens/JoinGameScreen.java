package nl.tudelft.ti2206.bubbleshooter.screens;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.mode.BelowLineCondition;
import nl.tudelft.ti2206.bubbleshooter.mode.MultiPlayerMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The screen shown when joining a multi-player game.
 * @author group-15
 *
 */
public class JoinGameScreen extends AbstractScreen {
	private ObjectInputStream br = null;
	private ObjectOutputStream bw = null;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public JoinGameScreen(BubbleShooter game) {
		super(game);
		
		LabelStyle labelstyle = new LabelStyle(game.font, Color.WHITE);
		TextFieldStyle textstyle = new TextFieldStyle(game.font, Color.WHITE, null, null, null);
		TextButtonStyle buttonstyle = new TextButtonStyle();
		buttonstyle.font = game.font;
		
		Label label = new Label("Please enter the IP address of a server", labelstyle);
		TextField text = new TextField("", textstyle);
		TextButton connect = new TextButton("Connect", buttonstyle);
		
		connect.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.engine.play(SoundID.BUTTON);
				//game.setScreen(new BubbleShooterScreen(game));
				
				// FIXME: MAYBE MAKE THIS FAULT TOLERANT
				connect(text.getText());
			}
		});
		
		table.add(label).expandX().center().row();
		table.add(text).expandX().center().row();
		table.add(connect).expandX().center().row();
	}
	
	/**
	 * Implements the escape key on your keyboard for our UI which brings the user back to the main menu.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
	/**
	 * Implements our client socket wih datastreams.
	 * @param ip
	 */
	public void connect(String ip) {
		try {
			Socket socket = new Socket(ip, 8008);

			bw = new ObjectOutputStream(socket.getOutputStream());
			bw.flush();
			br = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("CLIENT CONNECTED!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Gdx.app.exit();
		}
		game.setScreen(new BubbleShooterScreen(game, new MultiPlayerMode(new BelowLineCondition(), br, bw)));
	}
}