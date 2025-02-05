package com.brokencube.api.ranks.commands;

import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoConsoleImplementationException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_Rank_View extends SubCommand {
	
	public Command_Rank_View(Command parent) {
		super(parent, "rank.view");
		this.useCase = "/rank view";
		this.description = "View what rank you are.";
	}
	
	@Override
	public void consoleExe(Console c, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoConsoleImplementationException, 
		NoPermsException {
		throw new NoConsoleImplementationException();
	}
	
	@Override
	public void userExe(User u, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
			exe(u, split);
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 1 || split.length == 2 && split[1].equalsIgnoreCase("view")) {
			e.sendMessage(Messages.success+"You have the rank of "+((User) e).rank.prefix+Messages.success+".");
		} else {
			if(!e.hasPermission(permString))
				throw new NoPermsException();
			
			String targetName;
			if(split.length == 3)
				targetName = split[2];
			else
				targetName = split[1];
			User target = (User)instance.getUR().getExecutorFromUsername(targetName);
			if(target == null) {
				e.sendMessage(Messages.error+"That player (&b"+targetName+Messages.error+") does not exist or is not online!");
				return;
			}
			e.sendMessage("&b"+targetName+Messages.success+" has the rank of "+target.rank.prefix);
		}
	}
}
