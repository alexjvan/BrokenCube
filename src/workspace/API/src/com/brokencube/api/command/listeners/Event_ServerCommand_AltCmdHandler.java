package com.brokencube.api.command.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import com.brokencube.api.API;
import com.brokencube.api.Messages;
import com.brokencube.api.command.CmdSectionHelp;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoConsoleImplementationException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Console;

public class Event_ServerCommand_AltCmdHandler implements Listener {
	private API instance;
	
	public Event_ServerCommand_AltCmdHandler(API instance) {
		this.instance = instance;
	}

	@EventHandler
	public void onServerCommand(ServerCommandEvent e) {
		Console c = instance.getUR().getConsole();
		
		String cmd = e.getCommand();
		String[] split = cmd.split(" ");
		if(split.length == 0) 
			split = new String[] {cmd};
		else if(split.length > 1 && split[1] == "help") {
			// <cmd section> help [pg#]
			String cmdSection = split[0];
			if(split.length > 2 && split.length == 3) {
				int page = Integer.parseInt(split[2]);
				CmdSectionHelp.help(c, cmdSection, page);
			} else {
				CmdSectionHelp.help(c, cmdSection, 1);
			}
		}
		
		Command cm = instance.getCR().getCommand(cmd);
		
		if(cm == null) {
			c.sendMessage(Messages.nocmd);
			e.setCancelled(true);
			return;
		}
		
		try {
			cm.consoleExe(c, split);
		} catch (CommandNotFoundException e1) {
			c.sendMessage(Messages.nocmd);
		} catch(IncorrectArgumentsException e1) {
			c.sendMessage(Messages.wrongArgs);
		} catch (NoConsoleImplementationException e1) {
			c.sendMessage(Messages.noconsole);
		} catch (NoPermsException e1) {
			c.sendMessage(Messages.noperms);
		}
		
		e.setCancelled(true);
	}
	
}
