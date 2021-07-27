package com.brokencube.civilizations.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.brokencube.api.API;
import com.brokencube.api.chat.ColorReplacer;
import com.brokencube.civilizations.api.CivilizationsUser;

public class Event_AsyncPlayerChat_CivPlayerChatFormatter implements Listener {
	private API instance;
	
	public Event_AsyncPlayerChat_CivPlayerChatFormatter(API instance) {
		this.instance = instance;
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		CivilizationsUser u = (CivilizationsUser)instance.getUR().getExecutorFromUsername(e.getPlayer().getName());
		String msg = e.getMessage();
		
		String format = instance.getConf().chat.format;

		String formatted = ColorReplacer.colorize(format);
		
		while(formatted.contains("{username}")) 
			formatted = formatted.replace("{username}", ColorReplacer.colorize(u.getUserName()));

		while(formatted.contains("{rankprefix}")) 
			formatted = formatted.replace("{rankprefix}", ColorReplacer.colorize(u.rank.prefix));

		while(formatted.contains("{message}")) 
			formatted = formatted.replace("{message}", msg);
		
		while(formatted.contains("{civtag}"))
			formatted = formatted.replace("{civtag}", u.getCivilization().tag);
		
		while(formatted.contains("{empiretag}"))
			formatted = formatted.replace("{empiretag}", u.getEmpire().tag);
		
		instance.getUR().sendMessageAll(formatted, u.hasPermission("chat.color"));
		
		e.setCancelled(true);
	}
	
}
