package nl.tudelft.ti2206.bubbleshooter.engine;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.MusicID;
import nl.tudelft.ti2206.bubbleshooter.engine.Assets.SoundID;

import com.badlogic.gdx.audio.Music;

public class SoundEngine {
	Assets assets;
	Music m;
	
	private float bgmvolume = 0.5f;
	private float sfxvolume = 0.5f;
	
	public SoundEngine(Assets assets) {
		this.assets = assets;
	}
	
	public float getBGMVolume() {
		return this.bgmvolume;
	}
	
	public void setBGMVolume(float f) {
		f = Math.max(0, Math.min(1, f));
		
		this.bgmvolume = f;
		if (m != null) m.setVolume(f);
	}
	
	public float getSFXVolume() {
		return this.sfxvolume;
	}
	
	public void setSFXVolume(float f) {
		f = Math.max(0, Math.min(1, f));
		
		this.sfxvolume = f;
	}
	
	public void play(MusicID id) {
		if (m != null && !m.equals(assets.get(id))) m.stop();
		m = assets.get(id);
		m.setLooping(true);
		m.setVolume(bgmvolume);
		m.play();
	}
	
	public void play(SoundID id) {
		assets.get(id).play(sfxvolume);
	}
	
	public void pause() {
		m.pause();
	}
}