package com.brokencube.api.motd.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import com.brokencube.api.motd.MOTD;

public class Event_ServerListPing_SetMOTD implements Listener {
	private MOTD motd;
	
	public Event_ServerListPing_SetMOTD(MOTD motd) {
		this.motd = motd;
	}
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent e) {
		e.setMotd(motd.built);
		e.setMaxPlayers(0);
		// TODO
		//e.setServerIcon(null);
	}

}
