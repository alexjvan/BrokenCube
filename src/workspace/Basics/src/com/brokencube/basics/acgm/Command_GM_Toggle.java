package com.brokencube.basics.acgm;

import org.bukkit.GameMode;

import com.brokencube.api.Messages;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.NoConsoleImplementationException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class Command_GM_Toggle extends SubCommand {
	
	public Command_GM_Toggle(Command parent) {
		super(parent, "gm.toggle");
		this.useCase = "/gm toggle [self|<username>]";
		this.description = "Toggle your, or another player's, gamemode.";
	}
	
	@Override
	public void consoleExe(Console c, String[] args) throws NoConsoleImplementationException {
		throw new NoConsoleImplementationException();
	}
	
	@Override
	public void userExe(User u, String[] args) throws CommandNotFoundException, NoPermsException {
		exe((Executor)u, args);
	}

	@Override
	public void exe(Executor e, String[] args) throws CommandNotFoundException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		if(args.length == 3) {
			if(e.hasPermission("gm.toggle.others")) {
				User target;
				if(args[2].equalsIgnoreCase("self")) {
					target = (User) e;
				} else {
					target = (User)instance.getUR().getExecutorFromUsername(args[2]);
				}
				
				if(target == null) {
					e.sendMessage(Messages.error+"Target player (&b"+args[2]+Messages.error+") was not found!");
					return;
				}
				
				if(target.getPlayer().getGameMode() == GameMode.ADVENTURE) {
					target.getPlayer().setGameMode(GameMode.CREATIVE);
					if(e.hasPermission("gm.toggle.others.hidden")) {
						target.sendMessage("&aYour gamemode has been toggled to &6&lCREATIVE&a mode.");
					} else {
						target.sendMessage("&aYour gamemode has been toggled to &6&lCREATIVE&a mode by &b"+
								((User) e).getCustomName()+"&a.");
					}
					e.sendMessage("&aYou toggled &b" +target.getCustomName()+ "&a's gamemode to &6&lCREATIVE &amode.");
				} else if(target.getPlayer().getGameMode() == GameMode.CREATIVE || 
						((User) e).getPlayer().getGameMode() == GameMode.SPECTATOR || 
						((User) e).getPlayer().getGameMode() == GameMode.SURVIVAL) {
					target.getPlayer().setGameMode(GameMode.ADVENTURE);
					if(e.hasPermission("gm.toggle.others.hidden")) {
						target.sendMessage("&aYour gamemode has been toggled to &6&lADVENTURE&a mode.");
					} else {
						target.sendMessage("&aYour gamemode has been toggled to &6&lADVENTURE&a mode by &b"+
								((User) e).getCustomName()+"&a.");
					}
					e.sendMessage("&aYou toggled &b" +target.getCustomName()+ "&a's gamemode to &6&lADVENTURE &amode.");
				}
			} else
				throw new NoPermsException();
		} else {
			if(((User) e).getPlayer().getGameMode() == GameMode.ADVENTURE) {
				((User) e).getPlayer().setGameMode(GameMode.CREATIVE);
				e.sendMessage("&aYour gamemode has been toggled to &6&lCREATIVE&a mode.");
			} else if(((User) e).getPlayer().getGameMode() == GameMode.CREATIVE || 
					((User) e).getPlayer().getGameMode() == GameMode.SPECTATOR || 
					((User) e).getPlayer().getGameMode() == GameMode.SURVIVAL) {
				((User) e).getPlayer().setGameMode(GameMode.ADVENTURE);
				e.sendMessage("&aYour gamemode has been toggled to &6&lADVENTURE&a mode.");
			}
		}
	}
}
