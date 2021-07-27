package com.brokencube.civilizations.civilization.commands;

import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.NoConsoleImplementationException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;
import com.brokencube.civilizations.Civilizations;
import com.brokencube.civilizations.Messages;
import com.brokencube.civilizations.api.CivilizationsUser;
import com.brokencube.civilizations.civilization.Civilization;
import com.brokencube.civilizations.world.CivilizationsWorld;
import com.brokencube.civilizations.world.CivilizationsWorldChunk;
import com.brokencube.civilizations.world.LandType;

public class Command_Civilizations_Create extends SubCommand {
	
	public Command_Civilizations_Create(Command parent) {
		super(parent, "civilizations.create");
		this.useCase = "/civs create <civ name>";
		this.description = "Create a new civilization with the given name at your given position. (Use underscores for spaces)";
	}

	@Override
	public void consoleExe(Console c, String[] split) throws CommandNotFoundException, NoConsoleImplementationException,
		NoPermsException {
		throw new NoConsoleImplementationException();
	}
	
	@Override
	public void userExe(User u, String[] split) throws CommandNotFoundException, NoPermsException {
		exe((Executor)u, split);
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, NoPermsException {
		if(!e.hasPermission(permString))
			throw new NoPermsException();
		CivilizationsUser u = (CivilizationsUser)e;
		if(u.getCivilization() != null) {
			u.sendMessage(Messages.inCiv);
			return;
		}
		// If has money
		if(u.getBalance() >= (int)Civilizations.getInstance().getCivilizationsConfig().prices.civilization_creation) {
			u.sendMessage(com.brokencube.api.Messages.nomoney);
			return;
		}
		// If in valid location
		CivilizationsWorld civworld = Civilizations.getInstance().getWorldManager().getCivWorldFromWorld(u.getWorld());
		if(civworld == null) {
			u.sendMessage(Messages.worldNoExist);
			return;
		}
		CivilizationsWorldChunk chunk = civworld.getChunk(u.getLocation());
		if(chunk.landType != LandType.Wilderness) {
			u.sendMessage(Messages.landTaken);
			return;
		}
		// If name isn't already taken
		String name = split[2];
		if(Civilizations.getInstance().getCivManager().getByName("") != null) {
			u.sendMessage(Messages.civExists);
			return;
		}
		u.removeFromBalance((int)Civilizations.getInstance().getCivilizationsConfig().prices.civilization_creation);
		
		// Create Civilization
		Civilization newCiv = new Civilization(name, u.getUserName(), true);
		
		newCiv.spawn = u.getLocation();
		newCiv.home = chunk;
		newCiv.plots.add(chunk);
		
		// update world
		chunk.landType = LandType.Civilization;
		chunk.ownedBy = newCiv;
		Civilizations.getInstance().getWorldManager().getCivWorldFromWorld(u.getWorld()).setChunkData(chunk);
		
		// Add new civ to manager
		Civilizations.getInstance().getCivManager().civs.put(name, newCiv);
		u.setCivilization(newCiv);
		
		u.sendMessage("&aYou have successfully created the civilization &6" + name + " &a!");
	}
	
}
