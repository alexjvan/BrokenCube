package com.brokencube.api.permissions.commands;

import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_Perms_Unregistered extends SubCommand {
	
	public Command_Perms_Unregistered(Command parent) {
		super(parent, "perms.unregistered");
		this.useCase = "/permissions unregistered";
		this.description = "Get a list of all of the permissions in the game that have no rank assigned.";
	}
	
	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		
		if(instance.getPR().permsReqNotFound.size() == 0) {
			e.sendMessage(Messages.success+"There are no more requested and unfound permissions!");
			return;
		}
		for(int i = 0; i < instance.getPR().permsReqNotFound.size(); i++) {
			String checkPerm = instance.getPR().permsReqNotFound.get(i);
			
			if(instance.getPR().permissionExists(checkPerm))
				instance.getPR().permsReqNotFound.remove(checkPerm);
			else
				e.sendMessage("&c"+checkPerm);
		}
		// redo - some may have been removed
		if(instance.getPR().permsReqNotFound.size() == 0) {
			e.sendMessage(Messages.success+"There are no more requested and unfound permissions!");
			return;
		}
	}
	
}
