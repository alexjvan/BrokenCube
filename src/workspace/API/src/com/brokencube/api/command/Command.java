package com.brokencube.api.command;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.ranks.Rank;

public abstract class Command extends CommandBase {
	protected API instance;
	
	public String starter;
	
	public List<SubCommand> children = new ArrayList<SubCommand>();
	
	Rank lowestPermNeeded;

	public Command(API instance, String starter) {
		this.instance = instance;
		this.starter = starter;
		lowestPermNeeded = instance.getRM().getRankFromName("Undefined");
	}
	
	public boolean isCommand(String check) {
		if(starter.equalsIgnoreCase(check))
			return true;
		for(int i = 0; i < alternatives.size(); i++)
			if(alternatives.get(i).equalsIgnoreCase(check))
				return true;
		return false;
	}
	
	// Adding, setting, getting
	public void addChild(SubCommand child) {
		this.children.add(child);
	}
	
	public String getCommand() {
		return this.starter;
	}
	
	public Rank getLowestPermNeeded() {
		return this.lowestPermNeeded;
	}
	
	public void regrabPerms() {
		for(int i = 0; i < children.size(); i++)
			children.get(i).regrabPerm();
	}
	
}
