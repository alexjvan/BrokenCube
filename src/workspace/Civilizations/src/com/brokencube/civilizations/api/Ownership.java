package com.brokencube.civilizations.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Ownership {
	protected HashMap<String, Object> fileValues = new HashMap<>();

	public String name;
	public String motd;
	public String tag;
	
	public String owner;
	
	public PrivacyStatus pStatus;
	public List<String> invites;
	
	public double bank;
	public double tax;
	
	public Ownership(String name, String owner) {
		this.name = name;
		this.motd = "";
		this.tag = "";
		
		this.owner = owner;
		
		this.pStatus = PrivacyStatus.Closed;
		this.invites = new ArrayList<>();
		
		this.bank = 0;
		this.tax = 0;
	}
	
}
