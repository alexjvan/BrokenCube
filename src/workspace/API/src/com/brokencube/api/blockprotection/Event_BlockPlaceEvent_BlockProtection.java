package com.brokencube.api.blockprotection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.brokencube.api.API;
import com.brokencube.api.user.User;

public class Event_BlockPlaceEvent_BlockProtection implements Listener {
	private API instance;
	
	public Event_BlockPlaceEvent_BlockProtection(API instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		User u = (User)instance.getUR().getExecutorFromUsername(e.getPlayer().getName());
		boolean allowPlace = (boolean)instance.getConf().get("bp.allowPlace");
		if(!allowPlace) {
			if(!u.hasPermission("bp.override.place")) {
				e.setCancelled(true);
			}
		}
	}

}
