package com.brokencube.civilizations.empire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brokencube.api.API;
import com.brokencube.civilizations.builtconfigs.EmpiresConfig;

public class EmpiresManager {
	private API api;

	public Map<String, Empire> empires = new HashMap<>();
	
	public EmpiresManager(API api, EmpiresConfig config) {
		this.api = api;
		for(Empire e : config.empires)
			empires.put(e.name, e);
	}
	
	public List<Empire> getCivs() { return (List<Empire>)empires.values(); }
	
	public Empire getByName(String name) {
		if(empires.containsKey(name))
			return empires.get(name);
		return null;
	}
}
