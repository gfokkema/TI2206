package nl.tudelft.ti2206.bubbleshooter.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Bubbleshooter implements ApplicationListener {
	Texture bubble_blue;
	Texture bubble_orange;
	Texture bubble_green;
	Texture bubble_pink;
	SpriteBatch batch;
	float elapsed;

	@Override
	public void create () {
		// some bubbles
		bubble_blue = new Texture(Gdx.files.internal("Bubble-Blue.png"));
		bubble_orange = new Texture(Gdx.files.internal("Bubble-Orange.png"));
		bubble_green = new Texture(Gdx.files.internal("Bubble-green.png"));
		bubble_pink = new Texture(Gdx.files.internal("Bubble-pink.png"));
		batch = new SpriteBatch();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bubble_blue, 300+100*(float)Math.cos(elapsed), 300+25*(float)Math.sin(elapsed));
		batch.draw(bubble_orange, 100+100*(float)Math.cos(elapsed), 100+25*(float)Math.sin(elapsed));
		batch.draw(bubble_green, 200+100*(float)Math.cos(elapsed), 200+25*(float)Math.sin(elapsed));
		batch.draw(bubble_pink, 400+100*(float)Math.cos(elapsed), 400+25*(float)Math.sin(elapsed));
		batch.end();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
