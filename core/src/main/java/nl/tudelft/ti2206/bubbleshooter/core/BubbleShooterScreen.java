package nl.tudelft.ti2206.bubbleshooter.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

public class BubbleShooterScreen implements Screen {
	
	Launch game;
	private Rectangle bubble_blue;
	Texture bubble_blue_img;
	Texture bubble_orange;
	Texture bubble_green;
	Texture bubble_pink;
	float elapsed;

	public BubbleShooterScreen(Launch game) {
		this.game = game;
		create();
	}
	
	public void create () {
		// some bubbles, positioning
		bubble_blue_img = new Texture(Gdx.files.internal("Bubble-Blue.png"));
		bubble_blue = new Rectangle();
		bubble_blue.x = 400;
		bubble_blue.y = 400;
//		bubble_orange = new Texture(Gdx.files.internal("Bubble-Orange.png"));
//		bubble_green = new Texture(Gdx.files.internal("Bubble-green.png"));
//		bubble_pink = new Texture(Gdx.files.internal("Bubble-pink.png"));
	}

	@Override
	public void resize (int width, int height) {
	}
	
	@Override
	public void render (float delta) {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(bubble_blue_img, bubble_blue.x, bubble_blue.y);
//		batch.draw(bubble_orange, 100+100*(float)Math.cos(elapsed), 100+25*(float)Math.sin(elapsed));
//		batch.draw(bubble_green, 200+100*(float)Math.cos(elapsed), 200+25*(float)Math.sin(elapsed));
//		batch.draw(bubble_pink, 400+100*(float)Math.cos(elapsed), 400+25*(float)Math.sin(elapsed));
		game.batch.end();
		
		// sample user input
		if(Gdx.input.isKeyPressed(Keys.LEFT)) bubble_blue.x -= 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) bubble_blue.x += 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.DOWN)) bubble_blue.y -= 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.UP)) bubble_blue.y += 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) dispose();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
		// testing
		System.exit(0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
