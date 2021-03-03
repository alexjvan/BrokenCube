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
		
	}
	
}
