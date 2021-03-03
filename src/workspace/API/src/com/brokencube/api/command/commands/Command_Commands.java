package com.brokencube.api.command.commands;

import com.brokencube.api.API;
import com.brokencube.api.Utils;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_Commands extends Command {
	private Command_Commands_List list;
	
	public Command_Commands(API api) {
		super(api, "commands");
		this.addAlternative("cmds");
		this.list = new Command_Commands_List(this);
		this.useCase = "/commands help";
		this.description = "All command related commands.";
	}

	@Override
	public void exe(Executor e, String[] split)
			throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 1) {
			list.exe(e, split);
		} else if(split.length == 2) {
			if(split[1].equalsIgnoreCase("list")) {
				list.exe(e, split);
			} else if(Utils.isInt(split[1])) {
				list.exe(e, split);
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 3) {
			if(split[1].equalsIgnoreCase("list")) {
				if(Utils.isInt(split[2]))
					list.exe(e, split);
				else
					throw new IncorrectArgumentsException();
			} else
				throw new CommandNotFoundException();
		} else
			throw new IncorrectArgumentsException();
	}
	
}
