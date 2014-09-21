package nl.tudelft.ti2206.bubbleshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;

public class GameLostScreen extends AbstractScreen {

	public GameLostScreen(BubbleShooter game) {
		super(game);
		LabelStyle labelstyle = new LabelStyle(game.font, Color.WHITE);
		Label lost = new Label("You LOST!", labelstyle);
		Label info = new Label("Press ESC to return", labelstyle);
		lost.setScale(5);
		table.add(lost).expandX().center().row();
		table.add(info).expandX().center().row();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}
