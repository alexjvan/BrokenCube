package com.brokencube.api.blockprotection;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import com.brokencube.api.API;
import com.brokencube.api.user.User;

public class Event_ItemEvents_BlockProtection implements Listener {
	private API instance;
	
	public Event_ItemEvents_BlockProtection(API instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {
		User u = (User)instance.getUR().getExecutorFromUsername(e.getPlayer().getName());
		if(check(e, u)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onYummu(PlayerItemConsumeEvent e) {
		User u = (User)instance.getUR().getExecutorFromUsername(e.getPlayer().getName());
		if(check(e, u)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSleepy(PlayerBedEnterEvent e) {
		User u = (User)instance.getUR().getExecutorFromUsername(e.getPlayer().getName());
		if(check(e, u)) {
			e.setCancelled(true);
		}
	}
	
	private boolean check(Event e, User u) {
		boolean allowItem = (boolean)instance.getConf().get("bp.allowItem");
		if(!allowItem) {
			if(!u.hasPermission("bp.override.item")) {
				return true;
			}
		}
		return false;
	}

}
