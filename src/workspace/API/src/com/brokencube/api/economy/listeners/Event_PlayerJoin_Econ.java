package com.brokencube.api.economy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.brokencube.api.economy.Economy;

public class Event_PlayerJoin_Econ implements Listener {
	private Economy econ;

	public Event_PlayerJoin_Econ(Economy econ) {
		this.econ = econ;
	}
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		econ.getBalance(e.getPlayer().getName());
	}
}
