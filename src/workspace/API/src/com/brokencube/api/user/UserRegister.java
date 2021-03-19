package com.brokencube.api.user;

import java.util.ArrayList;
import java.util.List;

import com.brokencube.api.API;

public class UserRegister<T extends User>  {
	private List<T> users  = new ArrayList<T>();
	private Console console;
	
	public UserRegister(API instance) {
		this.console = new Console(instance);
	}
	
	public void addUserToRegister(T user) {
		if(users.size() != 0)
			binSearchAdd(user, 0, users.size() - 1);
		else
			users.add(user);
	}
	
	private void binSearchAdd(T u, int left, int right) {
		if(left < right) {
			int mid = left + right / 2;
			if(users.get(mid).username.equalsIgnoreCase(u.username))
				return;
			
			if(users.get(mid).username.compareTo(u.username) > 0)
				binSearchAdd(u, mid + 1, right);
			else
				binSearchAdd(u, left, mid - 1);
		} else {
			if(users.get(left).username.compareTo(u.username) > 0) {
				users.add(left + 1, u);
			} else {
				users.add(left, u);
			}
		}
	}
	
	public List<T> getAllUsers() {
		return users;
	}
	
	public Executor getExecutorFromUsername(String username) {
		if(username.equalsIgnoreCase("CONSOLE")) {
			return console;
		}
		if(users.size() != 0)
			return binSearchUsers(username, 0, users.size() - 1);
		else
			return null;
	}
	
	private User binSearchUsers(String username, int left, int right) {
		if(left <= right) {
			int mid = left + right / 2;
			if(users.get(mid).username.equalsIgnoreCase(username))
				return users.get(mid);
			
			if(users.get(mid).username.compareTo(username) > 0)
				return binSearchUsers(username, mid + 1, right);
			return binSearchUsers(username, left, mid - 1);
		}
		return null;
	}
	
	public Console getConsole() {
		return console;
	}
	
	public void sendMessageAll(String message, boolean color) {
		if(color)
			for(int i = 0; i < users.size(); i++) {
				users.get(i).sendMessage(message);
			}
		else
			for(int i = 0; i < users.size(); i++) {
				users.get(i).sendMessageNoColor(message);
			}
	}
	
}
