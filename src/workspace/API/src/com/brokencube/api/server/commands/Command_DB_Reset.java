package com.brokencube.api.server.commands;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_DB_Reset extends SubCommand {
	public Command_DB_Reset(Command parent) {
		super(parent, "db.reset");
		this.useCase = "/database reset";
		this.description = "Reset the database connection.";
	}
	
	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		
		if(split.length != 2)
			throw new IncorrectArgumentsException();
		
		instance.getDB().resetConnection();
		if(instance.getDB().connectionOk()) {
			e.sendMessage("&a&lConncetion was successfully reset!");
		} else {
			e.sendMessage("&c&lThere was an issue re-establishing the connection and the database can't be found!");
		}
	}
	
}
