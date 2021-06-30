package com.brokencube.api.blockprotection.commands;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_BlockProtection_Break extends SubCommand {
	public static Command_BlockProtection_Break sinstance;

	public Command_BlockProtection_Break(Command parent) {
		super(parent, "blockprotection.break");
		this.useCase = "/blockprotection break [disable|enable|toggle|view]";
		this.description = "Command to deal with the breaking ability of block protection.";
		sinstance = this;
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 2) {
			if(!e.hasPermission("blockprotection.view"))  
				throw new NoPermsException();
			viewMessage(e);
		} else if(split.length == 3) {
			if(!e.hasPermission(permString))
				throw new NoPermsException();
			if(split[2].equalsIgnoreCase("disable")) {
				instance.getConf().blockProtection.allowBreak = false;
			} else if(split[2].equalsIgnoreCase("enable")) {
				instance.getConf().blockProtection.allowBreak = true;
			} else if(split[2].equalsIgnoreCase("toggle")) {
				if(instance.getConf().blockProtection.allowBreak)
					instance.getConf().blockProtection.allowBreak = false;
				else
					instance.getConf().blockProtection.allowBreak = true;
			} else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}
	
	public void viewMessage(Executor e) {
		if(instance.getConf().blockProtection.allowBreak)
			e.sendMessage("&6The current status of breaking is: &aENABLED");
		else
			e.sendMessage("&6The current status of breaking is: &cDISABLED");
	}
}
