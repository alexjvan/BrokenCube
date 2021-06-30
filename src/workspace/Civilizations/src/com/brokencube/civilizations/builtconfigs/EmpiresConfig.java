package com.brokencube.civilizations.builtconfigs;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.local.ConfigFile;
import com.brokencube.civilizations.empire.Empire;

public class EmpiresConfig {
	private ConfigFile conf;

	public List<Empire> empires;
	
	public EmpiresConfig(API api) {
		conf = new ConfigFile(api, "empires.yml");
		
		load();
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		conf.tryAddValue("civs", new ArrayList<Empire>());
		
		empires = (ArrayList<Empire>)conf.get("empires");
	}
	
	public void save() {
		conf.set("empires", empires);
		conf.save();
	}
}
