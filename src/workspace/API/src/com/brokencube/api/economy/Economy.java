package com.brokencube.api.economy;

import com.brokencube.api.API;
import com.brokencube.api.builtconfigs.EconConfig;
import com.brokencube.api.economy.listeners.Event_PlayerJoin_Econ;

public class Economy {
	private EconConfig config;
	
	public Economy(API api, String holder) {
		config = new EconConfig(api);
		
		// ---==EVENTS==---
		api.getServer().getPluginManager().registerEvents(new Event_PlayerJoin_Econ(this), api);
	}
	
	public void save() { 
		config.save();
	}
	
	public double getBalance(String username) {
		if(!config.balances.keySet().contains(username))
			config.balances.put(username, 0.0);
		return config.balances.get(username);
	}
	
	public void setBalance(String username, double balance) {
		config.balances.put(username, balance);
	}
	
	public void addToBalance(String username, double amount) {
		config.balances.put(username, config.balances.get(username) + amount);
	}
	
	public void subtractFromBalance(String username, double amount) {
		config.balances.put(username, config.balances.get(username) - amount);
	}
}
