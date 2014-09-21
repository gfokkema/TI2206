package nl.tudelft.ti2206.bubbleshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;

public class GameWonScreen extends AbstractScreen {

	public GameWonScreen(BubbleShooter game) {
		super(game);
		LabelStyle labelstyle = new LabelStyle(game.font, Color.WHITE);
		Label won = new Label("You WON!", labelstyle);
		Label info = new Label("Press ESC to return", labelstyle);
		won.setScale(5);
		table.add(won).expandX().center().row();
		table.add(info).expandX().center().row();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(game.mms);
	}
}
