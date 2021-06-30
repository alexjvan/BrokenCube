package com.brokencube.civilizations.civilization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brokencube.api.API;
import com.brokencube.civilizations.builtconfigs.CivsConfig;

public class CivilizationsManager {
	private API api;

	public Map<String, Civilization> civs = new HashMap<>();
	
	public CivilizationsManager(API api, CivsConfig config) {
		this.api = api;
		for(Civilization civ : config.civs)
			civs.put(civ.name, civ);
	}
	
	public List<Civilization> getCivs() { return (List<Civilization>)civs.values(); }
	
	public Civilization getByName(String name) {
		if(civs.containsKey(name))
			return civs.get(name);
		return null;
	}
	
}
