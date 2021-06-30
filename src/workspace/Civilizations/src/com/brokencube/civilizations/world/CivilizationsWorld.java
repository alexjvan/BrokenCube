package com.brokencube.civilizations.world;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

import com.brokencube.api.API;
import com.brokencube.civilizations.builtconfigs.CivWorldConfig;

public class CivilizationsWorld {
	private API api;
	private String name;
	public World world;
	
	private CivWorldConfig config;
	
	CivilizationsWorldChunk[][] grid;
	// Grid will work with a ChunkData in a 2d array
	// Each chunk (16x16 blocks), will be placed as a position in the array
	// A position in the array will be the Chunk's y position - the y offset (Same for x)
	// For example, if the first chunk that there is data for is 3, and you are grabbing the data
	//		for chunk 9, the position in the array for that chunk would be 9-3=6
	// As another example, if the first chunk that there is data for is -18, and you are grabbing
	//		the data for chunk 12, the position in the array would be 12-(-18)=12+18=30
	int xoffset;
	int yoffset;
	int xMax;
	int yMax;
	
	public CivilizationsWorld(API api, World world) {
		this.api = api;
		this.world = world;
		this.name = world.getName();
		
		config = new CivWorldConfig(api, name);
		
		loadWorld();
	}
	
	private void loadWorld() {
		xoffset = config.xoffset;
		yoffset = config.yoffset;
		xMax = config.xMax;
		yMax = config.yMax;
		expandGrid();
		for(CivilizationsWorldChunk chunk : config.chunkData)
			grid[chunk.y - yoffset][chunk.x - xoffset] = chunk;
	}
	
	public void save() {
		// Set values
		List<CivilizationsWorldChunk> chunkData = new ArrayList<>();
		for(CivilizationsWorldChunk[] y : grid)
			for(CivilizationsWorldChunk chunk : y)
				if(chunk.landType != LandType.Wilderness)
					chunkData.add(chunk);
		config.chunkData = chunkData;
		config.xoffset = xoffset;
		config.yoffset = yoffset;
		config.xMax = xMax;
		config.yMax = yMax;
		// Save config
		this.config.save();
	}
	
	private void expandGrid() {
		if(grid == null) {
			grid = new CivilizationsWorldChunk[yMax - yoffset][xMax - xoffset];
			return;
		}
		CivilizationsWorldChunk[][] copy = grid.clone();
		for(CivilizationsWorldChunk[] y : copy)
			for(CivilizationsWorldChunk chunk : y)
				grid[chunk.y - yoffset][chunk.x - xoffset] = chunk;
	}
	
	private CivilizationsWorldChunk getChunkAt(int x, int y) {
		int xChunk = x / 16;
		int yChunk = y / 16;
		return getChunk(xChunk, yChunk);
	}
	
	public CivilizationsWorldChunk getChunk(Location loc) {
		return getChunkAt((int)loc.getX(), (int)loc.getZ());
	}
	
	public CivilizationsWorldChunk getChunk(int xChunk, int yChunk) {
		if(xChunk < xoffset || yChunk < yoffset || xChunk > xMax - xoffset || yChunk > yMax - yoffset)
			return new CivilizationsWorldChunk(xChunk, yChunk, LandType.Wilderness);
		return grid[yChunk - yoffset][xChunk - xoffset];
	}
	
	public void setChunkData(CivilizationsWorldChunk chunk) {
		this.setChunkData(chunk.x, chunk.y, chunk);
	}
	
	public void setChunkData(int xChunk, int yChunk, CivilizationsWorldChunk chunk) {
		if(xChunk < xoffset || yChunk < yoffset || xChunk > xMax - xoffset || yChunk > yMax - yoffset) {
			if(xChunk < xoffset) {
				xoffset = xChunk;
			} else if(xChunk > xMax - xoffset) {
				xMax = xChunk;
			}
			if(yChunk < yoffset) {
				yoffset = xChunk;
			} else if(yChunk > yMax - yoffset) {
				yMax = yChunk;
			}
			expandGrid();
		}
		grid[yChunk - yoffset][xChunk - xoffset] = chunk;
	}
	
}
