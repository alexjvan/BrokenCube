package com.brokencube.civilizations.builtconfigs;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.local.ConfigFile;
import com.brokencube.civilizations.civilization.Civilization;

public class CivsConfig {
	private ConfigFile conf;

	public List<Civilization> civs;
	
	public CivsConfig(API api) {
		conf = new ConfigFile(api, "civilizations.yml");
		
		load();
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		conf.tryAddValue("civs", new ArrayList<Civilization>());
		
		civs = (ArrayList<Civilization>)conf.get("civs");
	}
	
	public void feed(List<Civilization> civs) { this.civs = civs; }
	
	public void save() {
		conf.set("civs", civs);
		conf.save();
	}
}
