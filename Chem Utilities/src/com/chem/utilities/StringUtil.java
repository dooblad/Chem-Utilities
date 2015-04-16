package com.chem.utilities;

public class StringUtil {
    /**
     * Determines if a String starts with a certain character of the alphabet, and ignores
     * the case while doing so.
     * 
     * @param a
     *            The String being checked for the phrase.
     * @param b
     *            The phrase being checked for in String a.
     * 
     * @return Whether the String a starts with the String b.
     */
    public static boolean startsWithIgnoreCase(String a, String b) {
	return a.toUpperCase().startsWith(b.toUpperCase());
    }
}
