package com.brokencube.api.builtconfigs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brokencube.api.API;
import com.brokencube.api.local.ConfigFile;

public class APIConfig {
	private ConfigFile conf;
	
	public BlockProtection blockProtection;
	public Chat chat;
	public Database database;
	public Defaults defaults;
	public List<Object> permissionOverride;
	public Worlds worlds;
	
	public APIConfig(API api) {
		this.conf = new ConfigFile(api);
		blockProtection = new BlockProtection();
		chat = new Chat();
		database = new Database();
		defaults = new Defaults();
		permissionOverride = new ArrayList<>();
		worlds = new Worlds();
		
		load();
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		// Try set
		conf.tryAddValue("bp.allowBreak", false);
		conf.tryAddValue("bp.allowItem", false);
		conf.tryAddValue("bp.allowPlace", false);
		
		conf.tryAddValue("chat.channels", new ArrayList<String>(Arrays.asList("global","staff")));
		conf.tryAddValue("chat.enabled", true);
		conf.tryAddValue("chat.format", "{rankprefix} {username} &r&b> &7{message}");
		
		conf.tryAddValue("db.host", "");
		conf.tryAddValue("db.user", "");
		conf.tryAddValue("db.password", "");

		conf.tryAddValue("defaults.gamemode", 2);

		conf.tryAddValue("permissionOverride", new ArrayList<Object>());
		
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
		
		// Get
		blockProtection.allowBreak = (boolean)conf.get("bp.allowBreak");
		blockProtection.allowItem = (boolean)conf.get("bp.allowItem");
		blockProtection.allowPlace = (boolean)conf.get("bp.allowPlace");
		
		chat.channels = (ArrayList<String>)conf.get("chat.channels");
		chat.enabled = (boolean)conf.get("chat.enabled");
		chat.format = (String)conf.get("chat.format");
		
		database.host = (String)conf.get("db.host");
		database.user = (String)conf.get("db.user");
		database.password = (String)conf.get("db.password");
		
		defaults.gamemode = (int)conf.get("defaults.gamemode");

		permissionOverride = (ArrayList<Object>)conf.get("permissionOverride");
		
		worlds.Default.name = (String)conf.get("worlds.default.name");
		worlds.Default.animalSpawn = (boolean)conf.get("worlds.default.animalSpawn");
		worlds.Default.environment = (String)conf.get("worlds.default.environment");
		worlds.Default.explosions = (boolean)conf.get("worlds.default.explosions");
		worlds.Default.firespread = (boolean)conf.get("worlds.default.firespread");
		worlds.Default.gamemode = (int)conf.get("worlds.default.gamemode");
		worlds.Default.generator = (String)conf.get("worlds.default.generator");
		worlds.Default.mobSpawn = (boolean)conf.get("worlds.default.mobSpawn");
		worlds.Default.pvp = (boolean)conf.get("worlds.default.pvp");
		worlds.Default.structures = (boolean)conf.get("worlds.default.structures");
		worlds.Default.type = (String)conf.get("worlds.default.type");
	}
	
	public void save() {
		// Set
		conf.set("bp.allowBreak", blockProtection.allowBreak);
		conf.set("bp.allowItem", blockProtection.allowItem);
		conf.set("bp.allowPlace", blockProtection.allowPlace);
		
		conf.set("chat.channels", chat.channels);
		conf.set("chat.enabled", chat.enabled);
		conf.set("chat.format", chat.format);
		
		conf.set("db.host", database.host);
		conf.set("db.user", database.user);
		conf.set("db.password", database.password);

		conf.set("defaults.gamemode", defaults.gamemode);

		conf.set("permissionOverride", permissionOverride);
		
		conf.set("worlds.default.name", worlds.Default.name);
		conf.set("worlds.default.animalSpawn", worlds.Default.animalSpawn);
		conf.set("worlds.default.environment", worlds.Default.environment);
		conf.set("worlds.default.explosions", worlds.Default.explosions);
		conf.set("worlds.default.firespread", worlds.Default.firespread);
		conf.set("worlds.default.gamemode", worlds.Default.gamemode);
		conf.set("worlds.default.generator", worlds.Default.generator);
		conf.set("worlds.default.mobSpawn", worlds.Default.mobSpawn);
		conf.set("worlds.default.pvp", worlds.Default.pvp);
		conf.set("worlds.default.structures", worlds.Default.structures);
		conf.set("worlds.default.type", worlds.Default.type);
		
		// Save
		conf.save();
	}
	
	public class BlockProtection {
		public boolean allowBreak;
		public boolean allowItem;
		public boolean allowPlace;
	}
	
	public class Chat {
		public List<String> channels;
		public boolean enabled;
		public String format;
	}
	
	public class Database {
		public String host;
		public String user;
		public String password;
	}

	public class Defaults {
		public int gamemode;
	}
	
	public class Worlds {
		public World Default;
		
		public Worlds() {
			Default = new World();
		}
		
		public class World {
			public String name;
			public boolean animalSpawn;
			public String environment;
			public boolean explosions;
			public boolean firespread;
			public int gamemode;
			public String generator;
			public boolean mobSpawn;
			public boolean pvp;
			public boolean structures;
			public String type;
		}
	}
	
}
