package com.brokencube.api.user;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.brokencube.api.ranks.Rank;

public class User extends Executor {
	
	public int id;
	public String username;
	public String customName;
	public boolean friendsOpen;
	public int xp;
	public int coins;
	public int gems;
	
	public List<String> additionalPermissions = new ArrayList<String>();
	
	public boolean firstLogin = false;
	
	public User lastMessaged = null;
	
	public User(Player player, int id, String username, String customName, Rank rank, boolean friendsOpen, int xp, int coins, int gems) {
		super(player);
		this.username = username;
		this.customName = customName;
		this.rank = rank;
		this.friendsOpen = friendsOpen;
		this.xp = xp;
		this.coins = coins;
		this.gems = gems;
	}
	
	public Player getPlayer() {
		return (Player)executor;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public String getCustomName() {
		if(this.customName == null)
			return this.username;
		return this.customName;
	}
	
	@Override
	public boolean isUser() {
		return true;
	}
	
	@Override
	public boolean isConsole() {
		return false;
	}
	
	public String getBuiltName() {
		return this.getCustomName();
	}

	@Override
	public boolean hasPermission(String perm) {
		if(rank.hasPermission(perm))
			return true;
		else if(additionalPermissions.contains(perm))
			return true;
		return false;		
	}
	
}
