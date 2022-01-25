package es.xdec0de.blp.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageUtils {

	private final static Pattern HEX_PATTERN = Pattern.compile("#([A-Fa-f0-9]{6})");

	// String utility //

	/**
	 * Applies color to the specified string, with hexadecimal color codes support.
	 * 
	 * @param string The string to color
	 * 
	 * @return The string, colored
	 */
	public static String applyColor(String string) {
		if(string == null)
			return "null";
		char COLOR_CHAR = '\u00A7';
		Matcher matcher = HEX_PATTERN.matcher(string);
		StringBuffer buffer = new StringBuffer(string.length() + 4 * 8);
		while (matcher.find()) {
			String group = matcher.group(1);
			matcher.appendReplacement(buffer, COLOR_CHAR + "x"
					+ COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
					+ COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
					+ COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5));
		}
		return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
	}

	/**
	 * Applies color to the specified list of strings, with hexadecimal color codes support.
	 * 
	 * @param list The string list to color
	 * 
	 * @return The string list, colored
	 */
	public static List<String> applyColor(List<String> list) {
		if(list == null)
			return new LinkedList<String>();
		List<String> res = new LinkedList<String>();
		for(String str : list)
			res.add(applyColor(str));
		return res;
	}

	// Loggers //

	/**
	 * Sends the specified string to the console, if the string is null, "null" will be sent, if the string is empty
	 * , nothing will be done.
	 * 
	 * @param str The string to send
	 */
	public static void log(String str) {
		if(str == null)
			str = "null";
		if(!str.isEmpty())
			Bukkit.getConsoleSender().sendMessage(str);
	}

	/**
	 * Applies color to the specified string and then sends it to the console, if the string is null, "null" will be sent
	 * , if the string is empty, nothing will be done.
	 * 
	 * @param str The string to send
	 */
	public static void logCol(String str) {
		if(!str.isEmpty())
			Bukkit.getConsoleSender().sendMessage(applyColor(str)); // It's not necessary to null check as applyColor does it.
	}
}
