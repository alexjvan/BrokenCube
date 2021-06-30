package com.brokencube.api.user.commands;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_User extends Command {

	public Command_User(API instance) {
		super(instance, "user");
	}

	@Override
	public void exe(Executor e, String[] split)
			throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 1) {
			// Information
			// TODO
		} else if(split.length == 2) {
			if(split[1].equalsIgnoreCase("information") || split[1].equalsIgnoreCase("info")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("statistics") || split[1].equalsIgnoreCase("stats")) {
				// TODO
			} else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}
	
}
