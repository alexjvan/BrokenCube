package com.brokencube.api.permissions.commands;

import com.brokencube.api.Messages;
import com.brokencube.api.Utils;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.command.exceptions.CommandNotFoundException;
import com.brokencube.api.command.exceptions.IncorrectArgumentsException;
import com.brokencube.api.command.exceptions.NoPermsException;
import com.brokencube.api.ranks.Rank;
import com.brokencube.api.user.Executor;

import net.md_5.bungee.api.ChatColor;

public class Command_Perms_Set extends SubCommand {

	public Command_Perms_Set(Command parent) {
		super(parent, "perms.set");
		this.useCase = "/permissions set <permission> <rankName|rankNum>";
		this.description = "Assign a permission to a rank.";
	}

	@Override
	public void exe(Executor e, String[] split) throws CommandNotFoundException, IncorrectArgumentsException, NoPermsException {
		if (!e.hasPermission(permString))
			throw new NoPermsException();

		String rank = split[3];
		Rank r;

		if (Utils.isInt(rank)) {
			int i = Integer.parseInt(rank);
			r = instance.getRM().getRankFromNum(i);
		} else {
			r = instance.getRM().getRankFromName(rank);
		}
		if (r == null) {
			e.sendMessage(Messages.error + "Rank given (&b" + rank + Messages.error + ") is not a valid rank.");
			return;
		}

		boolean database = false;
		
		// need to insert into db
		if (!instance.getPR().permissionExists(split[2])) {
			database = instance.getDB().insertQuery("INSERT INTO permissions VALUES ('" + split[2] + "', " + r.num + ")");
		}
		// need to update db
		else {
			int changed = instance.getDB().updateQuery("UPDATE permissions SET rankid=" + r.num + " WHERE permission=\"" + split[2] + "\"");
			if(changed != 0)
				database = true;
		}
		if(database) {
			instance.getPR().assignPermission(split[2], r);
			e.sendMessage(Messages.success + "Successfully set " + ChatColor.GOLD + split[2] + ChatColor.GREEN + " to " + ChatColor.GOLD + r.name + ChatColor.GREEN + "!");
		} else {
			e.sendMessage(Messages.error+"Issue is manipulating the database! Cancelling internal manipulation");
			return;
		}
	}

}
