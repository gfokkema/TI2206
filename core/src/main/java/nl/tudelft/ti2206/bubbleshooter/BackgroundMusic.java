package nl.tudelft.ti2206.bubbleshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Responsible for managing the background music.
 * @author group-15
 *
 */
public class BackgroundMusic {

	/**
	 * Initialize background music.
	 */
	Music BGM;
	
	/**
	 * Create a new instance of background music, provided with the file name.
	 * Supports .ogg, .mp3, .wave.
	 * @param name the file name of the BGM
	 */
	public BackgroundMusic(String name) {
		BGM = Gdx.audio.newMusic(Gdx.files.internal(name));
		BGM.setLooping(true);
		setVolume(0.5f);
	}
	
	/**
	 * Set the background music volume.
	 * Minimum is 0, maximum is 1 - [0,1] interval.
	 */
	public void setVolume(float volume) {
		BGM.setVolume(volume);
	}
	
	/**
	 * Get the background music.
	 * @return BGM the background music
	 */
	public Music getBGM() {
		return BGM;
	}
	
	/**
	 * Get the background music volume.
	 */
	public float getVolume() {
		return BGM.getVolume();
	}
}
