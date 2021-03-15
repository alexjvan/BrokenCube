package com.brokencube.api.economy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brokencube.api.API;

public class Economy {
	private API api;
	private String holder;
	
	private Map<Integer, Integer> original;
	private Map<Integer, Integer> balances = new HashMap<Integer, Integer>();
	
	public Economy(API api, String holder) {
		this.holder = holder;
		List<String[]> records = api.getDB().getQuery("GET * FROM "+holder+"_economy");
		if(records == null) {
			// Need to create table
			api.getDB().insertQuery("CREATE TABLE "+holder+"_economy (userID int, balance int);");
		} else {
			for(String[] record : records) {
				int user = Integer.parseInt(record[0]);
				int bal = Integer.parseInt(record[1]);
				balances.put(user, bal);
			}
		}
		original = balances;
	}
	
	public void updateDB() {
		for(Integer key : balances.keySet()) {
			if(!original.containsKey(key)) {
				// Not in the database
				api.getDB().insertQuery("INSERT INTO "+holder+"_economy VALUES ("+key+", "+balances.get(key)+");");
			} else if(balances.get(key) != original.get(key)) {
				// Different value of whats in the database
				api.getDB().updateQuery("UPDATE "+holder+"_economy SET balance=" + balances.get(key) + " WHERE userID="+key);
			}
		}
		original = balances;
	}
	
	public int getBalance(int userID) {
		if(!balances.containsKey(userID))
			balances.put(userID, 0);
		return balances.get(userID);
	}
	
	public void setBalance(int userID, int balance) {
		this.balances.put(userID, balance);
	}
	
	public void addToBalance(int userID, int amount) {
		this.balances.put(userID, this.balances.get(userID) + amount);
	}
	
	public void subtractFromBalance(int userID, int amount) {
		this.balances.put(userID, this.balances.get(userID) - amount);
	}
}
