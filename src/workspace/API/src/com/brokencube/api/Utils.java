package com.brokencube.api;

public class Utils {
	public static boolean isInt(String check) {
		if(check == null)
			return false;
		try {
			Integer.parseInt(check);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static boolean isLetter(char check) {
	    return Character.isLetter(check);
	}
}
