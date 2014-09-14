package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.*;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Tests the {@link Assets} class, which manages all external assets used by Bubbleshooter.
 */
@RunWith(MockitoJUnitRunner.class)
public class AssetsTest {
	@Mock AssetManager loader;
	@Mock Music music;
	@Mock Sound sound;
	@Mock Texture texture;
	
	private Assets assets;
	
	/**
	 * Set up our mocks and our asset loader.
	 */
	@Before
	public void setUp() {
		assets = new Assets(loader);
		Mockito.when(loader.get("test.ogg")).thenReturn(music);
		Mockito.when(loader.get("test.wav")).thenReturn(sound);
		Mockito.when(loader.get("test.png")).thenReturn(texture);
	}
	
	/**
	 * Test loading and retrieving a {@link Music} file.
	 */
	@Test
	public void testLoadMusic() {
		assets.load(MusicID.GAME, "test.ogg");
		Mockito.verify(loader).load("test.ogg", Music.class);
		
		assertEquals(music, assets.get(MusicID.GAME));
	}
	
	/**
	 * Test loading and retrieving a {@link Sound} file.
	 */
	@Test
	public void testLoadSound() {
		assets.load(SoundID.BUBBLE, "test.wav");
		Mockito.verify(loader).load("test.wav", Sound.class);
		
		assertEquals(sound, assets.get(SoundID.BUBBLE));
	}
	
	/**
	 * Test loading and retrieving a {@link Texture} file.
	 */
	@Test
	public void testLoadTexture() {
		assets.load(TextureID.BUBBLE, "test.png");
		Mockito.verify(loader).load("test.png", Texture.class);
		
		assertEquals(texture, assets.get(TextureID.BUBBLE));
	}
	
	/**
	 * Test blocking the thread until loading finishes.
	 */
	@Test
	public void testFinish() {
		assets.load(MusicID.GAME, "test.ogg");
		assets.load(SoundID.BUBBLE, "test.wav");
		assets.load(TextureID.BUBBLE, "test.png");
		assets.finish();
		Mockito.verify(loader).finishLoading();
	}
}
