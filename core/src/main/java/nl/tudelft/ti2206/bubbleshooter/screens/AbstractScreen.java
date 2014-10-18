package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngine;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SkinID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Abstraction of the various screens.
 * @author group-15
 *
 */
public class AbstractScreen extends ScreenAdapter {
	protected BubbleShooter game;
	protected Stage stage;
	protected Table table;
	protected TextButtonStyle buttonStyle;
	protected LabelStyle labelStyle;
	protected TextFieldStyle textStyle;
	
	/**
	 * Sets up the buttons to be displayed.
	 * @param game the current game session
	 */
	public AbstractScreen(BubbleShooter game) {
		this.game = game;
		this.stage = new Stage(new ScreenViewport());
		this.table = new Table();
		this.buttonStyle = new TextButtonStyle(	Assets.getAssets().get(SkinID.BUTTON),
												Assets.getAssets().get(SkinID.BUTTON),
												Assets.getAssets().get(SkinID.BUTTON),
												game.font);
		this.labelStyle = new LabelStyle(game.font, Color.WHITE);
		this.textStyle = new TextFieldStyle(game.font, Color.WHITE, null, null, null);
		table.debug();
		
		TextureRegion region = new TextureRegion(Assets.getAssets().get(TextureID.BACKGROUND));
		TextureRegionDrawable drawable = new TextureRegionDrawable(region);
		table.background(drawable);
		table.setFillParent(true);
		stage.addActor(table);
	}
	
	/**
	 * Show is being called upon entering the screen.
	 */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		SoundEngine.getSoundEngine().play(MusicID.MENU);
	}
	
	/**
	 * Play some main menu background music.
	 * This music will be played in the main menu screen and options screen
	 */
	@Override
	public void hide() {
		SoundEngine.getSoundEngine().pause();
	}
	
	/**
	 * Hide is being called when the main menu screen is not the current screen.
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	/**
	 * Render the screen.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	    stage.act(delta);
	    stage.draw();
	}
	
	/**
	 * Dispose of the stage.
	 */
	@Override
	public void dispose() {
		stage.dispose();
	}
	
	public ButtonStyle getButtonStyle() {
		return this.buttonStyle;
	}
}
