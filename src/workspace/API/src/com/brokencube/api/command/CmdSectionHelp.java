package com.brokencube.api.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.Messages;
import com.brokencube.api.chat.ColorReplacer;
import com.brokencube.api.user.Executor;
import com.brokencube.api.user.User;

public class CmdSectionHelp {
	
	// User [CmdSection, []]
	private static HashMap<String, HashMap<String, ArrayList<CommandBase>>> usersLists = new HashMap<>();
	
	private static final int numCmdsToSend = 10;
	
	public static void help(Executor e, String cmdSection, int page) {
		sendHelp(e, cmdSection, page);
	}
	
	public static void clearUser(Executor e) {
		String name = "";
		if(e.isUser())
			name = ((User)e).username;
		else
			name = "<Console>";
		
		usersLists.get(name).clear();
	}
	
	private static void sendHelp(Executor e, String cmdSection, int page) {
		String name = "";
		if(e.isUser())
			name = ((User)e).username;
		else
			name = "<Console>";
		// Check if the user has any lists
		if(!usersLists.containsKey(name)) {
			usersLists.put(name, new HashMap<>());
		}
		
		// Check if the user has the particular list...
		HashMap<String, ArrayList<CommandBase>> user = usersLists.get(name);
		if(!user.containsKey(cmdSection)) {
			createList(name, e, cmdSection);
		}
		
		ArrayList<CommandBase> toSend = user.get(cmdSection);
		
		int pages = toSend.size() / numCmdsToSend;
		if(toSend.size() % numCmdsToSend != 0)
			pages++;
		
		String printCmdSec = cmdSection.substring(0, 1).toUpperCase() + cmdSection.substring(1);
		
		e.sendMessage(Messages.general + "-------===["+printCmdSec + (cmdSection!=""?" ":"") +"Help Page " + Messages.success + page + Messages.general + " / " + Messages.success + pages + Messages.general + "]===-------");
		e.sendMessage(ColorReplacer.colorize("&8Key: &7<> &8are required arguments, &7[] &8are optional arguments."));
		if(page > pages) {
			return;
		}
		
		int toSendI = numCmdsToSend;
		if(page == 1) {
			toSendI--;
			e.sendMessage(ColorReplacer.colorize("&8For more commands type in /commands list [page #]"));
		}
		
		for(int i = 0; i < toSendI; i++) {
			int sendIndex = ((page - 1) * (numCmdsToSend - 1)) + i;
			if(sendIndex < toSend.size()) {
				e.sendMessage(ColorReplacer.colorize("&b" + toSend.get(sendIndex).useCase + " &6| &f"+toSend.get(sendIndex).description));
			}
		}
	}
	
	private static void createList(String name, Executor e, String cmdSection) {
		ArrayList<CommandBase> cmds = new ArrayList<>();
		
		List<Command> parents = API.instance.getCR().commands;
		
		if(cmdSection == "") {
			for(int i = 0; i < parents.size(); i++)
				addParent(e, parents.get(i), cmds);
		} else {
			Command c = API.instance.getCR().getCommand(cmdSection);
			addParent(e, c, cmds);
		}
		
		HashMap<String, ArrayList<CommandBase>> user = usersLists.get(name);
		
		user.put(cmdSection, cmds);
	}
	
	private static void addParent(Executor e, Command c, ArrayList<CommandBase> cmds) {
		if(c.children.size() == 0) {
			if(e.isConsole() || e.rank.inherits(c.getLowestPermNeeded()))
				cmds.add(c);
		} else {
			ArrayList<CommandBase> cmdTree = new ArrayList<>();
			cmdTree.add(c);
			boolean adding = false;
			for(int i = 0; i < c.children.size(); i++) {
				if(addChild(e, c.children.get(i), cmdTree))
					adding = true;
			}
			
			if(adding)
				cmds.addAll(cmdTree);
		}
	}
	
	private static boolean addChild(Executor e, SubCommand sc, ArrayList<CommandBase> cmdTree) {
		if(!e.hasPermission(sc.permString))
			return false;
		cmdTree.add(sc);
		return true;
	}
}
