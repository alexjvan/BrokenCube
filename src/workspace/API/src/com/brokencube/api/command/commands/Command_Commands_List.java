package com.brokencube.api.command.commands;

import com.brokencube.api.Utils;
import com.brokencube.api.command.CmdSectionHelp;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_Commands_List extends SubCommand {
			
	public Command_Commands_List(Command_Commands parent) {
		super(parent, "commands.list");
		this.useCase = "/commands list [page#]";
		this.description = "List the commands available to you.";
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		
		if(split.length == 1) {
			CmdSectionHelp.help(e, "", 1);
		} else if(split.length == 2) {
			if(Utils.isInt(split[1])) {
				CmdSectionHelp.help(e, "", Integer.parseInt(split[1]));
			} else {
				CmdSectionHelp.help(e, "", 1);
			}
		} else if(split.length == 3) {
			CmdSectionHelp.help(e, "", Integer.parseInt(split[2]));
		}
	}
}
