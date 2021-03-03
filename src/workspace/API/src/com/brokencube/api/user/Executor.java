package com.brokencube.api.user;

import org.bukkit.command.CommandSender;

import com.brokencube.api.chat.ColorReplacer;
import com.brokencube.api.ranks.Rank;

public abstract class Executor {
	
	protected CommandSender executor;
	
	public Rank rank;
	
	public Executor(CommandSender exe) {
		this.executor = exe;
	}
	
	public abstract boolean isUser();
	public abstract boolean isConsole();
	public abstract boolean hasPermission(String perm);
	
	public void sendMessage(String message) {
		this.executor.sendMessage(ColorReplacer.colorize(message));
	}
	
	public void sendMessageNoColor(String message) {
		this.executor.sendMessage(message);
	}

}
