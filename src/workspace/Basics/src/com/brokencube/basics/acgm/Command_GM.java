package com.brokencube.basics.acgm;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.NoConsoleImplementationException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_GM extends Command {
	public Command_GM_Toggle toggle;
	
	public Command_GM(API api) {
		super(api, "gamemode");
		addAlternative("gm");
		this.toggle = new Command_GM_Toggle(this);
		this.useCase = "/gamemode(gm) help";
		this.description = "All gamemode related commands.";
	}
	
	@Override
	public void consoleExe(Console c, String[] split) throws CommandNotFoundException, NoConsoleImplementationException,
		NoPermsException {
		throw new NoConsoleImplementationException();
	}
	
	@Override
	public void userExe(User u, String[] split) throws CommandNotFoundException, NoPermsException {
		exe((Executor)u, split);
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, NoPermsException {
		if(split.length == 1) {
			toggle.exe(e, split);
		} else if(split.length == 2) {
			if(split[1].equalsIgnoreCase("adventure")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("creative")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("spectator")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("survival")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("toggle"))
				toggle.exe(e, split);
			else if(split[1].equalsIgnoreCase("view")) {
				// TODO
			} else
				toggle.exe(e, split);
		} else if(split.length == 3) {
			if(split[1].equalsIgnoreCase("adventure")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("creative")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("default")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("set")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("spectator")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("survival")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("toggle"))
				toggle.exe(e, split);
			else if(split[1].equalsIgnoreCase("view")) {
				// TODO
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 4) {
			if(split[1].equalsIgnoreCase("default")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("set")) {
				// TODO
			} else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}

}
