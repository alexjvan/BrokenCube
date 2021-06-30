package com.brokencube.civilizations.builtconfigs;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.local.ConfigFile;
import com.brokencube.civilizations.world.CivilizationsWorldChunk;

public class CivWorldConfig {
	private ConfigFile conf;
	
	public int xoffset;
	public int yoffset;
	public int xMax;
	public int yMax;
	public List<CivilizationsWorldChunk> chunkData;
	
	public CivWorldConfig(API api, String name) {
		this.conf = new ConfigFile(api, "civWorld"+name+".yml");
		
		load();
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		conf.tryAddValue("xoffset", 0);
		conf.tryAddValue("yoffset", 0);
		conf.tryAddValue("xMax", 0);
		conf.tryAddValue("yMax", 0);
		conf.tryAddValue("chunkData", new ArrayList<CivilizationsWorldChunk>());
		
		xoffset = (int)conf.get("xoffset");
		yoffset = (int)conf.get("yoffset");
		xMax = (int)conf.get("xMax");
		yMax = (int)conf.get("yMax");
		chunkData = (ArrayList<CivilizationsWorldChunk>)conf.get("chunkData");
	}
	
	public void save() {
		conf.set("xoffset", xoffset);
		conf.set("yoffset", yoffset);
		conf.set("xMax", xMax);
		conf.set("yMax", yMax);
		conf.set("chunkData", chunkData);
		
		conf.save();
	}
}
