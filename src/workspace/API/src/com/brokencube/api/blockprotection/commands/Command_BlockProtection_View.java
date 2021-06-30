package com.brokencube.api.blockprotection.commands;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_BlockProtection_View extends SubCommand {

	public Command_BlockProtection_View(Command parent) {
		super(parent, "blockprotection.view");
		this.useCase = "/blockprotection view";
		this.description = "Command to deal with the viewing the status of block protections.";
	}
	

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		Command_BlockProtection_Break.sinstance.viewMessage(e);
		Command_BlockProtection_Item.sinstance.viewMessage(e);
		Command_BlockProtection_Place.sinstance.viewMessage(e);
	}

}
