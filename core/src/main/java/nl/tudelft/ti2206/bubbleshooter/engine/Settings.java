package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Properties;

import com.badlogic.gdx.Gdx;

public class Settings {

	private static Settings settings = null;
	private String filename = "settings.config";
	Assets assets;
	String currentpath;
	LinkedList<String> list;
	Properties prop;

	public Settings(Assets assets){
		this.assets = assets;
		prop = new Properties();
		list = new LinkedList<String>();
		
		try {
			setSettings(readSettingsFile(getFileName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Settings(){
		this(Assets.getAssets());
	}
	
	/**
	 * Creates and returns the unique instance of the {@link Settings}.
	 * @return	unique instance of {@link Settings}
	 */
	public static Settings getSettings() {
		if (settings == null) settings = new Settings();
		
		return settings;
	}
	
	public void setSettings(String path) {
		this.currentpath = path;
	}
	
	public void nextTheme()  {
		assets.unloadTextures();
		// kinda shitty
		String last = list.removeLast();
		setSettings(last);
		writeSettingsFile();
		list.addFirst(last);
		assets.reload();
	}
	
	public void writeSettingsFile() {
		OutputStream out;
		try {
			out = new FileOutputStream(getFileName());
			prop.setProperty("selected_theme", currentpath);
		try {
			prop.store(out, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String getFileName() {
		return this.filename;
	}
	
	public String readSettingsFile(String res) throws IOException {
		InputStream in = Gdx.files.internal(res).read();
		return readSettingsFile(in);
	}
	
	
	public String readSettingsFile(InputStream in){
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return prop.getProperty("selected_theme");
	}
	
	public void addTheme(String themepath) {
		if(themepath.equals(currentpath)){
			list.addFirst(themepath);
		}
		else{
			list.add(themepath);
		}
	}
	
	public String getCurrentPath() {
		if(currentpath != null){
			return this.currentpath;
		}
		return "";
	}

}
