package com.brokencube.api.blockprotection.commands;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_BlockProtection extends Command {
	Command_BlockProtection_Break cBreak;
	Command_BlockProtection_Item item;
	Command_BlockProtection_Place place;
	Command_BlockProtection_View view;
	
	public Command_BlockProtection(API api) {
		super(api, "blockprotection");
		this.addAlternative("bp");
		this.useCase = "/blockprotection(bp) help";
		this.description = "All block protection related commands.";
		
		this.cBreak = new Command_BlockProtection_Break(this);
		this.item = new Command_BlockProtection_Item(this);
		this.place = new Command_BlockProtection_Place(this);
		this.view = new Command_BlockProtection_View(this);
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if(split.length == 1) {
			view.exe(e, split);
		} else if(split.length == 2) {
			if(split[1].equalsIgnoreCase("break")) {
				cBreak.exe(e, split);
			} else if(split[1].equalsIgnoreCase("disable")) {
				if(!e.hasPermission("blockprotection.break") && !e.hasPermission("blockprotection.item") && !e.hasPermission("blockprotection.place"))
					throw new CommandNotFoundException();
				if(e.hasPermission("blockprotection.break"))
					instance.getConf().blockProtection.allowBreak = false;
				if(e.hasPermission("blockprotection.item"))
					instance.getConf().blockProtection.allowItem = false;
				if(e.hasPermission("blockprotection.place"))
					instance.getConf().blockProtection.allowPlace = false;
			} else if(split[1].equalsIgnoreCase("enable")) {
				if(!e.hasPermission("blockprotection.break") && !e.hasPermission("blockprotection.item") && !e.hasPermission("blockprotection.place"))
					throw new CommandNotFoundException();
				if(e.hasPermission("blockprotection.break"))
					instance.getConf().blockProtection.allowBreak = true;
				if(e.hasPermission("blockprotection.item"))
					instance.getConf().blockProtection.allowItem = true;
				if(e.hasPermission("blockprotection.place"))
					instance.getConf().blockProtection.allowPlace = true;
			} else if(split[1].equalsIgnoreCase("item")) {
				item.exe(e, split);
			} else if(split[1].equalsIgnoreCase("place")) {
				place.exe(e, split);
			} else if(split[1].equalsIgnoreCase("toggle")) {
				if(!e.hasPermission("blockprotection.break") && !e.hasPermission("blockprotection.item") && !e.hasPermission("blockprotection.place"))
					throw new CommandNotFoundException();
				if(e.hasPermission("blockprotection.break"))
					if(instance.getConf().blockProtection.allowBreak)
						instance.getConf().blockProtection.allowBreak = false;
					else
						instance.getConf().blockProtection.allowBreak = true;
				if(e.hasPermission("blockprotection.item"))
					if(instance.getConf().blockProtection.allowItem)
						instance.getConf().blockProtection.allowItem = false;
					else
						instance.getConf().blockProtection.allowItem = true;
				if(e.hasPermission("blockprotection.place"))
					if(instance.getConf().blockProtection.allowPlace)
						instance.getConf().blockProtection.allowPlace = false;
					else
						instance.getConf().blockProtection.allowPlace = true;
			} else if(split[1].equalsIgnoreCase("view")) {
				view.exe(e, split);
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 3) {
			if(split[1].equalsIgnoreCase("break")) {
				cBreak.exe(e, split);
			} else if(split[1].equalsIgnoreCase("item")) {
				item.exe(e, split);
			} else if(split[1].equalsIgnoreCase("place")) {
				place.exe(e, split);
			} else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}
}
