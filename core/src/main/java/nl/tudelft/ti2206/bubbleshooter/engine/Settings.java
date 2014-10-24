package nl.tudelft.ti2206.bubbleshooter.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.Gdx;

public class Settings {

	private static Settings settings = null;
	private String filename = "Settings.txt";
	Assets assets;
	String currentpath;

	public Settings(Assets assets){
		this.assets = assets;
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
		writeSettingsFile();
	}
	
	public void left() {
		//TODO take the left item from the current pos
	}
	
	public void right() {
		//TODO take the right item from the current pos
	}
	
	public void writeSettingsFile() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename, "UTF-8");
			//FIXME only writing currently selected theme (nothing with sound settings saving)
			writer.println(currentpath);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return this.filename;
	}
	
	public String readSettingsFile(String res) throws IOException {
		InputStream in = Gdx.files.internal(res).read();
		return readSettingsFile(in);
	}
	
	
	public String readSettingsFile(InputStream in) throws IOException {
		InputStreamReader is = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(is);
		
		String setting;
		while ((setting = br.readLine()) != null) {
			Matcher matcher = Pattern.compile("[GameOptions]").matcher(setting);
			if (!matcher.matches()) throw new IOException();
			
			//TODO only accounting for one line now.
			if((setting = br.readLine()) != null)
				 return setting;
			}
		return setting;
	}
	

}
