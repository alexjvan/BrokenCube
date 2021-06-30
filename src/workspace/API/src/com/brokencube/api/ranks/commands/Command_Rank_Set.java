package com.brokencube.api.ranks.commands;

import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.ranks.Rank;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_Rank_Set extends SubCommand {
	
	public Command_Rank_Set(Command parent) {
		super(parent, "rank.set");
		this.useCase = "/rank set <user> <rank>";
		this.description = "Set a user's rank.";
	}
	
	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		
		User target = (User)parent.getInstance().getUR().getExecutorFromUsername(split[2]);
		if(target == null) {
			e.sendMessage(Messages.error+"That user does not seem to exist.");
			return;
		}
		
		Rank r = parent.getInstance().getRM().getRankFromName(split[3]);
		if(r == null) {
			e.sendMessage(Messages.error+"That rank does not seem to exist.");
			return;
		}
		
		if(e.isUser()) {
			User u = (User)e;
			if(!u.rank.inherits(target.rank)) {
				u.sendMessage(Messages.error+"You cannot set the rank of someone who is a higher rank than you.");
				return;
			}
			if(u.rank.inherits(r)) {
				u.sendMessage(Messages.error+"You cannot set the rank of someone else to one that is higher than yours.");
				return;
			}
			// Set Rank
			target.rank = r;
			u.sendMessage(Messages.success+"You have set &6"+target.username+" &ato the rank &6"+r.name+"&a.");
			target.sendMessage(Messages.success+"Your rank has been set to &6"+r.name+"&a.");
		} else {
			// Set Rank
			target.rank = r;
			e.sendMessage(Messages.success+"You have set &6"+target.username+" &ato the rank &6"+r.name+"&a.");
			target.sendMessage(Messages.success+"Your rank has been set to &6"+r.name+"&a.");
		}
	}

}
