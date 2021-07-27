package com.brokencube.api.motd;

import com.brokencube.api.API;
import com.brokencube.api.chat.ColorReplacer;
import com.brokencube.api.motd.listeners.Event_ServerListPing_SetMOTD;

public class MOTD {
	private API instance;
	
	public static int lineLength = (59-1)/2;
	public String[] lines = new String[2];
	public String built;
	
	public MOTD(API instance) {
		this.instance = instance;
		lines[0] = instance.getConf().motd.lineOne;
		lines[1] = instance.getConf().motd.lineTwo;
		buildMOTD();
		
		// ---==EVENTS==---
		instance.getServer().getPluginManager().registerEvents(new Event_ServerListPing_SetMOTD(this), instance);
	}
	
	public void feedConf() {
		instance.getConf().motd.lineOne = lines[0];
		instance.getConf().motd.lineTwo = lines[1];
	}
	
	public void buildMOTD() {
		String built = "";
		for(int i = 0; i < lineLength - lines[0].length(); i++)
			built += " ";
		built += ColorReplacer.colorize(lines[0]);
		built += "\n";
		for(int i = 0; i < lineLength - lines[1].length(); i++)
			built += " ";
		built += ColorReplacer.colorize(lines[1]);
		this.built = built;
	}
	
}
