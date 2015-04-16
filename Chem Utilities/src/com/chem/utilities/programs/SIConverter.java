package com.chem.utilities.programs;

import java.util.Scanner;

import com.chem.utilities.Main;
import com.chem.utilities.StringUtil;

public class SIConverter implements Program {
    private Scanner console;
    
    public SIConverter(Scanner console) {
	this.console = console;
    }

    public void run() {
	boolean again = true;
	
	System.out.println("This program converts SI units between");
	System.out.println("different SI prefixes. Scientific notation");
	System.out.println("is supported in this example format: 1.0 * 10^3");
	while (again) {
	    System.out.print("Scientific notation (Y or N)? ");
	    boolean sciNotation = startsWithIgnoreCase(console.next(), "y");
	    System.out.print("What's the number? ");
	    double number;
	    if (sciNotation) {
		double mantissa = console.nextDouble();
		console.next();
		int exponent = parseScientificNotation(console.next());
		number = mantissa * Math.pow(10, exponent);
	    } else {
		number = console.nextDouble();
	    }

	    System.out.print("What's the prefix? ");
	    int exponent1 = parsePrefix(console.next());

	    System.out.print("What prefix do you want it at? ");
	    int exponent2 = parsePrefix(console.next());

	    number *= Math.pow(10, exponent1 - exponent2);

	    System.out.println("Your number is " + number);

	    System.out.println();
	    System.out.print("Another conversion (Y or N)? ");
	    again = StringUtil.startsWithIgnoreCase(console.next(), "Y");
	    System.out.println();
	}
	Main.currentProgram = new Menu(console);
    }

    private static int parsePrefix(String input) {
	if (input.equals("da"))
	    return 1;
	else if (input.equals("h"))
	    return 2;
	else if (input.equals("k"))
	    return 3;
	else if (input.equals("M"))
	    return 6;
	else if (input.equals("G"))
	    return 9;
	else if (input.equals("T"))
	    return 12;
	else if (input.equals("P"))
	    return 15;
	else if (input.equals("E"))
	    return 18;
	else if (input.equals("Z"))
	    return 21;
	else if (input.equals("Y"))
	    return 24;

	if (input.equals("d"))
	    return -1;
	else if (input.equals("c"))
	    return -2;
	else if (input.equals("m"))
	    return -3;
	else if (input.equals("micro"))
	    return -6;
	else if (input.equals("n"))
	    return -9;
	else if (input.equals("p"))
	    return -12;
	else if (input.equals("f"))
	    return -15;
	else if (input.equals("a"))
	    return -18;
	else if (input.equals("z"))
	    return -21;
	else if (input.equals("y"))
	    return -24;

	return 0;
    }

    private static int parseScientificNotation(String input) {
	int exponent = 0;
	for (int i = 0; i < input.length(); i++) {
	    if (input.charAt(i) == '^') {
		exponent = Integer.parseInt(input.substring(i + 1));
		break;
	    }
	}
	return exponent;
    }

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
