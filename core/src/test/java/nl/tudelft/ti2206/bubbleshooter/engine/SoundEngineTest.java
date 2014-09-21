package nl.tudelft.ti2206.bubbleshooter.engine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Tests the {@link SoundEngine} class, which will take care of all {@link Sound} and {@link Music} playback.
 */
@RunWith(MockitoJUnitRunner.class)
public class SoundEngineTest {
	@Mock Assets assets;
	@Mock Music music_game, music_menu;
	@Mock Sound sound_bubble, sound_button;

	private SoundEngine engine;

	/**
	 * Set up our mocks and our {@link SoundEngine}.
	 */
	@Before
	public void setUp() {
		Mockito.when(assets.get(MusicID.GAME)).thenReturn(music_game);
		Mockito.when(assets.get(MusicID.MENU)).thenReturn(music_menu);
		Mockito.when(assets.get(SoundID.BUBBLE)).thenReturn(sound_bubble);
		Mockito.when(assets.get(SoundID.BUTTON)).thenReturn(sound_button);
		engine = new SoundEngine(assets);
	}

	/**
	 * Tests default values after initialization of the {@link SoundEngine}.
	 */
	@Test
	public void testSoundEngine() {
		assertEquals(.5f, engine.getBGMVolume(), .001);
		assertEquals(.5f, engine.getSFXVolume(), .001);
	}

	/**
	 * Tests changing the {@link Music} volume.
	 */
	@Test
	public void testBGMVolume() {
		engine.setBGMVolume(-0.1f);
		assertEquals(0.0f, engine.getBGMVolume(), .001);

		engine.setBGMVolume(0.0f);
		assertEquals(0.0f, engine.getBGMVolume(), .001);

		engine.setBGMVolume(0.6f);
		assertEquals(0.6f, engine.getBGMVolume(), .001);

		engine.setBGMVolume(1.0f);
		assertEquals(1.0f, engine.getBGMVolume(), .001);

		engine.setBGMVolume(1.1f);
		assertEquals(1.0f, engine.getBGMVolume(), .001);
	}

	/**
	 * Tests changing the {@link Sound} effects volume.
	 */
	@Test
	public void testSFXVolume() {
		engine.setSFXVolume(-0.1f);
		assertEquals(0.0f, engine.getSFXVolume(), .001);

		engine.setSFXVolume(0.0f);
		assertEquals(0.0f, engine.getSFXVolume(), .001);

		engine.setSFXVolume(0.6f);
		assertEquals(0.6f, engine.getSFXVolume(), .001);

		engine.setSFXVolume(1.0f);
		assertEquals(1.0f, engine.getSFXVolume(), .001);

		engine.setSFXVolume(1.1f);
		assertEquals(1.0f, engine.getSFXVolume(), .001);
	}

	/**
	 * Tests playback of {@link Music} files.
	 */
	@Test
	public void testPlayMusic() {
		engine.setBGMVolume(.4f);
		engine.play(MusicID.GAME);
		Mockito.verify(music_game).setVolume(.4f);
		Mockito.verify(music_game).play();
	}

	/**
	 * Tests changing volume during playback of {@link Music} files.
	 */
	@Test
	public void testPlayMusicVolume() {
		engine.play(MusicID.GAME);
		engine.setBGMVolume(.6f);
		Mockito.verify(music_game).setVolume(.6f);
	}

	/**
	 * Tests switching {@link Music} file during playback.
	 */
	@Test
	public void testPlayMusicSwitch() {
		engine.setBGMVolume(.6f);
		engine.play(MusicID.GAME);
		engine.play(MusicID.MENU);
		Mockito.verify(music_game).setVolume(.6f);
		Mockito.verify(music_game).stop();
		Mockito.verify(music_menu).setVolume(.6f);
		Mockito.verify(music_menu).play();
	}

	/**
	 * Tests switching {@link Music} file to the same file during playback.
	 * The music file should not start over again in this scenario.
	 */
	@Test
	public void testPlayMusicRepeat() {
		engine.play(MusicID.MENU);
		engine.play(MusicID.MENU);
		Mockito.verify(music_menu, times(2)).play();
		Mockito.verify(music_menu, never()).stop();
	}

	/**
	 * Tests pausing playback of a {@link Music} file.
	 */
	@Test
	public void testPauseMusic() {
		engine.play(MusicID.GAME);
		engine.pause();
		Mockito.verify(music_game).pause();
	}

	/**
	 * Tests playback of {@link Sound} effects.
	 */
	@Test
	public void testPlaySFX() {
		engine.setSFXVolume(.4f);
		engine.play(SoundID.BUBBLE);
		Mockito.verify(sound_bubble).play(.4f);

		engine.setSFXVolume(2.0f);
		engine.play(SoundID.BUBBLE);
		Mockito.verify(sound_bubble).play(1.0f);

		engine.play(SoundID.BUTTON);
		Mockito.verify(sound_button).play(1.0f);
	}
}
