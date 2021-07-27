package com.brokencube.api.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.brokencube.api.API;

public class TabCompleteMain implements TabCompleter {
	private API instance;
	
	public TabCompleteMain(API instance) {
		this.instance = instance;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] args) {
		if(args.length == 0) {
			List<String> cmds = new ArrayList<>();
			
			
			
			Collections.sort(cmds);
			return cmds;
		}
		return null;
	}

}
