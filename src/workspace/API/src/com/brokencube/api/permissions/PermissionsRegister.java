package com.brokencube.api.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.brokencube.api.API;
import com.brokencube.api.command.Command;
import com.brokencube.api.command.SubCommand;
import com.brokencube.api.ranks.Rank;

public class PermissionsRegister {
	private API instance;
	
	public TreeMap<String, Rank> perms = new TreeMap<String, Rank>();
	
	public List<String> permsReqNotFound = new ArrayList<String>();
	
	public PermissionsRegister(API instance) {
		this.instance = instance;
		grabPerms();
	}
	
	public void grabPerms() {
		List<String[]> results = instance.getDB().getQuery("SELECT permission, rankid FROM permissions");
		for(int i = 0; i < results.size(); i++) {
			String[] row = results.get(i);
			assignPermission(row[0], instance.getRM().getRankFromNum(Integer.parseInt(row[1])));
		}
	}
	
	public void addPermission(String perm) {
		if(!perms.containsKey(perm)) {
			perms.put(perm, instance.getRM().getRankFromName("Undefined"));
		}
	}

	public void assignPermission(String perm, Rank rank) {
		perms.put(perm, rank);
		rank.perms.add(perm);
		List<Command> cmds = instance.getCR().getCommands();
		for(int u = 0; u < cmds.size(); u++) {
			List<SubCommand> subs = cmds.get(u).children;
			for(int i = 0; i < subs.size(); i++) {
				if(subs.get(i).permString.equals(perm)) {
					subs.get(i).regrabPerm();
					return;
				}
			}
		}
	}
	
	public TreeMap<String, Rank> getPerms() {
		return this.perms;
	}

	public Rank getRank(String permString) {
		if(!perms.containsKey(permString)) {
			this.permsReqNotFound.add(permString);
			return instance.getRM().getRankFromName("Undefined");
		}
		return perms.get(permString);
	}
	
	public boolean permissionExists(String perm) {
		return this.perms.containsKey(perm);
	}
	
}
