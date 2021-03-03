package com.brokencube.civilizations.civilization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.bukkit.Location;

import com.brokencube.civilizations.api.Ownership;
import com.brokencube.civilizations.api.PrivacyStatus;
import com.brokencube.civilizations.world.CivilizationsWorldChunk;

public class Civilization extends Ownership {
	public List<String> deputies;
	public List<String> citizens;
	
	public int plotTax;
	public int shopTax;
	public int embassyTax;
	
	public int plotPrice;
	public int shopPrice;
	public int embassyPrice;
	
	public int landLimit;
	public int bonusPlots;
	
	public Location spawn;
	public CivilizationsWorldChunk home;
	public List<CivilizationsWorldChunk> plots;
	public List<CivilizationsWorldChunk> outposts;

	public Civilization(String name, String owner) {
		super(name, owner);
	}
	
	public Civilization(File f) throws FileNotFoundException {
		super(f);
	}
}
