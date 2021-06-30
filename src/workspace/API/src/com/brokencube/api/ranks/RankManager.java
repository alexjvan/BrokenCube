package com.brokencube.api.ranks;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.user.Console;

public class RankManager {
	private API instance;
	public Console console;
	public List<Rank> ranks = new ArrayList<>();
	
	public RankManager(API instance) {
		this.instance = instance;
		grabRanks();
		grabInheritance();
	}
	
	private void grabRanks() {
		List<String[]> results = instance.getDB().getQuery("SELECT id, name, prefix, booster FROM ranks");
		for(int i = 0; i < results.size(); i++) {
			String[] cur = results.get(i);
			Rank newRank = new Rank(Integer.parseInt(cur[0]), cur[1], cur[2], Integer.parseInt(cur[3]));
			ranks.add(newRank);
		}
	}
	
	private void grabInheritance() {
		// id, rankid, inheritedid
		List<String[]> results = instance.getDB().getQuery("SELECT rankid, inheritsid FROM rank_inheritance");
		for(int i = 0; i < results.size(); i++) {
			String[] cur = results.get(i);
			Rank obj = getRankFromNum(Integer.parseInt(cur[0]));
			Rank inhs = getRankFromNum(Integer.parseInt(cur[1]));
			obj.inherits.add(inhs);
			inhs.inheritedBy.add(obj);
		}
	}
	
	public Rank getRankFromName(String name) {
		for(int i = 0; i < ranks.size(); i++)
			if(ranks.get(i).name.equals(name))
				return ranks.get(i);
		return null;
	}
	
	public Rank getRankFromNum(int num) {
		for(int i = 0; i < ranks.size(); i++)
			if(ranks.get(i).num == num)
				return ranks.get(i);
		return null;
	}
}
