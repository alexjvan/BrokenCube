package com.brokencube.api.blockprotection.commands;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_BlockProtection_Place extends SubCommand {
	public static Command_BlockProtection_Place sinstance;

	public Command_BlockProtection_Place(Command parent) {
		super(parent, "blockprotection.place");
		this.useCase = "/blockprotection place [disable|enable|toggle|view]";
		this.description = "Command to deal with the placing ability of block protection.";
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
				instance.getConf().blockProtection.allowPlace = false;
			} else if(split[2].equalsIgnoreCase("enable")) {
				instance.getConf().blockProtection.allowPlace = true;
			} else if(split[2].equalsIgnoreCase("toggle")) {
				if(instance.getConf().blockProtection.allowPlace)
					instance.getConf().blockProtection.allowPlace = false;
				else
					instance.getConf().blockProtection.allowPlace = true;
			} else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}
	
	public void viewMessage(Executor e) {
		if(instance.getConf().blockProtection.allowPlace)
			e.sendMessage("&6The current status of placing is: &aENABLED");
		else
			e.sendMessage("&6The current status of placing is: &cDISABLED");
	}

}
