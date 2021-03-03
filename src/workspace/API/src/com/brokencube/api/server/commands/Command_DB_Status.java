package com.brokencube.api.server.commands;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_DB_Status extends SubCommand {
	
	public Command_DB_Status(Command parent) {
		super(parent, "db.status");
		this.useCase = "/database status";
		this.description = "Check the database's connection status.";
	}
	
	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		
		if(split.length != 2)
			throw new IncorrectArgumentsException();
		
		if(instance.getDB().connectionOk()) {
			e.sendMessage("&6Connection to the server is &a&lGOOD&r&6.");
		} else {
			e.sendMessage("&6Connection to the server is &c&lBAD&r&6.");
		}
	}

}
