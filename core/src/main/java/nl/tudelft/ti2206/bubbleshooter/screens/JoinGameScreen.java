package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.Launch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class JoinGameScreen extends AbstractScreen {
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public JoinGameScreen(Launch game) {
		super(game);
	}
	
	@Override
	public void show() {
		super.show();
		
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
				//game.setScreen(new BubbleShooterScreen(game));
				System.out.println(text.getText());
			}
		});
		
		table.add(label).expandX().center().row();
		table.add(text).expandX().center().row();
		table.add(connect).expandX().center().row();
	}
}