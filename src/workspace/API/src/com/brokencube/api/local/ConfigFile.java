package com.brokencube.api.local;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.brokencube.api.API;

public class ConfigFile {
	private API instance;

	String filename = "config.yml";
	File file;
	FileConfiguration config;
	Map<String, Object> values = new HashMap<String, Object>();
	
	public ConfigFile(API instance) {
		this.instance = instance;
		initFiles();
	}
	
	public ConfigFile(API instance, String filename) {
		this.instance = instance;
		this.filename = filename;
		initFiles();
	}
	
	private void initFiles() {
		this.file = new File(instance.getDataFolder(), filename);
		if(!file.exists()) {
			if(!instance.getDataFolder().exists()) {
				instance.getLogger().log(Level.SEVERE, "Creating data folder " + filename);
				instance.getDataFolder().mkdir();
			}
			instance.getLogger().log(Level.SEVERE, "Creating configuration file " + filename);
			try {
				file.createNewFile();
			} catch (IOException e) {
				instance.getLogger().log(Level.SEVERE, "Cannot create config file!", e);
			}
		}
		
		config = YamlConfiguration.loadConfiguration(this.file);
		config.options().copyDefaults();
	}
	
	public void tryAddValue(String name, Object value) {
		Object val = config.get(name);
		if(val != null) {
			values.put(name, val);
		} else {
			values.put(name, value);
			save();
		}
	}
	
	public Object get(String name) {
		return values.get(name);
	}
	
	public String[] emptyValues() {
		List<String> empty = new ArrayList<String>();
		String[] keys = (String[])values.keySet().toArray();
		for(int i = 0; i < keys.length; i++) {
			if(values.get(keys[i]) == null)
				empty.add(keys[i]);
		}
		return (String[])empty.toArray();
	}

	public void set(String name, Object value) {
		values.put(name, value);
		config.set(name, value);
		save();
	}
	
	public void save() {
		for(Map.Entry<String, Object> entry : values.entrySet()) {
			config.set(entry.getKey(), entry.getValue());
		}
		try {
			config.save(file);
		} catch (IOException e) {
			instance.getLogger().log(Level.SEVERE, "Cannot save config file!", e);
		}
	}
	
}
