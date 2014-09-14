package nl.tudelft.ti2206.bubbleshooter.audio;

import java.util.EnumMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public enum MusicID {
		GAME,
		MENU
	}
	public enum SoundID {
		BUBBLE,
		BUTTON,
		CANNON
	}
	public enum TextureID {
		BACKGROUND,
		BUBBLE,
		BUTTON,
		CANNON
	}
	
	AssetManager loader;
	EnumMap<MusicID, String> music;
	EnumMap<SoundID, String> sounds;
	EnumMap<TextureID, String> textures;
	
	public Assets() {
		this.loader = new AssetManager();
		music = new EnumMap<MusicID, String>(MusicID.class);
		sounds = new EnumMap<SoundID, String>(SoundID.class);
		textures = new EnumMap<TextureID, String>(TextureID.class);
	}
	
	public Music get(MusicID id) {
		return loader.get(music.get(id));
	}
	
	public Sound get(SoundID id) {
		return loader.get(sounds.get(id));
	}
	
	public Texture get(TextureID id) {
		return loader.get(textures.get(id));
	}
	
	public void load(MusicID id, String handle) {
		music.put(id, handle);
		loader.load(handle, Music.class);
	}
	
	public void load(SoundID id, String handle) {
		sounds.put(id, handle);
		loader.load(handle, Sound.class);
	}
	
	public void load(TextureID id, String handle) {
		textures.put(id, handle);
		loader.load(handle, Texture.class);
	}
	
	public void finish() {
		loader.finishLoading();
	}
}
