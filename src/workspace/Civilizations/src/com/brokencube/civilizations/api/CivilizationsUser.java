package com.brokencube.civilizations.api;

import org.bukkit.entity.Player;

import com.brokencube.api.ranks.Rank;
import com.brokencube.api.user.User;
import com.brokencube.civilizations.Civilizations;
import com.brokencube.civilizations.civilization.Civilization;

public class CivilizationsUser extends User {
	Civilizations civs;
	int balance;
	Civilization civ;
	
	public CivilizationsUser(Civilizations civs, Player player, int id, String username, String customName, Rank rank, boolean friendsOpen, int xp, int coins, int gems) {
		super(player, id, username, customName, rank, friendsOpen, xp, coins, gems);
		this.civs = civs;
		this.balance = civs.getEcon().getBalance(id);
		// Grab civ
	}
	
	public int getBalance() { return this.balance; }
	
	public void setBalance(int amount) {
		this.balance = amount;
		this.civs.getEcon().setBalance(id, amount);
	}
	
	public void addToBalance(int amount) {
		this.balance += amount;
		this.civs.getEcon().addToBalance(id, amount);
	}

	public void removeFromBalance(int amount) {
		this.balance -= amount;
		this.civs.getEcon().subtractFromBalance(id, amount);
	}
	
}
