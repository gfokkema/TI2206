package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.EnumMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class manages all external assets that Bubbleshooter uses.
 */
public class Assets {
	/**
	 * This enum contains all types of {@link Music} assets.
	 */
	public enum MusicID {
		GAME,
		MENU
	}
	/**
	 * This enum contains all types of {@link Sound} assets.
	 */
	public enum SoundID {
		BUBBLE,
		BUTTON,
		CANNON
	}
	/**
	 * This enum contains all types of {@link Texture} assets.
	 */
	public enum TextureID {
		BACKGROUND,
		BORDER,
		BUBBLE,
		BUTTON,
		CANNON
	}
	
	private static Assets assets = null;
	
	AssetManager loader;
	EnumMap<MusicID, String> music;
	EnumMap<SoundID, String> sounds;
	EnumMap<TextureID, String> textures;
	
	/**
	 * Initializes the {@link Assets} class that holds all external assets for Bubbleshooter.
	 * @param loader	the {@link AssetManager} to use
	 */
	private Assets(AssetManager loader) {
		this.loader = loader;
		music = new EnumMap<MusicID, String>(MusicID.class);
		sounds = new EnumMap<SoundID, String>(SoundID.class);
		textures = new EnumMap<TextureID, String>(TextureID.class);
	}

	/**
	 * Initializes the {@link Assets} class that holds all external assets for Bubbleshooter.
	 */
	private Assets() {
		this(new AssetManager());
	}
	
	/**
	 * Singleton pattern for a global asset manager.
	 * @return	the unique instance of {@link Assets}
	 */
	public synchronized static Assets getAssets() {
		if (assets == null) assets = new Assets();
		
		return assets;
	}
	
	/**
	 * Set the assetmanager we should use.
	 * This can be used for changing themes between levels.
	 * @param loader	the {@link AssetManager} to use
	 */
	public void setAssetManager(AssetManager loader) {
		this.loader = loader;
	}
	
	/**
	 * Retrieve the {@link Music} that is linked to {@link MusicID}.
	 * @param id	{@link MusicID} representing the {@link Music} type
	 * @return		{@link Music} linked to {@link MusicID}
	 */
	public Music get(MusicID id) {
		return loader.get(music.get(id));
	}
	
	/**
	 * Retrieve the {@link Sound} that is linked to {@link SoundID}.
	 * @param id	{@link SoundID} representing the {@link Sound} type
	 * @return		{@link Sound} linked to {@link SoundID}
	 */
	public Sound get(SoundID id) {
		return loader.get(sounds.get(id));
	}
	
	/**
	 * Retrieve the {@link Texture} that is linked to {@link TextureID}.
	 * @param id	{@link TextureID} representing the {@link Texture} type
	 * @return		{@link Texture} linked to {@link TextureID}
	 */
	public Texture get(TextureID id) {
		return loader.get(textures.get(id));
	}

	/**
	 * Loads a file and links it to a {@link MusicID}.
	 * @param id		{@link MusicID} to link this file with
	 * @param handle	handle for the {@link Music} file
	 */
	public void load(MusicID id, String handle) {
		music.put(id, handle);
		loader.load(handle, Music.class);
	}
	
	/**
	 * Loads a file and links it to a {@link SoundID}.
	 * @param id		{@link SoundID} to link this file with
	 * @param handle	handle for the {@link Sound} file
	 */
	public void load(SoundID id, String handle) {
		sounds.put(id, handle);
		loader.load(handle, Sound.class);
	}
	
	/**
	 * Loads a file and links it to a {@link TextureID}.
	 * @param id		{@link TextureID} to link this file with
	 * @param handle	handle for the {@link Texture} file
	 */
	public void load(TextureID id, String handle) {
		textures.put(id, handle);
		loader.load(handle, Texture.class);
	}
	
	/**
	 * This method blocks until loading is finished.
	 */
	public void finish() {
		loader.finishLoading();
	}
}
