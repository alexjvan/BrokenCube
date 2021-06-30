package com.brokencube.api;

import com.brokencube.api.chat.ColorReplacer;

public class Messages {
	
	// GENERIC
	public static String error = ColorReplacer.colorize("&c");
	public static String general = ColorReplacer.colorize("&6");
	public static String success = ColorReplacer.colorize("&a");
	
	// COMMANDS
	public static String nocmd = ColorReplacer.colorize(error+"This command does not exist.");
	public static String wrongArgs = ColorReplacer.colorize(error+"The arguments given were wrong. Please check the command usage.");
	
	// CONSOLE
	public static String noconsole = ColorReplacer.colorize(error+"&lThere is no console implementation for this command.");
	
	// ENTER/EXIT
	public static String join = ColorReplacer.colorize("&a&l[&2&l+&a&l] &r&6{username}");
	public static String firstTime = ColorReplacer.colorize("&aPlease welcome {username} to the server!");
	public static String leave = ColorReplacer.colorize("&c&l[&4&l-&c&l] &r&6{username}");
	
	// MONEY
	public static String nomoney = ColorReplacer.colorize("&cYou don't have enough money to do this.");
	
	// PERMS
	public static String noperms = ColorReplacer.colorize(error+"&lYou don't have the permissions to do this.");
	public static String nopermsbut = ColorReplacer.colorize(error+"&lYou don't have the permissions to do this, try using the help command.");
	
}
