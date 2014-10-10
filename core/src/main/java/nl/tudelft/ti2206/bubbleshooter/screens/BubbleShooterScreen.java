package nl.tudelft.ti2206.bubbleshooter.screens;

import java.time.Duration;
import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.BubbleShooter;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;
import nl.tudelft.ti2206.bubbleshooter.mode.BSMode;
import nl.tudelft.ti2206.bubbleshooter.util.EndingObserver;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * The bubble shooter screen is the screen where the actual game is being played.
 * @author group-15
 *
 */
public class BubbleShooterScreen extends ScreenAdapter implements StatsObserver, EndingObserver {
	BubbleShooter game;
	BSMode game_mode;
	private Stage stage;
	private Table table;
	private Label timerField;
	private Label scoreField;

	/**
	 * Constructor of BubbleShooterScreen
	 * Creates a cannon, board and game.
	 * @param game	the parent {@link BubbleShooter} instance
	 */
	public BubbleShooterScreen(BubbleShooter game, BSMode game_mode) {
		this.stage = new Stage(new ScreenViewport());
		this.table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		LabelStyle style = new LabelStyle(game.font, Color.WHITE);
		timerField = new Label("Time left:", style);
		scoreField = new Label("Score:", style);
		table.add(timerField).expandX().expandY().top().left();
		table.add(scoreField).expandX().expandY().top().right();

		this.game = game;
		this.game_mode = game_mode;
		game_mode.addStatsObserver(this);
		game_mode.addEndingObserver(this);
	}

	/**
	 * Render everything on the bubble shooter screen
	 */
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		game_mode.update(delta);
		game.batch.begin();
		// Draw all game sprites.
		game_mode.getDrawables().forEach((Vector2 o, Collection<BSDrawable> c) -> {
			c.forEach((BSDrawable d) -> draw(o, d));
		});
		game.batch.end();
		stage.act();
		stage.draw();
	}
	
	/**
	 * Show is being called upon entering the screen.
	 */
	@Override
	public void show() {
		 game.engine.play(MusicID.GAME);
	}
	
	/**
	 * Hide is being called when exiting the screen.
	 */
	@Override
	public void hide() {
		// game.engine.pause();
	}
	
	/**
	 * Draw the various elements.
	 * @param offset an offset to which something is drawn.
	 * @param drawable the {@link BSDrawable}.
	 */
	public void draw(Vector2 offset, BSDrawable drawable) {
		Vector2 position = drawable.getPosition();
		Vector2 origin = drawable.getOrigin();
		
		game.batch.setColor(drawable.getColor());
		game.batch.draw(new TextureRegion(game.assets.get(drawable.getTexture())),
						offset.x + position.x, offset.y + position.y,
						origin.x, origin.y,
						drawable.getWidth(), drawable.getHeight(),
						1, 1,
						drawable.getRotation());
	}

	/**
	 * Draw the timer onto the playing-field.
	 */
	@Override
	public void drawTimer(Duration duration) {
		String timeString = "Time left: " + duration.toMinutes() + ":" + (duration.getSeconds()%60);
		timerField.setText(timeString);
	}

	/**
	 * Draw the score onto the playing-field.
	 */
	@Override
	public void drawScore(int score) {
		scoreField.setText("Score:" + score);
	}

	@Override
	public void lost() {
		game.setScreen(new GameEndedScreen(game, "YOU LOST!", game_mode.getScore()));
	}

	@Override
	public void won() {
		if (game_mode.hasNext()) game_mode.next();
		else game.setScreen(new GameEndedScreen(game, "YOU WON!", game_mode.getScore()));
	}

	@Override
	public void wonBoard() {
	}
}
