package com.brokencube.api.plugins;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginRegister {

	public Map<String, JavaPlugin> plugins = new HashMap<String, JavaPlugin>();
	
	public PluginRegister() {
		
	}
	
	public void registerPlugin(JavaPlugin plugin, String alias) {
		plugins.put(alias, plugin);
	}
	
	public JavaPlugin getPluginFromAlias(String alias) {
		return plugins.get(alias);
	}
	
}
