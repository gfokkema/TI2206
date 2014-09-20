package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.Launch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class HostGameScreen extends AbstractScreen {
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public HostGameScreen(Launch game) {
		super(game);
		
		LabelStyle style = new LabelStyle(game.font, Color.WHITE);
		Label label = new Label("Please wait for a player to connect", style);
		table.add(label).expandX().center().row();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}