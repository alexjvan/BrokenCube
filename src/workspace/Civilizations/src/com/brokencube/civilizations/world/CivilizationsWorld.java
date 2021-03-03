package com.brokencube.civilizations.world;

import java.util.HashMap;

public class CivilizationsWorld {

	public String worldName;
	
	// y, [x, chunk]
	HashMap<CoordinatePair, CivilizationsWorldChunk> grid = new HashMap<CoordinatePair, CivilizationsWorldChunk>();
	
	public void setChunk(CoordinatePair coord, CivilizationsWorldChunk chunk) {
		HashMap<CoordinatePair, CivilizationsWorldChunk> nu = new HashMap<CoordinatePair, CivilizationsWorldChunk>();
		CoordinatePair[] coords = null;
		grid.keySet().toArray(coords);
		boolean inserted = false;
		for(int findY = 0; findY < coords.length; findY++) {
			CoordinatePair checking = coords[findY];
			if(checking.getY() < coord.getY()) {
				nu.put(checking, grid.get(checking));
			} else if(checking.getY() == coord.getY()) {
				if(checking.getX() < coord.getX()) {
					nu.put(checking, grid.get(checking));
				} else if(checking.getX() == coord.getX()) {
					nu.put(checking, chunk);
					inserted = true;
				} else {
					if(!inserted)
						nu.put(coord, chunk);
					nu.put(checking, grid.get(checking));
				}
			} else {
				if(!inserted)
					nu.put(coord, chunk);
				nu.put(checking, grid.get(checking));
			}
		}
	}
	
	public CoordinatePair getCoordinatePair(int x, int y) {
		CoordinatePair[] coords = null;
		grid.keySet().toArray(coords);
		for(int findY = 0; findY < coords.length; findY++) {
			CoordinatePair checking = coords[findY];
			if(checking.getY() > y)
				return null;
			else if(checking.getY() == y) {
				if(checking.getX() > x)
					return null;
				else if(checking.getX() == x)
					return checking;
			}
		}
		return null;
	}
	
	public CivilizationsWorldChunk getChunk(int x, int y) {
		return getChunk(getCoordinatePair(x, y));
	}
	
	public CivilizationsWorldChunk getChunk(CoordinatePair coord) {
		return grid.get(coord);
	}
	
}
