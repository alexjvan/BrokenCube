package com.brokencube.api.chat;

import org.bukkit.ChatColor;

public class ColorReplacer {

	public static String colorize(String replaceMe) {
		return replaceMe
				.replaceAll("&0", ChatColor.BLACK+"")
				.replaceAll("&1", ChatColor.DARK_BLUE+"")
				.replaceAll("&2", ChatColor.DARK_GREEN+"")
				.replaceAll("&3", ChatColor.DARK_AQUA+"")
				.replaceAll("&4", ChatColor.DARK_RED+"")
				.replaceAll("&5", ChatColor.DARK_PURPLE+"")
				.replaceAll("&6", ChatColor.GOLD+"")
				.replaceAll("&7", ChatColor.GRAY+"")
				.replaceAll("&8", ChatColor.DARK_GRAY+"")
				.replaceAll("&9", ChatColor.BLUE+"")
				.replaceAll("&a", ChatColor.GREEN+"")
				.replaceAll("&b", ChatColor.AQUA+"")
				.replaceAll("&c", ChatColor.RED+"")
				.replaceAll("&d", ChatColor.LIGHT_PURPLE+"")
				.replaceAll("&e", ChatColor.YELLOW+"")
				.replaceAll("&f", ChatColor.WHITE+"")
				.replaceAll("&r", ChatColor.RESET+"")
				.replaceAll("&l", ChatColor.BOLD+"")
				.replaceAll("&o", ChatColor.ITALIC+"")
				.replaceAll("&n", ChatColor.UNDERLINE+"")
				.replaceAll("&m", ChatColor.STRIKETHROUGH+"")
				.replaceAll("&k", ChatColor.MAGIC+"");
	}
	
}
