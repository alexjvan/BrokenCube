package com.brokencube.api;

import org.bukkit.plugin.java.JavaPlugin;

import com.brokencube.api.blockprotection.Event_BlockBreakEvent_BlockProtection;
import com.brokencube.api.blockprotection.Event_BlockPlaceEvent_BlockProtection;
import com.brokencube.api.blockprotection.Event_ItemEvents_BlockProtection;
import com.brokencube.api.blockprotection.commands.Command_BlockProtection;
import com.brokencube.api.builtconfigs.APIConfig;
import com.brokencube.api.chat.listeners.Event_AsyncPlayerChatEvent_PlayerChatFormatter;
import com.brokencube.api.command.CommandRegister;
import com.brokencube.api.command.commands.Command_Commands;
import com.brokencube.api.command.listeners.Event_PlayerCommandPreProcess_AltCmdHandler;
import com.brokencube.api.command.listeners.Event_ServerCommand_AltCmdHandler;
import com.brokencube.api.permissions.PermissionsRegister;
import com.brokencube.api.permissions.commands.Command_Perms;
import com.brokencube.api.plugins.PluginRegister;
import com.brokencube.api.ranks.RankManager;
import com.brokencube.api.ranks.commands.Command_Rank;
import com.brokencube.api.server.Database;
import com.brokencube.api.server.commands.Command_DB;
import com.brokencube.api.user.User;
import com.brokencube.api.user.UserRegister;
import com.brokencube.api.user.listeners.Event_PlayerJoin_DB;
import com.brokencube.api.worlds.WorldManager;

public class API extends JavaPlugin {
	private Database db;
	@SuppressWarnings("rawtypes")
	private UserRegister ur;
	private PermissionsRegister pr;
	private CommandRegister cr;
	private RankManager rm;
	private PluginRegister plug;
	private WorldManager wm;
	
	APIConfig conf;
	
	public static API instance;
	
	@Override
	public void onLoad() {
		instance = this;
		
		plug = new PluginRegister();
		plug.registerPlugin(this, "API");
		
		// Create conf here to get DB access
		conf = new APIConfig(this);
		
		// DATABASE NEEDS TO BE FIRST
		db = new Database(this);
		
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
		// Check if this is null here, if not a different type of user is being used for the server (ex: CivsUser)
		if(ur == null)
			ur = new UserRegister<User>(this);
		rm.console = ur.getConsole();
		
		// World Manager
		wm = new WorldManager(this);
		
		// ---==Commands==---
		cr.registerCommand(new Command_BlockProtection(this));
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
	
	public APIConfig getConf() {
		return conf;
	}
	
	public Database getDB() {
		return this.db;
	}
	
	public void setUR(@SuppressWarnings("rawtypes") UserRegister set) {
		this.ur = set;
	}
	
	@SuppressWarnings("rawtypes")
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
