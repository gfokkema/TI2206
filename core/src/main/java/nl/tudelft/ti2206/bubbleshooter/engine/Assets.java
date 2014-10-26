package nl.tudelft.ti2206.bubbleshooter.engine;

import java.util.EnumMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * This class manages all external assets that Bubbleshooter uses.
 */
public class Assets {
	/**
	 * This enum contains all types of {@link Skin} assets.
	 */
	public enum SkinID {
		BUTTON
	}
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
		MENUBACKGROUND,
		GAMEBACKGROUND,
		BORDER,
		BUBBLE,
		STONEBUBBLE,
		MICHAELBAYBUBBLE,
		BOMBUBBLE,
		BUTTON,
		CANNON
	}
	
	/**
	 * Singleton for assets.
	 */
	private static Assets assets = null;
	
	AssetManager loader;
	EnumMap<MusicID, String> music;
	EnumMap<SoundID, String> sounds;
	EnumMap<TextureID, String> textures;
	EnumMap<SkinID, String> skins;
	Skin skin;
	
	/**
	 * Initializes the {@link Assets} class that holds all external assets for Bubbleshooter.
	 * @param loader	the {@link AssetManager} to use
	 */
	private Assets(AssetManager loader, Skin skin) {
		this.loader = loader;
		this.skin = skin;
		
		music = new EnumMap<MusicID, String>(MusicID.class);
		sounds = new EnumMap<SoundID, String>(SoundID.class);
		textures = new EnumMap<TextureID, String>(TextureID.class);
		skins = new EnumMap<SkinID, String>(SkinID.class);
	}

	/**
	 * Initializes the {@link Assets} class that holds all external assets for Bubbleshooter.
	 */
	private Assets() {
		this(new AssetManager(), new Skin());
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
	 * Set the skin we should use.
	 * This can be used for changing menu themes.
	 * @param skin	the {@link Skin} to use
	 */
	public void setSkin(Skin skin) {
		this.skin = skin;
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
	 * Retrieve the {@link Drawable} that is linked to {@link SkinID}.
	 * @param id	{@link SkinID} representing the {@link Drawable} for the UI skin
	 * @return		{@link Drawable} linked to {@link SkinID}
	 */
	public Drawable get(SkinID id) {
		Drawable d = skin.getDrawable(skins.get(id));
		d.setMinHeight(50);
		d.setMinWidth(200);
		return d;
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
	 * @param settings	the {@link Settings} to use
	 * @param id		{@link MusicID} to link this file with
	 * @param handle	handle for the {@link Music} file
	 */
	public void load(Settings settings, MusicID id, String handle) {
		music.put(id, handle);
		loader.load(handle, Music.class);
	}
	
	/**
	 * Loads a file and links it to a {@link SkinID}.
	 * @param settings	the {@link Settings} to use
	 * @param id		{@link SkinID} to link this file with
	 * @param handle	handle for the {@link Drawable} file
	 */
	public void load(Settings settings, SkinID id, String handle) {
		String path = settings.getCurrentPath() + handle;
		skins.put(id, path);
		loader.load(path, Texture.class);
	}
	
	/**
	 * Loads a file and links it to a {@link SoundID}.
	 * @param settings	the {@link Settings} to use
	 * @param id		{@link SoundID} to link this file with
	 * @param handle	handle for the {@link Sound} file
	 */
	public void load(Settings settings, SoundID id, String handle) {
		sounds.put(id, handle);
		loader.load(handle, Sound.class);
	}
	
	/**
	 * Loads a file and links it to a {@link TextureID}.
	 * @param settings	the {@link Settings} to use
	 * @param id		{@link TextureID} to link this file with
	 * @param handle	handle for the {@link Texture} file
	 */
	public void load(Settings settings, TextureID id, String handle) {
		String path = settings.getCurrentPath() + handle;
		textures.put(id, path);
		loader.load(path, Texture.class);
	}
	
	/**
	 * This method blocks until loading is finished.
	 */
	public void finish() {
		loader.finishLoading();
		skins.values().forEach((String handle) -> {
			skin.add(handle, loader.get(handle), Texture.class);
		});
	}
	
	/**
	 * Unload all the textures.
	 */
	public void unloadTextures() {
		textures.values().forEach((String handle) -> {
			if(assets.loader.isLoaded(handle)) {
				assets.loader.unload(handle);
				textures.remove(handle);
			}
		});
	}
	
	/**
	 * Load all the textures using the specified settings.
	 * @param settings	the {@link Settings} to use
	 */
	public void loadTextures(Settings settings) {
		assets.load(settings, TextureID.MENUBACKGROUND, "MainMenuDoomBG.png");
		assets.load(settings, TextureID.GAMEBACKGROUND, "BG_back.png");
		assets.load(settings, TextureID.BORDER, "MPborder.png");
		assets.load(settings, TextureID.BUBBLE, "Bubble-Blue.png");
		assets.load(settings, TextureID.CANNON, "cannon.png");
		assets.load(settings, TextureID.STONEBUBBLE, "StoneBubble.png");
		assets.load(settings, TextureID.BOMBUBBLE, "BomBubble.png");
		assets.load(settings, TextureID.MICHAELBAYBUBBLE, "Nuke.png");
		assets.load(settings, SkinID.BUTTON, "brown_button.png");
		assets.finish();
	
		assets.get(TextureID.BUBBLE).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		assets.get(TextureID.STONEBUBBLE).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		assets.get(TextureID.BOMBUBBLE).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		assets.get(TextureID.MICHAELBAYBUBBLE).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		assets.get(TextureID.CANNON).setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	/**
	 * Load all the textures using global settings.
	 */
	public void loadTextures() {
		loadTextures(Settings.getSettings());
	}
}
