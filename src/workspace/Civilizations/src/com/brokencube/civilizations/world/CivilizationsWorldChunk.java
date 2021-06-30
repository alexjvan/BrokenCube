package com.brokencube.civilizations.world;

import com.brokencube.civilizations.civilization.Civilization;

public class CivilizationsWorldChunk {
	
	public LandType landType = LandType.Wilderness;
	public Civilization ownedBy;
	
	public int x;
	public int y;
	
	public CivilizationsWorldChunk(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public CivilizationsWorldChunk(int x, int y, LandType type) {
		this(x, y);
		this.landType = type;
	}

}
