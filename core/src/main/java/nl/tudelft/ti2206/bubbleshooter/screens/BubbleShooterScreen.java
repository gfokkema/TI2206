package nl.tudelft.ti2206.bubbleshooter.screens;

import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.artifacts.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Launch;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;

public class BubbleShooterScreen extends ScreenAdapter {
	
	Launch game;
	Bubble blue;
	Cannon cannon;
	float elapsed;

	
	public BubbleShooterScreen(Launch game) {
		this.game = game;
		create();
	}
	
	public void create () {
		// some bubbles, positioning
		blue = new Bubble("Bubble-Blue.png");
		blue.setPosition(400, 400);
		cannon = new Cannon(Gdx.graphics.getWidth()/2,100,"testCannon.png");
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
		
		int blue_width = blue.getTexture().getWidth();
		int blue_height = blue.getTexture().getHeight();
		Rectangle bRectangle = blue.getRectangle();
		
		// batch begins
		game.batch.begin();
		
		// bubble stays within screen bounds
		if(blue.getX() < 0) bRectangle.x = 0;
		if(blue.getX() > Gdx.graphics.getWidth() - blue_width) bRectangle.x = Gdx.graphics.getWidth() - blue_width;
		if(blue.getY() < 0) bRectangle.y = 0;
		if(blue.getY() > Gdx.graphics.getHeight() - blue_height) bRectangle.y = Gdx.graphics.getHeight() - blue_height;
		
		// draw the cannon (+ temp a bubble...)
		cannon.draw(game.batch);
		blue.draw(game.batch);
		
		// end batch
		game.batch.end();
		
		// testing...
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) dispose();
	}

	@Override
	public void dispose () {
		// testing
		System.exit(0);
	}

}
