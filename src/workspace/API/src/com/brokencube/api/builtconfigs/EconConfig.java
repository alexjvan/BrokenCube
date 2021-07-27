package com.brokencube.api.builtconfigs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.brokencube.api.API;
import com.brokencube.api.local.ConfigFile;

public class EconConfig {
	private ConfigFile conf;
	
	public Map<String, Double> balances;
	
	@SuppressWarnings("unchecked")
	public EconConfig(API api) {
		this.conf = new ConfigFile(api, "econ.yml");

		conf.tryAddValue("users", new ArrayList<String>());
		conf.tryAddValue("balances", new ArrayList<Double>());
		
		List<String> users = (ArrayList<String>)conf.get("users");
		List<Double> bals = (ArrayList<Double>)conf.get("balances");

		balances = new HashMap<>();
		for(int i = 0; i < users.size(); i++)
			balances.put(users.get(i), bals.get(i));
	}
	
	@SuppressWarnings("unchecked")
	public void save() {
		conf.set("users", (List<String>)balances.keySet());
		conf.set("balances", (List<Double>)balances.values());
		conf.save();
	}

}
