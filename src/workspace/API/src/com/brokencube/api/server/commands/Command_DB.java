package com.brokencube.api.server.commands;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_DB extends Command {
	public Command_DB_Reset reset;
	public Command_DB_Status status;

	public Command_DB(API instance) {
		super(instance, "database");
		addAlternative("db");
		this.reset = new Command_DB_Reset(this);
		this.status = new Command_DB_Status(this);
		this.useCase = "/database(db) help";
		this.description = "All database related commands.";
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 1) {
			status.exe(e, split);
		} else if(split.length == 2 ) {
			if(split[1].equalsIgnoreCase("reset"))
				reset.exe(e, split);
			else if(split[1].equalsIgnoreCase("status"))
				status.exe(e, split);
			else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}
	
}
