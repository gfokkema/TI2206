package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SkinID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.eq;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Tests the {@link Assets} class, which manages all external assets used by Bubbleshooter.
 */
@RunWith(MockitoJUnitRunner.class)
public class AssetsTest {
	@Mock AssetManager loader;
	@Mock Drawable drawable;
	@Mock Music music;
	@Mock Settings settings;
	@Mock Skin skin;
	@Mock Sound sound;
	@Mock Texture texture;

	private Assets assets;

	/**
	 * Set up our mocks and our asset loader.
	 */
	@Before
	public void setUp() {
		assets = Assets.getAssets();
		assets.setAssetManager(loader);
		assets.setSkin(skin);
		Mockito.when(loader.get("test.drw")).thenReturn(texture);
		Mockito.when(loader.get("test.ogg")).thenReturn(music);
		Mockito.when(loader.get("test.wav")).thenReturn(sound);
		Mockito.when(loader.get("test.png")).thenReturn(texture);
		Mockito.when(settings.getCurrentPath()).thenReturn("");
		Mockito.when(skin.getDrawable("test.drw")).thenReturn(drawable);
	}
	
	/**
	 * Test loading and retrieving a {@link Drawable}.
	 */
	@Test
	public void testLoadDrawable() {
		assets.load(settings, SkinID.BUTTON, "test.drw");
		Mockito.verify(loader).load("test.drw", Texture.class);
		
		assets.finish();
		Mockito.verify(skin).add("test.drw", texture, Texture.class);
		
		assertEquals(drawable, assets.get(SkinID.BUTTON));
	}

	/**
	 * Test loading and retrieving a {@link Music} file.
	 */
	@Test
	public void testLoadMusic() {
		assets.load(settings, MusicID.GAME, "test.ogg");
		Mockito.verify(loader).load("test.ogg", Music.class);

		assertEquals(music, assets.get(MusicID.GAME));
	}

	/**
	 * Test loading and retrieving a {@link Sound} file.
	 */
	@Test
	public void testLoadSound() {
		assets.load(settings, SoundID.BUBBLE, "test.wav");
		Mockito.verify(loader).load("test.wav", Sound.class);

		assertEquals(sound, assets.get(SoundID.BUBBLE));
	}

	/**
	 * Test loading and retrieving a {@link Texture} file.
	 */
	@Test
	public void testLoadTexture() {
		assets.load(settings, TextureID.BUBBLE, "test.png");
		Mockito.verify(loader).load("test.png", Texture.class);

		assertEquals(texture, assets.get(TextureID.BUBBLE));
	}

	/**
	 * Test blocking the thread until loading finishes.
	 */
	@Test
	public void testFinish() {
		assets.load(settings, MusicID.GAME, "test.ogg");
		assets.load(settings, SoundID.BUBBLE, "test.wav");
		assets.load(settings, TextureID.BUBBLE, "test.png");
		assets.finish();
		Mockito.verify(loader).finishLoading();
	}
	
	/**
	 * Test unloading all assets.
	 */
	@Test
	public void testUnloadTextures() {
		Mockito.when(loader.isLoaded("bubble.png")).thenReturn(true);
		Mockito.when(loader.isLoaded("button.png")).thenReturn(true);
		Mockito.when(loader.isLoaded("test.png")).thenReturn(false);
		
		assets.load(settings, TextureID.BUBBLE, "bubble.png");
		assets.load(settings, TextureID.BUTTON, "button.png");
		assets.load(settings, TextureID.BORDER, "test.png");
		assets.unloadTextures();
		Mockito.verify(loader, times(2)).unload(any());
	}
	
	/**
	 * Test loading all assets.
	 */
	@Test
	public void testLoadTextures() {
		Mockito.when(assets.get(any(TextureID.class))).thenReturn(texture);
		
		assets.loadTextures(settings);
		Mockito.verify(loader, times(9)).load(any(), eq(Texture.class));
		Mockito.verify(loader, times(1)).finishLoading();
		Mockito.verify(texture, times(5)).setFilter(any(), any());
	}
}
