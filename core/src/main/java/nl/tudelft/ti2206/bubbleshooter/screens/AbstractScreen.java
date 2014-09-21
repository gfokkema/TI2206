package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class AbstractScreen extends ScreenAdapter {
	protected BubbleShooter game;
	protected Stage stage;
	protected Table table;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public AbstractScreen(BubbleShooter game) {
		this.game = game;
		this.stage = new Stage(new ScreenViewport());
		this.table = new Table();
		
		TextureRegion region = new TextureRegion(game.assets.get(TextureID.BACKGROUND));
		TextureRegionDrawable drawable = new TextureRegionDrawable(region);
		table.background(drawable);
		table.setFillParent(true);
		stage.addActor(table);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		game.engine.play(MusicID.MENU);
	}
	
	/**
	 * Play some main menu background music.
	 * This music will be played in the main menu screen and options screen
	 */
	@Override
	public void hide() {
		game.engine.pause();
	}
	
	/**
	 * Hide is being called when the main menu screen is not the current screen.
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	    stage.act(delta);
	    stage.draw();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
