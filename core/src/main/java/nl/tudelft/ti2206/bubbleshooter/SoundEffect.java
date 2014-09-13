package nl.tudelft.ti2206.bubbleshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Responsible for the sound effects.
 * @author group-15
 *
 */
public class SoundEffect {
	
	/**
	 * Initialize SoundFX, volume and id.
	 */
	Sound SFX;
	float SFXvol;
	long id;
	
	/**
	 * Create a new instance of SoundEffect, provided with the file name
	 * Supports .ogg, .mp3, .wave.
	 * @param name the file name of the SFX.
	 */
	public SoundEffect(String name) {
		SFX = Gdx.audio.newSound(Gdx.files.internal(name));
		SFXvol = 0;
	}
	
	/**
	 * Set the background music volume.
	 * Minimum is 0, maximum is 1 - [0,1] interval.
	 */
	public void setVolume(float volume) {
		SFXvol = volume;
		SFX.setVolume(id, SFXvol);
	}
	
	/**
	 * Get the SFX.
	 * @return
	 */
	public Sound getSFX() {
		return SFX;
	}
	
	/**
	 * Get the SFX volume.
	 */
	public float getVolume() {
		return SFXvol;
	}
	
	/**
	 * Initial play of the SFX.
	 */
	public void play() {
		id = SFX.play(SFXvol);
	}
}
