package com.brokencube.civilizations;

import com.brokencube.api.chat.ColorReplacer;

public class Messages {

	// CIVS
	public static String civExists = ColorReplacer.colorize(com.brokencube.api.Messages.error + "A civilization by this name already exists.");
	public static String inCiv = ColorReplacer.colorize("&aYou are already in a civilization.");
	
	// LAND
	public static String landTaken = ColorReplacer.colorize(com.brokencube.api.Messages.error + "This land is either already taken or protected.");
	
	// WORLDS
	public static String worldNoExist = ColorReplacer.colorize(com.brokencube.api.Messages.error + "The Civilizations World Manager can't find the world you are in.");
	
	
}
