package com.brokencube.api.blockprotection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.brokencube.api.API;
import com.brokencube.api.user.User;

public class Event_BlockBreakEvent_BlockProtection implements Listener {
	private API instance;
	
	public Event_BlockBreakEvent_BlockProtection(API instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		User u = (User)instance.getUR().getExecutorFromUsername(e.getPlayer().getName());
		boolean allowBreak = (boolean)instance.getConf().blockProtection.allowBreak;
		if(!allowBreak) {
			if(!u.hasPermission("bp.override.break")) {
				e.setCancelled(true);
			}
		}
	}

}
