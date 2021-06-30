package com.brokencube.civilizations;

import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.java.JavaPlugin;

import com.brokencube.api.API;
import com.brokencube.api.economy.Economy;
import com.brokencube.api.user.UserRegister;
import com.brokencube.civilizations.api.CivilizationsUser;
import com.brokencube.civilizations.builtconfigs.CivilizationsConfig;
import com.brokencube.civilizations.builtconfigs.CivsConfig;
import com.brokencube.civilizations.builtconfigs.EmpiresConfig;
import com.brokencube.civilizations.civilization.CivilizationsManager;
import com.brokencube.civilizations.civilization.commands.Command_Civilizations;
import com.brokencube.civilizations.world.CivilizationsWorldManager;

public class Civilizations extends JavaPlugin {
	private API api;
	private static Civilizations instance;
	
	Economy econ;
	
	private CivilizationsConfig config;
	private CivsConfig civs;
	private EmpiresConfig empires;

	private CivilizationsWorldManager civworldmanager;
	private CivilizationsManager civManager;
	
	@Override
	public void onLoad() {
		api = API.instance;
		while(api == null) {
			api = API.instance;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) { }
		}
		api.setUR(new UserRegister<CivilizationsUser>(api));
		instance = this;
		// Config files
		config = new CivilizationsConfig(api);
		civs = new CivsConfig(api);
		empires = new EmpiresConfig(api);
	}
	
	@Override
	public void onEnable() {
		api.getPlug().registerPlugin(this, "Civilizations");
		// Set economy
		econ = new Economy(api, "civs");
		// Load civs and empires
		civManager = new CivilizationsManager(api, civs);
		// Load worlds
		civworldmanager = new CivilizationsWorldManager(api);
		
		// ---==Commands==---
		api.getCR().registerCommand(new Command_Civilizations(api));
	}
	
	@Override
	public void onDisable() {
		this.econ.save();
		
		config.save();
		civs.feed(civManager.getCivs());
		civs.save();
		empires.save();
	}

	public Economy getEcon() { return this.econ; }
	public CivilizationsManager getCivManager() { return this.civManager; }
	public CivilizationsWorldManager getWorldManager() { return this.civworldmanager; }
	
	public CivilizationsConfig getCivilizationsConfig() { return this.config; }
	public CivsConfig getCivsConfig() { return this.civs; }
	public EmpiresConfig getEmpiresConfig() { return this.empires; }
	
	public static Civilizations getInstance() { return instance; }
	
}
