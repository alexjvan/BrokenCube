package com.brokencube.basics.server;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_Server extends Command {
	public Command_Server_Stop stop;
	
	public Command_Server(API api) {
		super(api, "server");
		this.stop = new Command_Server_Stop(this);
		this.useCase = "/server help";
		this.description = "All server related commands.";
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 2) {
			if(split[1].equalsIgnoreCase("stop"))
				stop.exe(e, split);
			else
				throw new CommandNotFoundException();
		} else
			throw new IncorrectArgumentsException();
	}

}
