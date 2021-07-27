package com.brokencube.api.motd.commands;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_MOTD extends Command {
	
	public Command_MOTD(API api) {
		super(api, "motd");
		this.useCase = "/motd help";
		this.description = "All motd related commands.";
	}

	@Override
	public void exe(Executor e, String[] split)
			throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		// TODO
	}
	
}
