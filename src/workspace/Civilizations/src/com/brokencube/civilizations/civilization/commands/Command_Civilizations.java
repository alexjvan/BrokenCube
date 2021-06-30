package com.brokencube.civilizations.civilization.commands;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.user.Executor;

public class Command_Civilizations extends Command {

	private Command_Civilizations_Create create;
	
	public Command_Civilizations(API instance) {
		super(instance, "civilizations");
		addAlternative("civs");
		addAlternative("c");
		this.useCase = "/civilizations(civs) help";
		this.description = "All civilization related commands.";
		
		this.create = new Command_Civilizations_Create(this);
	}
	
	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, NoPermsException {
		if(split.length == 1) {
			// TODO INFO
		} else if(split.length == 2) {
			if(split[1].equalsIgnoreCase("balance")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("claim")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("delete")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("here")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("info")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("leave")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("residents")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("spawn")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("unclaim")) {
				// TODO
			} else {
				// Civ Name
			}
		} else if(split.length == 3) {
			if(split[1].equalsIgnoreCase("buyPower")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("claim")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("create")) {
				create.exe(e, split);
			} else if(split[1].equalsIgnoreCase("deposit")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("info")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("invite")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("join")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("list")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("outpost")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("remove")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("residents")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("set")) {
				if(split[2].equalsIgnoreCase("homePlot")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("spawn")) {
					// TODO
				} else
					throw new CommandNotFoundException();
			} else if(split[1].equalsIgnoreCase("spawn")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("unclaim")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("view")) {
				// TODO
			} else if(split[1].equalsIgnoreCase("withdraw")) {
				// TODO
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 4) {
			if(split[1].equalsIgnoreCase("set")) {
				if(split[2].equalsIgnoreCase("board")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("inviteOnly")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("mayor")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("name")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("perms")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("plotPrice")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("tag")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("taxes")) {
					// TODO
				} else
					throw new CommandNotFoundException();
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 5) {
			if(split[1].equalsIgnoreCase("set")) {
				if(split[2].equalsIgnoreCase("perms")) {
					// TODO
				} else if(split[2].equalsIgnoreCase("plotPrice")) {
					if(split[3].equalsIgnoreCase("embassy")) {
						// TODO
					} else if(split[3].equalsIgnoreCase("global")) {
						// TODO
					} else if(split[3].equalsIgnoreCase("shop")) {
						// TODO
					} else if(split[3].equalsIgnoreCase("plot")) {
						// TODO
					} else
						throw new CommandNotFoundException();
				} else if(split[2].equalsIgnoreCase("taxes")) {
					if(split[3].equalsIgnoreCase("embassy")) {
						// TODO
					} else if(split[3].equalsIgnoreCase("global")) {
						// TODO
					} else if(split[3].equalsIgnoreCase("shop")) {
						// TODO
					} else if(split[3].equalsIgnoreCase("plot")) {
						// TODO
					} else
						throw new CommandNotFoundException();
				} else
					throw new CommandNotFoundException();
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 6) {
			if(split[1].equalsIgnoreCase("set")) {
				if(split[2].equalsIgnoreCase("perms")) {
					// TODO
				} else
					throw new CommandNotFoundException();
			} else
				throw new CommandNotFoundException();
		} else if(split.length == 7) {
			if(split[1].equalsIgnoreCase("set")) {
				if(split[2].equalsIgnoreCase("perms")) {
					// TODO
				} else
					throw new CommandNotFoundException();
			} else
				throw new CommandNotFoundException();
		} else
			throw new CommandNotFoundException();
	}

}
