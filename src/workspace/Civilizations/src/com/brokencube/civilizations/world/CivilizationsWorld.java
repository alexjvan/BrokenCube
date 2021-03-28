package com.brokencube.civilizations.world;

import java.util.List;

import com.brokencube.api.API;

public class CivilizationsWorld {
	private API api;

	CivilizationsWorldChunk[][] grid = new CivilizationsWorldChunk[1][1];
	// Grid will work with a ChunkData in a 2d array
	// Each chunk (16x16 blocks), will be placed as a position in the array
	// A position in the array will be the Chunk's y position - the y offset (Same for x)
	// For example, if the first chunk that there is data for is 3, and you are grabbing the data
	//		for chunk 9, the position in the array for that chunk would be 9-3=6
	// As another example, if the first chunk that there is data for is -18, and you are grabbing
	//		the data for chunk 12, the position in the array would be 12-(-18)=12+18=30
	int xoffset = 0;
	int yoffset = 0;
	int xMax = 1;
	int yMax = 1;
	
	public CivilizationsWorld(API api) {
		loadWorld();
	}
	
	private void loadWorld() {
		api.getDB().getQuery("");
	}
	
	private void expandGrid() {
		CivilizationsWorldChunk[][] copy = grid.clone();
		grid = new CivilizationsWorldChunk[yMax - yoffset][xMax - xoffset];
		for(int y = 0; y < copy.length; y++)
			for(int x = 0; x < copy[y].length; x++) {
				CivilizationsWorldChunk cur = copy[y][x];
				grid[cur.y - yoffset][cur.x - xoffset] = cur;
			}
	}
	
}
