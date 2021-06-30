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
	public Command_Rank_Set set;
	public Command_Rank_View view;
	
	public Command_Rank(API api) {
		super(api, "rank");
		this.useCase = "/rank help";
		this.description = "All rank related commands.";

		this.set = new Command_Rank_Set(this);
		this.view = new Command_Rank_View(this);
	}

	@Override
	public void consoleExe(Console c, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoConsoleImplementationException,
		NoPermsException {
		exe(c, split);
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
			if(split[1].equalsIgnoreCase("booster")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("information")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("inheritance")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("prefix")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("unique")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("view"))
				view.exe(e, split);
			else
				view.exe(e, split);
		} else if(split.length == 3) {
			if(split[1].equalsIgnoreCase("booster")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("inheritance")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("prefix")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("view"))
				view.exe(e, split);
			else
				throw new CommandNotFoundException();
		} else if(split.length == 4) {
			if(split[1].equalsIgnoreCase("rename")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("set")) {
				set.exe(e, split);
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 5) {
			if(split[1].equalsIgnoreCase("booster")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("inheritance")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("prefix")) {
				// TODO
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 6 && split[1].equalsIgnoreCase("create")) {
			// TODO
		} else
			throw new CommandNotFoundException();
	}

}
