package com.brokencube.civilizations;

import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.java.JavaPlugin;

import com.brokencube.api.API;
import com.brokencube.api.economy.Economy;
import com.brokencube.api.user.UserRegister;
import com.brokencube.civilizations.api.CivilizationsUser;

public class Civilizations extends JavaPlugin {
	private API api;
	
	Economy econ;
	
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
	}
	
	@Override
	public void onEnable() {
		api.getPlug().registerPlugin(this, "Basics");
		// Set economy
		econ = new Economy(api, "civs");
		// Load civs and empires
	}
	
	@Override
	public void onDisable() {
		this.econ.updateDB();
	}

	public Economy getEcon() { return this.econ; }
	
}
