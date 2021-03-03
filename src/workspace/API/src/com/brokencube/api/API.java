package com.brokencube.api;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.plugin.java.JavaPlugin;

import com.brokencube.api.blockprotection.Event_BlockBreakEvent_BlockProtection;
import com.brokencube.api.blockprotection.Event_BlockPlaceEvent_BlockProtection;
import com.brokencube.api.blockprotection.Event_ItemEvents_BlockProtection;
import com.brokencube.api.chat.listeners.Event_AsyncPlayerChatEvent_PlayerChatFormatter;
import com.brokencube.api.command.CommandRegister;
import com.brokencube.api.command.commands.Command_Commands;
import com.brokencube.api.command.listeners.Event_PlayerCommandPreProcess_AltCmdHandler;
import com.brokencube.api.command.listeners.Event_ServerCommand_AltCmdHandler;
import com.brokencube.api.local.ConfigFile;
import com.brokencube.api.permissions.PermissionsRegister;
import com.brokencube.api.permissions.commands.Command_Perms;
import com.brokencube.api.plugins.PluginRegister;
import com.brokencube.api.ranks.RankManager;
import com.brokencube.api.ranks.commands.Command_Rank;
import com.brokencube.api.server.Database;
import com.brokencube.api.server.commands.Command_DB;
import com.brokencube.api.user.Console;
import com.brokencube.api.user.UserRegister;
import com.brokencube.api.user.listeners.Event_PlayerJoin_DB;
import com.brokencube.api.worlds.WorldManager;

public class API extends JavaPlugin {
	private Database db;
	private UserRegister ur;
	private PermissionsRegister pr;
	private CommandRegister cr;
	private RankManager rm;
	private PluginRegister plug;
	private WorldManager wm;
	
	ConfigFile conf;
	
	public static API instance;
	
	@Override
	public void onLoad() {
		instance = this;
		
		plug = new PluginRegister();
		plug.registerPlugin(this, "API");
		
		// Create conf here to get DB access
		conf = new ConfigFile(this);
		
		conf.tryAddValue("db.host", "");
		conf.tryAddValue("db.user", "");
		conf.tryAddValue("db.password", "");
		
		// DATABASE NEEDS TO BE FIRST
		db = new Database(this);
		
		conf.tryAddValue("worlds.default.name", "World");
		conf.tryAddValue("worlds.default.animalSpawn", true);
		conf.tryAddValue("worlds.default.environment", "Overworld");
		conf.tryAddValue("worlds.default.explosions", false);
		conf.tryAddValue("worlds.default.firespread", true);
		conf.tryAddValue("worlds.default.gamemode", 2);
		conf.tryAddValue("worlds.default.generator", "natural");
		conf.tryAddValue("worlds.default.mobSpawn", true);
		conf.tryAddValue("worlds.default.pvp", false);
		conf.tryAddValue("worlds.default.structures", false);
		conf.tryAddValue("worlds.default.type", "Normal");
		
		// RANKS NEED TO BE BEFORE USERS
		// RANKS ALSO NEEDS TO BE BEFORE OTHER THINGS LIKE COMMAND AND PERMISSION REGISTER TO LOAD THE RANKS
		rm = new RankManager(this);
	}
	
	@Override
	public void onEnable() {
		// PERMISSION HANDLER NEEDS TO GO BEFORE LOADING COMMANDS
		// COMMANDS NEED TO GO BEFORE PERMISSIONS LOADING
		// CONFIG NEEDS TO BE AFTER PERMISSIONS
		cr = new CommandRegister();
		pr = new PermissionsRegister(this);
		// do this here cuz throw error that rm doesn't exist otherwise
		rm.console = new Console(this);
		ur = new UserRegister(this);
		
		// CONFIG
		conf.tryAddValue("bp.allowBreak", false);
		conf.tryAddValue("bp.allowItem", false);
		conf.tryAddValue("bp.allowPlace", false);
		
		conf.tryAddValue("chat.channels", new ArrayList<String>(Arrays.asList("global","staff")));
		conf.tryAddValue("chat.enabled", true);
		conf.tryAddValue("chat.format", "{rankprefix} {username} &r&b> &7{message}");
		
		conf.tryAddValue("defaults.gamemode", 2);
		
		conf.tryAddValue("permissionOverride", new ArrayList<Object>());
		
		// World Manager
		wm = new WorldManager(this);
		
		// ---==Commands==---
		cr.registerCommand(new Command_Commands(this));
		cr.registerCommand(new Command_DB(this));
		cr.registerCommand(new Command_Perms(this));
		cr.registerCommand(new Command_Rank(this));
		
		// ---==EVENTS==---
		// Block Protection
		getServer().getPluginManager().registerEvents(new Event_BlockBreakEvent_BlockProtection(this), this);
		getServer().getPluginManager().registerEvents(new Event_BlockPlaceEvent_BlockProtection(this), this);
		getServer().getPluginManager().registerEvents(new Event_ItemEvents_BlockProtection(this), this);
		// Chat
		getServer().getPluginManager().registerEvents(new Event_AsyncPlayerChatEvent_PlayerChatFormatter(this), this);
		// Commands
		getServer().getPluginManager().registerEvents(new Event_PlayerCommandPreProcess_AltCmdHandler(this), this);
		getServer().getPluginManager().registerEvents(new Event_ServerCommand_AltCmdHandler(this), this);
		// User
		getServer().getPluginManager().registerEvents(new Event_PlayerJoin_DB(this), this);
	}
	
	@Override
	public void onDisable() {
		conf.save();
	}
	
	public CommandRegister getCR() {
		return this.cr;
	}
	
	public PermissionsRegister getPR() {
		return this.pr;
	}
	
	public RankManager getRM() {
		return this.rm;
	}
	
	public ConfigFile getConf() {
		return conf;
	}
	
	public Database getDB() {
		return this.db;
	}
	
	public UserRegister getUR() {
		return this.ur;
	}

	public PluginRegister getPlug() {
		return this.plug;
	}
	
	public WorldManager getWM() {
		return this.wm;
	}
	
	public void setAltWorldManager(WorldManager wm) {
		this.wm = wm;
	}
}
