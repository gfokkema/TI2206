package nl.tudelft.ti2206.bubbleshooter.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameUI {
	private Stage stage;

	public GameUI(Stage stage) {
		this.stage = stage;
		//stage.addActor(table);
	}

	public void draw() {
		stage.act();
		stage.draw();
	}
}
