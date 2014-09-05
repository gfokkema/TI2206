package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.core.Launch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * The actual menu screen, which shows the various buttons to choose from.
 * Such buttons include:
 * - Starting game
 * - Options menu
 * - Viewing high scores
 * @author group-15
 *
 */
public class MainMenuScreen extends ScreenAdapter {
	
	/**
	 * Initialize the skin, stage and the current game session.
	 */
	private Skin skin;
	private Stage stage;
	Launch game;
	
	/**
	 * The menu screen calls the {@link #create} method estabilishing the stage.
	 * @param game the current game session
	 */
	public MainMenuScreen( Launch game){
		this.game = game;
		create();
	}
	
	/**
	 * Creating buttons and handling them.
	 */
	public void create(){
		skin = new Skin();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Pixmap pix = new Pixmap(100,100,Format.RGBA8888);
		pix.setColor(Color.CYAN);
		pix.fill();
		skin.add("white", new Texture(pix));
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.BLUE);
		
		skin.add("default", game.font);
		textButtonStyle.font = skin.getFont("default");
		
		skin.add("default", textButtonStyle);
		final TextButton textbutton = new TextButton("Test", textButtonStyle);
		textbutton.setPosition(1280/2, 720/2);
		stage.addActor(textbutton);
		
		// handling event changes
		ChangeListener changed = new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				textbutton.setText("Intializing game...");
				game.setScreen(new BubbleShooterScreen(game));
			}
			
		};
		
		textbutton.addListener(changed);
	}
	
	/**
	 * Render the stage.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

}
