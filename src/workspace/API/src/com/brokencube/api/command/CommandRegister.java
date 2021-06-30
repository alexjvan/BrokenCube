package com.brokencube.api.command;

import java.util.ArrayList;
import java.util.List;

public class CommandRegister {
	public List<Command> commands = new ArrayList<>();
	
	public void registerCommand(Command com) {
		commands.add(com);
	}
	
	public Command getCommand(String com) {
		String[] split = com.split(" ");
		if(split.length == 0)
			split = new String[] { com };
		for(int i = 0; i < commands.size(); i++)
			if(commands.get(i).isCommand(split[0]))
				return commands.get(i);
		return null;
	}
	
	public List<Command> getCommands() {
		return this.commands;
	}
	
	public void regrabAllPerms() {
		for(int i = 0; i < commands.size(); i++)
			commands.get(i).regrabPerms();
	}
	
}
