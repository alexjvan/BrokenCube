package com.brokencube.api.user.listeners;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.brokencube.api.API;
import com.brokencube.api.ranks.Rank;
import com.brokencube.api.server.Database;
import com.brokencube.api.server.events.UserAddedToRegisterEvent;
import com.brokencube.api.user.User;

public class Event_PlayerJoin_DB implements Listener {
	private API instance;

	public Event_PlayerJoin_DB(API instance) {
		this.instance = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Database db = this.instance.getDB();
		// id, username, custom_name, alias, vanished, prefix, suffix, rankid, xp,
		// coins, rubies
		List<String[]> returned = db.getQuery("SELECT * FROM users WHERE username='" + p.getName() + "'", true);
		// USER DOESN'T EXIST || USER DOES EXIST
		if (returned == null || returned.size() == 0) {
			createUser(p);
		} else {
			addUser(p, returned.get(0));
		}
	}

	private void createUser(Player p) {
		Boolean res = this.instance.getDB().insertQuery("INSERT INTO users (" + "username," + "custom_name," + "rankid," + "friendsOpen," + "xp," + "coins," + "rubies) " + "VALUES ('" + p.getName() + "', " + null + ", "
			+ instance.getRM().getRankFromName("User").num + "," + true + "," + 0 + "," + 0 + "," + 0 + ")");
		if (res) {
			String[] data = this.instance.getDB().getQuery("SELECT * FROM users WHERE username='" + p.getName() + "'").get(0);
			addUser(p, data);
			User u = (User) (this.instance.getUR().getExecutorFromUsername(p.getName()));
			u.firstLogin = true;
		} else {
			this.instance.getLogger().log(Level.SEVERE, "ERROR CREATING USER [" + p.getName() + "] UPON FIRST JOIN!");
			this.instance.getLogger().log(Level.SEVERE, "Setting user information to default information.");
			String[] data = { "-2", p.getName(), null, instance.getRM().getRankFromName("User").num + "", "1", "0", "0", "0" };
			addUser(p, data);
		}
	}

	private void addUser(Player p, String[] data) {
		int id = Integer.parseInt(data[0]);
		String username = data[1];
		String customName = data[2];
		Rank r = this.instance.getRM().getRankFromNum(Integer.parseInt(data[3]));
		Boolean friendsOpen = Boolean.parseBoolean(data[4]);
		int xp = Integer.parseInt(data[5]);
		int coins = Integer.parseInt(data[6]);
		int rubies = Integer.parseInt(data[7]);

		User u = new User(p, id, username, customName, r, friendsOpen, xp, coins, rubies);

		this.instance.getUR().addUserToRegister(u);
		UserAddedToRegisterEvent event = new UserAddedToRegisterEvent(u);
		Bukkit.getPluginManager().callEvent(event);
	}

}
