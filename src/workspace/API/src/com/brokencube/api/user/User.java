package com.brokencube.api.user;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.brokencube.api.chat.ColorReplacer;
import com.brokencube.api.ranks.Rank;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class User extends Executor {
	
	public int id;
	public String username;
	public String customName;
	public boolean friendsOpen;
	public int xp;
	public int coins;
	public int gems;
	
	public List<String> additionalPermissions = new ArrayList<>();
	
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
		
		getPlayer().setPlayerListName(ColorReplacer.colorize(rank.prefix+rank.name));
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
	
	public Location getLocation() {
		return getPlayer().getLocation();
	}
	
	public void sendActionBarMessage(String message) {
		getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}
	
	public void sendTitle(String title, String subtitle) {
		getPlayer().sendTitle(title, subtitle, 10, 70, 20);
	}
	
	public World getWorld() { 
		return getPlayer().getWorld();
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
