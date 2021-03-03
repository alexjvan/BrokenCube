package com.brokencube.api.server.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.brokencube.api.user.User;

public class UserAddedToRegisterEvent extends Event implements Cancellable {
	protected User user;
	private boolean isCancelled;
	private static final HandlerList HANDLERS_LIST = new HandlerList();
	
	public UserAddedToRegisterEvent(User user) {
		this.user = user;
		this.isCancelled = false;
	}
	
	@Override
	public boolean isCancelled() {
		return this.isCancelled;
	}
	
	@Override
	public void setCancelled(boolean value) {
		this.isCancelled = value;
	}
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS_LIST;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS_LIST;
	}
	
	public User getUser() {
		return this.user;
	}

}
