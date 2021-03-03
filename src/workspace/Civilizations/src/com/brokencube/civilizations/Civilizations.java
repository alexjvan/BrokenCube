package com.brokencube.civilizations;

import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.java.JavaPlugin;

import com.brokencube.api.API;
import com.brokencube.api.local.ConfigFile;

public class Civilizations extends JavaPlugin {
	private API api;
	
	ConfigFile gridFile;
	ConfigFile townsFile;
	
	@Override
	public void onEnable() {
		api = API.instance;
		while(api == null) {
			api = API.instance;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) { }
		}
		api.getPlug().registerPlugin(this, "Basics");
		
		gridFile = new ConfigFile(api, "gridData.yml");
		townsFile = new ConfigFile(api, "townsData.yml");
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
