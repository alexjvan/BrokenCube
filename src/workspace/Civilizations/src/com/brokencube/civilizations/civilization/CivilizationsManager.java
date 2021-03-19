package com.brokencube.civilizations.civilization;

import java.util.HashMap;
import java.util.Map;

import com.brokencube.api.API;

public class CivilizationsManager {
	private API api;

	public Map<String, Civilization> civs = new HashMap<String, Civilization>();
	
	public CivilizationsManager(API api) {
		this.api = api;
		// Load the civs from the database
	}
	
}
