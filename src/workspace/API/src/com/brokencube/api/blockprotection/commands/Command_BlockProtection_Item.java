package com.brokencube.api.blockprotection.commands;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_BlockProtection_Item extends SubCommand {
	public static Command_BlockProtection_Item sinstance;

	public Command_BlockProtection_Item(Command parent) {
		super(parent, "blockprotection.item");
		this.useCase = "/blockprotection item [disable|enable|toggle|view]";
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
				instance.getConf().blockProtection.allowItem = false;
			} else if(split[2].equalsIgnoreCase("enable")) {
				instance.getConf().blockProtection.allowItem = true;
			} else if(split[2].equalsIgnoreCase("toggle")) {
				if(instance.getConf().blockProtection.allowPlace)
					instance.getConf().blockProtection.allowItem = false;
				else
					instance.getConf().blockProtection.allowItem = true;
			} else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}
	
	public void viewMessage(Executor e) {
		if(instance.getConf().blockProtection.allowItem)
			e.sendMessage("&6The current status of item usage is: &aENABLED");
		else
			e.sendMessage("&6The current status of item usage is: &cDISABLED");
	}

}
