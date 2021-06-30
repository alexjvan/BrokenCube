package com.brokencube.civilizations.civilization;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import com.brokencube.civilizations.api.Ownership;
import com.brokencube.civilizations.world.CivilizationsWorldChunk;

public class Civilization extends Ownership {
	/* Ownership
	 * String name
	 * String motd
	 * String tag
	 */
	
	/* Ownership
	 * String owner
	 */
	public List<String> deputies;
	public List<String> citizens;
	
	/* Ownership
	 * PrivacyStatus pStatus
	 * List<String> invites
	 */
	
	/* Ownership
	 * double bank
	 * double tax
	 */
	
	public double plotTax;
	public double shopTax;
	public double embassyTax;
	
	public double plotPrice;
	public double shopPrice;
	public double embassyPrice;
	
	public int landLimit;
	public int bonusPlots;
	
	public Location spawn;
	public CivilizationsWorldChunk home;
	public List<CivilizationsWorldChunk> plots;
	public List<CivilizationsWorldChunk> outposts;

	public Civilization(String name, String owner, boolean newCiv) {
		super(name, owner);
		plotTax = 0;	
		shopTax = 0;
		embassyTax = 0;
		
		plotPrice = 0;
		shopPrice = 0;
		embassyPrice = 0;

		landLimit = 5;
		bonusPlots = 0;
		
		plots = new ArrayList<>();
		outposts = new ArrayList<>();
		
		if(!newCiv)
			loadCiv();
	}
	
	private void loadCiv() {
		// TODO
	}
}
