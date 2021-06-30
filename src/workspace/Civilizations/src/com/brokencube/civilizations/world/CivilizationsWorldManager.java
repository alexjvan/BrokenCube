package com.brokencube.civilizations.world;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import com.brokencube.api.API;

public class CivilizationsWorldManager {
	private API api;
	
	List<CivilizationsWorld> worlds = new ArrayList<>();
	
	public CivilizationsWorldManager(API api) {
		this.api = api;
		loadWorlds();
	}
	
	private void loadWorlds() {
		World[] worlds = api.getWM().getAllWorlds();
		for(World w : worlds)
			this.worlds.add(new CivilizationsWorld(api, w));
	}
	
	public void saveWorlds() {
		for(CivilizationsWorld w : worlds)
			w.save();
	}
	
	public CivilizationsWorld getCivWorldFromWorld(World w) {
		for(CivilizationsWorld cw : worlds)
			if(cw.world == w)
				return cw;
		return null;
	}
	
}
