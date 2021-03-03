package com.brokencube.basics;

import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.java.JavaPlugin;

import com.brokencube.api.API;
import com.brokencube.basics.acgm.Command_GM;
import com.brokencube.basics.server.Command_Server;

public class Basics extends JavaPlugin {
	private API api;
	
	@Override
	public void onEnable() {
		api = API.instance;
		while(api == null) {
			api = API.instance;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) { }
		}
		api.getPlug().registerPlugin(this, "Basics");
		
		api.getCR().registerCommand(new Command_GM(api));
		api.getCR().registerCommand(new Command_Server(api));
	}
	
	@Override
	public void onDisable() {
		
	}
}
