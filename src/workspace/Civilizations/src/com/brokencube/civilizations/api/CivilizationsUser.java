package com.brokencube.civilizations.api;

import org.bukkit.entity.Player;

import com.brokencube.api.ranks.Rank;
import com.brokencube.api.user.User;
import com.brokencube.civilizations.Civilizations;
import com.brokencube.civilizations.civilization.Civilization;
import com.brokencube.civilizations.empire.Empire;

public class CivilizationsUser extends User {
	Civilizations civs;
	double balance;
	Civilization civ;
	Empire empire;
	
	public CivilizationsUser(Civilizations civs, Player player, int id, String username, String customName, Rank rank, boolean friendsOpen, int xp, int coins, int gems) {
		super(player, id, username, customName, rank, friendsOpen, xp, coins, gems);
		this.civs = civs;
		this.balance = civs.getEcon().getBalance(this.username);
		// Grab balance
		this.balance = civs.getEcon().getBalance(this.username);
		// Grab civ
		for(Civilization c : civs.getCivManager().civs.values()) {
			if(c.owner == username || c.deputies.contains(username) || c.citizens.contains(username)) {
				civ = c;
				break;
			}
		}
		if(civ != null)
			empire = civs.getEmpiresManager().getByName(civ.empire);
	}
	
	public double getBalance() { return this.balance; }
	
	public void setBalance(double amount) {
		this.balance = amount;
		this.civs.getEcon().setBalance(this.username, amount);
	}
	
	public void addToBalance(double amount) {
		this.balance += amount;
		this.civs.getEcon().addToBalance(this.username, amount);
	}

	public void removeFromBalance(double amount) {
		this.balance -= amount;
		this.civs.getEcon().subtractFromBalance(this.username, amount);
	}
	
	public Empire getEmpire() { return this.empire; }
	
	public void setEmpire(Empire set) { this.empire = set; }
	
	public Civilization getCivilization() { return this.civ; }
	
	public void setCivilization(Civilization set) { this.civ = set; }
	
}
