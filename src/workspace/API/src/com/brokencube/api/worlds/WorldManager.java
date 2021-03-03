package com.brokencube.api.worlds;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;

import com.brokencube.api.API;

public class WorldManager {
	private API instance;
	Random rand = new Random();
	
	HashMap<String, World> worlds = new HashMap<>();
	
	public WorldManager(API instance) {
		this.instance = instance;
		List<World> worlds = instance.getServer().getWorlds();
		for(World w : worlds)
			this.worlds.put(w.getName(), w);
	}
	
	public void createWorld(String name, Environment env, WorldType type, boolean structures) {
		createWorld(name, env, type, structures, null, rand.nextLong());
	}
	
	public void createWorld(String name, Environment env, WorldType type, boolean structures, String generator, long seed) {
		WorldCreator wc = new WorldCreator(name);
		wc.environment(env);
		wc.type(type);
		wc.generateStructures(structures);
		wc.generator(generator);
		wc.seed(seed);
		World newWorld = wc.createWorld();
		worlds.put(name, newWorld);
	}
	
	public Location getSpawn(String world) {
		return worlds.get(world).getSpawnLocation();
	}
	
}
