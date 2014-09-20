package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.Launch;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MultiPlayerScreen extends AbstractScreen {
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public MultiPlayerScreen(Launch game) {
		super(game);
	}
	
	@Override
	public void show() {
		super.show();
		
		TextButtonStyle style = new TextButtonStyle();
		style.font = game.font;
		
		TextButton host = new TextButton("Host a game", style);
		TextButton join = new TextButton("Join a game", style);
		
		host.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new HostGameScreen(game));
			}
		});
		join.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new JoinGameScreen(game));
			}
		});
		
		table.add(host).expandX().center().row();
		table.add(join).expandX().center().row();
	}
}