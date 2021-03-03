package com.brokencube.basics.server;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_Server_Stop extends SubCommand {
	public Command_Server_Stop(Command parent) {
		super(parent, "server.stop");
		this.useCase = "/server stop";
		this.description = "Stop the server.";
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		
		if(split.length != 2)
			throw new IncorrectArgumentsException();
		
		instance.getServer().shutdown();
	}

}