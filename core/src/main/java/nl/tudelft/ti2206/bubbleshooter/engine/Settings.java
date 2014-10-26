package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Properties;

import com.badlogic.gdx.Gdx;

/**
 * Is responsible for managing settings:
 * - Read and Write
 * Can also set the next theme.
 * @author group-15
 *
 */
public class Settings {

	/**
	 * Settings has a singleton pattern.
	 */
	private static Settings settings = null;
	private String filename = "settings.config";
	Assets assets;
	String currentpath;
	LinkedList<String> list;
	Properties prop;

	/**
	 * Initializes the {@link Settings} class that holds all settings for Bubbleshooter.
	 * @param assets the {@link Assets} used.
	 */
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
	
	/**
	 * Initializes the {@link Settings} class that holds all settings for Bubbleshooter.
	 */
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
	
	/**
	 * Sets the theme path.
	 * @param path	the path to the stored theme
	 */
	public void setSettings(String path) {
		this.currentpath = path;
	}
	
	/**
	 * Uses the next theme in line.
	 */
	public void nextTheme()  {
		assets.unloadTextures();
		String last = list.removeLast();
		setSettings(last);
		writeSettingsFile();
		list.addFirst(last);
		assets.loadTextures();
	}
	
	/**
	 * Write the selected theme to the config file.
	 */
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
	
	/**
	 * Gets the config file name.
	 * @return filename the name of the config file.
	 */
	public String getFileName() {
		return this.filename;
	}
	
	/**
	 * Reads the config file.
	 * @param res name of the file
	 * @return the applied theme setting
	 * @throws IOException	when the settings file could not be read
	 */
	public String readSettingsFile(String res) throws IOException {
		InputStream in = Gdx.files.internal(res).read();
		return readSettingsFile(in);
	}
	
	/**
	 * Being called by the other readSettingsFile method.
	 * @param in is the {@link InputStream} given from the file.
	 * @return the applied theme setting
	 */
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
	
	/**
	 * Add a theme.
	 * @param themepath the path to the theme.
	 */
	public void addTheme(String themepath) {
		if(themepath.equals(currentpath)){
			list.addFirst(themepath);
		}
		else{
			list.add(themepath);
		}
	}
	
	/**
	 * Gets the current theme path.
	 * @return	the current theme path
	 */
	public String getCurrentPath() {
		if(currentpath != null){
			return this.currentpath;
		}
		return "";
	}

}
