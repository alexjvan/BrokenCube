package com.brokencube.civilizations.builtconfigs;

import com.brokencube.api.API;
import com.brokencube.api.local.ConfigFile;

public class CivilizationsConfig {
	private ConfigFile conf;
	
	public Prices prices;
	
	public CivilizationsConfig(API api) {
		this.conf = new ConfigFile(api);
		this.prices = new Prices();
		
		load();
	}
	
	private void load() {
		// Try set
		conf.tryAddValue("prices.civilization_creation", 2500);
		conf.tryAddValue("prices.civilization_tax", 100);
		conf.tryAddValue("prices.empire_creation", 50000);
		conf.tryAddValue("prices.empire_tax", 1000);
		conf.tryAddValue("prices.outpost", 6000);
		conf.tryAddValue("prices.plot", 1500);
		conf.tryAddValue("prices.plotPower", 1000);
		
		// Get
		prices.civilization_creation = (int)conf.get("prices.civilization_creation");
		prices.civilization_tax = (int)conf.get("prices.civilization_tax");
		prices.empire_creation = (int)conf.get("prices.empire_creation");
		prices.empire_tax = (int)conf.get("prices.empire_tax");
		prices.outpost = (int)conf.get("prices.outpost");
		prices.plot = (int)conf.get("prices.plot");
		prices.plotPower = (int)conf.get("prices.plotPower");
	}
	
	public void save() {
		// Set
		conf.set("prices.civilization_creation", prices.civilization_creation);
		conf.tryAddValue("prices.civilization_tax", prices.civilization_tax);
		conf.tryAddValue("prices.empire_creation", prices.empire_creation);
		conf.tryAddValue("prices.empire_tax", prices.empire_tax);
		conf.tryAddValue("prices.outpost", prices.outpost);
		conf.tryAddValue("prices.plot", prices.plot);
		conf.tryAddValue("prices.plotPower", prices.plotPower);
		
		// Save
		conf.save();
	}
	
	public class Prices {
		public int civilization_creation;
		public int civilization_tax;
		public int empire_creation;
		public int empire_tax;
		public int outpost;
		public int plot;
		public int plotPower;
	}
}
