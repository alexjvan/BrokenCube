package com.brokencube.civilizations.world;

public class CoordinatePair {

	public int x;
	public int y;
	
	public CoordinatePair() {
		this(0, 0);
	}
	
	public CoordinatePair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isXNegative() {
		return x < 0;
	}
	
	public boolean isYNegative() {
		return y < 0;
	}
	
}
