package com.brokencube.api.ranks.commands;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoConsoleImplementationException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_Rank extends Command {
	public Command_Rank_View view;
	
	public Command_Rank(API api) {
		super(api, "rank");
		this.view = new Command_Rank_View(this);
		this.useCase = "/rank help";
		this.description = "All rank related commands.";
	}

	@Override
	public void consoleExe(Console c, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoConsoleImplementationException,
		NoPermsException {
		throw new NoConsoleImplementationException();
	}
	
	@Override
	public void userExe(User u, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		exe(u, split);
	}
	
	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 1)
			view.exe(e, split);
		else if(split.length == 2) {
			if(split[1].equalsIgnoreCase("view"))
				view.exe(e, split);
			else
				throw new CommandNotFoundException();
		} else if(split.length == 3) {
			if(split[1].equalsIgnoreCase("view"))
				view.exe(e, split);
			else
				throw new CommandNotFoundException();
		} else
			throw new IncorrectArgumentsException();
	}

}
