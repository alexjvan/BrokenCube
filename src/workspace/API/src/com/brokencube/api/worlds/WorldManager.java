package com.brokencube.api.worlds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;

import com.brokencube.api.API;

public class WorldManager {
	protected API instance;
	private Random rand = new Random();
	
	private List<World> worlds = new ArrayList<>();
	
	public WorldManager(API instance) {
		this.instance = instance;
		List<World> worlds = instance.getServer().getWorlds();
		for(World w : worlds)
			this.worlds.add(w);
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
		worlds.add(newWorld);
	}
	
	public World getByName(String name) {
		for(World w : worlds)
			if(w.getName().equalsIgnoreCase(name))
				return w;
		return null;
	}
	
	public Location getSpawn(String world) {
		return getByName(world).getSpawnLocation();
	}
	
	public World[] getAllWorlds() {
		return worlds.toArray(new World[worlds.size()]);
	}
	
}
