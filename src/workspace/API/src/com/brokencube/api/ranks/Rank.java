package com.brokencube.api.ranks;

import java.util.ArrayList;
import java.util.List;

public class Rank {
	public int num;
	public String name;
	public String prefix;
	public int booster;
	public List<Rank> inherits = new ArrayList<>();
	public List<Rank> inheritedBy = new ArrayList<>();
	
	public List<String> perms = new ArrayList<>();
	
	public Rank(int num, String name, String prefix, int booster) {
		this.num = num;
		this.name = name;
		this.prefix = prefix;
		this.booster = booster;
	}
	
	public boolean inherits(Rank other) {
		if(name.equals(other.name))
			return true;
		for(int i = 0; i < inherits.size(); i++) {
			if(inherits.get(i).inherits(other))
				return true;
		}
		return false;
	}
	
	public boolean isInherited(Rank other) {
		for(int i = 0; i < inheritedBy.size(); i++) {
			if(inheritedBy.get(i).name.equalsIgnoreCase(other.name))
				return true;
			boolean internal = inheritedBy.get(i).isInherited(other);
			if(internal)
				return true;
		}
		return false;
	}
	
	public boolean hasPermission(String permission) {
		for(int i = 0; i < perms.size(); i++)
			if(perms.get(i).equals(permission))
				return true;
		for(int i = 0 ; i < inherits.size(); i++)
			if(inherits.get(i).hasPermission(permission)) 
				return true;
		return false;
	}
	
}
