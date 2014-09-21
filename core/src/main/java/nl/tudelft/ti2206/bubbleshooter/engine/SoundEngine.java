package nl.tudelft.ti2206.bubbleshooter.engine;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;

import com.badlogic.gdx.audio.Music;

/**
 * This class acts as a {@link SoundEngine} for the whole game.
 * When something in the game wants to play a sound,
 * or change settings regarding the sound, this class will be used.
 *
 */
public class SoundEngine {
	Assets assets;
	Music m;
	
	private float bgmvolume = 0.5f;
	private float sfxvolume = 0.5f;
	
	/**
	 * Construct a {@link SoundEngine} using the given {@link Assets} manager.
	 * @param assets	{@link Assets} manager containing all sounds
	 */
	public SoundEngine(Assets assets) {
		this.assets = assets;
	}
	
	/**
	 * Return the current background music volume.
	 * @return	current BGM volume (0 - 1)
	 */
	public float getBGMVolume() {
		return this.bgmvolume;
	}
	
	/**
	 * Set the current background music volume.
	 * @param f	volume from 0 - 1
	 */
	public void setBGMVolume(float f) {
		f = Math.max(0, Math.min(1, f));
		
		this.bgmvolume = f;
		if (m != null) m.setVolume(f);
	}
	
	/**
	 * Return the current sound effects volume.
	 * @return	current SFX volume (0 - 1)
	 */
	public float getSFXVolume() {
		return this.sfxvolume;
	}
	
	/**
	 * Set the current sound effects volume.
	 * @param f	volume from 0 - 1
	 */
	public void setSFXVolume(float f) {
		f = Math.max(0, Math.min(1, f));
		
		this.sfxvolume = f;
	}
	
	/**
	 * This method will start playback of the background music identified by {@link MusicID}. 
	 * @param id	{@link MusicID} that refers to music in {@link Assets}
	 */
	public void play(MusicID id) {
		if (m != null && !m.equals(assets.get(id))) m.stop();
		m = assets.get(id);
		m.setLooping(true);
		m.setVolume(bgmvolume);
		m.play();
	}
	
	/**
	 * This method will start playback of the sound effect identified by {@link SoundID}. 
	 * @param id	{@link SoundID} that refers to a sound effect in {@link Assets}
	 */
	public void play(SoundID id) {
		assets.get(id).play(sfxvolume);
	}
	
	/**
	 * This method will pause playback of the background music.
	 */
	public void pause() {
		m.pause();
	}
}
