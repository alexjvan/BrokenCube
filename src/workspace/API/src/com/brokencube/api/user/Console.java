package com.brokencube.api.user;

import com.brokencube.api.API;

public class Console extends Executor {

	public Console(API instance) {
		super(instance.getServer().getConsoleSender());
		rank = API.instance.getRM().getRankFromName("Console");
	}
	
	@Override
	public boolean isUser() {
		return false;
	}
	
	@Override
	public boolean isConsole() {
		return true;
	}

	@Override
	public boolean hasPermission(String perm) {
		return true;
	}

}
