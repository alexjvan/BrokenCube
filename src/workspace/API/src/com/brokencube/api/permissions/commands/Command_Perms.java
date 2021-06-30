package com.brokencube.api.permissions.commands;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_Perms extends Command {
	public Command_Perms_Set set;
	public Command_Perms_Unregistered unregistered;
	
	public Command_Perms(API api) {
		super(api, "permissions");
		addAlternative("perms");
		this.useCase = "/permissions(perms) help";
		this.description = "All permissions related commands.";

		this.set = new Command_Perms_Set(this);
		this.unregistered = new Command_Perms_Unregistered(this);
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 1) {
			// List
			// TODO
		} else if(split.length == 2) {
			if(split[1].equalsIgnoreCase("list")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("unregistered"))
				unregistered.exe(e, split);
			else
				throw new CommandNotFoundException();
		} else if(split.length == 3) {
			if(split[1].equalsIgnoreCase("list")) {
				// TODO
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 4) {
			if(split[1].equalsIgnoreCase("give")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("list")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("remove")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("set"))
				set.exe(e, split);
			else if(split[1].equalsIgnoreCase("take")) {
				// TODO
			} else
				throw new CommandNotFoundException();
		} else
			throw new IncorrectArgumentsException();
	}
	
}
