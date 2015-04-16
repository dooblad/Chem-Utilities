package com.chem.utilities.programs;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

import com.chem.utilities.Main;
import com.chem.utilities.StringUtil;

public class MolarMassCalculator implements Program {
    private Scanner console;

    public MolarMassCalculator(Scanner console) {
	this.console = console;
    }

    /**
     * Post: Executes this program within the current Chem Utilities context.
     */
    public void run() {
	// Keeps track of whether the user wants to continue calculating.
	boolean again = true;

	printIntro();
	while (again) {
	    String formula = getFormula();

	    double result = hundredthsRound(parseFormula(formula));
	    pasteToClipboard(String.valueOf(result));

	    System.out.println("Molar mass is " + result
		    + " (the result has been pasted to your clipboard).");
	    System.out.println();

	    System.out.print("Find number of moles (Y or N)? ");
	    boolean findMoles = StringUtil.startsWithIgnoreCase(console.nextLine(), "Y");
	    if (findMoles) {
		System.out.print("Mass of the compound (in g)? ");
		double moles = console.nextDouble() / result;
		pasteToClipboard(String.valueOf(moles));
		System.out
			.println("There are "
				+ moles
				+ " moles of the given compound (the result has been pasted to your clipboard).");
	    }
	    System.out.print("Another calculation (Y or N)? ");
	    again = StringUtil.startsWithIgnoreCase(console.next(), "Y");
	    System.out.println();
	}
	Main.currentProgram = new Menu(console);
    }

    /**
     * Post: Returns the molar mass of the interpreted "formula".
     */
    private static double parseFormula(String formula) {
	double result = 0.0;
	// Used for calculation of elements with subscripts after them.
	double last = 0.0;
	for (int i = 0; i < formula.length(); /* No consistent increment */) {
	    char current = formula.charAt(i);
	    // Set to a default (invalid) value.
	    char next = (char) -1;
	    if (i <= formula.length() - 2) {
		next = formula.charAt(i + 1);
	    }

	    if (isUpperCaseAlphabetic(current)) { // An element
		if (Character.isLowerCase(next)) { // Two-letter element symbol
		    last = getMolarMass(String.valueOf(current) + String.valueOf(next));
		    // Increment an additional time, because it's two letters.
		    i += 2;
		} else { // One-letter element symbol
		    last = getMolarMass(String.valueOf(current));
		    i++;
		}
		result += last;
	    } else if (Character.isDigit(current)) { // A number
		// Determine how large the number is.
		int size = 0;
		for (int j = i; j < formula.length(); j++) {
		    if (Character.isDigit(formula.charAt(j))) {
			size++;
		    } else {
			break;
		    }
		}
		// The mass has already been added once, so we only need to add it (n - 1)
		// times.
		int multiplier = (Integer.parseInt(formula.substring(i, i + size)) - 1);
		result += last * multiplier;
		// Increment
		i += size;
		last = 0.0;
	    } else if (current == '(') { // A sub-formula starting at the paranthesis
		String sub = formula.substring(i + 1);
		last = parseFormula(sub);
		// Skip past that which you just recursed over.
		i += sub.indexOf(")") + 2;
		result += last;
	    } else if (current == ')') { // End of sub-formula
		return result;
	    } else { // Unrecognized character
		System.out.println("What the fuck is this: " + current);
		i++;
	    }
	}
	return result;
    }
    
    /**
     * Post: Prompts the user to type in a chemical formula, and returns the formula in
     * String form.
     */
    private String getFormula() {
	System.out.print("Type in the compound: ");
	String line = console.nextLine();

	// Weird bug with nextLine() makes this necessary.
	if (line.length() == 0)
	    line = console.nextLine();

	return line;
    }

    /**
     * Post: Explains to the user what this program is for.
     */
    private static void printIntro() {
	System.out.println("This program computes the molar mass of a compound");
	System.out.println("in the example format: \"Ba(CH3CO2)2\"");
    }

    /**
     * Post: Pastes the "input" String into the user's system clipboard.
     */
    private static void pasteToClipboard(String input) {
	StringSelection selection = new StringSelection(String.valueOf(input));
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	clipboard.setContents(selection, selection);
    }

    /**
     * Post: Rounds "a" to the nearest hundredths place.
     */
    private static double hundredthsRound(double a) {
	return Math.round(a * 100.0) / 100.0;
    }

    /**
     * Post: Returns whether "ch" is an upper case alphabetic character.
     */
    private static boolean isUpperCaseAlphabetic(char ch) {
	return Character.isUpperCase(ch) && Character.isAlphabetic(ch);
    }

    /**
     * Pre: "symbol" must be a valid chemical symbol (case-sensitive).
     * 
     * Post: Returns the molar mass corresponding to the element "symbol" given.
     */
    private static double getMolarMass(String symbol) {
	switch (symbol) {
	case "H":
	    return 1.008;
	case "He":
	    return 4.003;
	case "Li":
	    return 6.941;
	case "Be":
	    return 9.012;
	case "B":
	    return 10.81;
	case "C":
	    return 12.01;
	case "N":
	    return 14.01;
	case "O":
	    return 16.00;
	case "F":
	    return 19.00;
	case "Ne":
	    return 20.18;
	case "Na":
	    return 23.00;
	case "Mg":
	    return 24.31;
	case "Al":
	    return 26.98;
	case "Si":
	    return 28.09;
	case "P":
	    return 30.97;
	case "S":
	    return 32.07;
	case "Cl":
	    return 35.45;
	case "Ar":
	    return 39.95;
	case "K":
	    return 39.10;
	case "Ca":
	    return 40.08;
	case "Sc":
	    return 44.96;
	case "Ti":
	    return 47.87;
	case "V":
	    return 50.94;
	case "Cr":
	    return 52.00;
	case "Mn":
	    return 54.94;
	case "Fe":
	    return 55.85;
	case "Co":
	    return 58.93;
	case "Ni":
	    return 58.69;
	case "Cu":
	    return 63.55;
	case "Zn":
	    return 65.39;
	case "Ga":
	    return 69.72;
	case "Ge":
	    return 72.64;
	case "As":
	    return 74.92;
	case "Se":
	    return 78.96;
	case "Br":
	    return 79.9;
	case "Kr":
	    return 83.8;
	case "Rb":
	    return 85.47;
	case "Sr":
	    return 87.62;
	case "Y":
	    return 88.91;
	case "Zr":
	    return 91.22;
	case "Nb":
	    return 92.91;
	case "Mo":
	    return 95.94;
	case "Tc":
	    return 98;
	case "Ru":
	    return 101.1;
	case "Rh":
	    return 102.9;
	case "Pd":
	    return 106.4;
	case "Ag":
	    return 107.9;
	case "Cd":
	    return 112.4;
	case "In":
	    return 114.8;
	case "Sn":
	    return 118.7;
	case "Sb":
	    return 121.8;
	case "Te":
	    return 127.6;
	case "I":
	    return 126.9;
	case "Xe":
	    return 131.3;
	case "Cs":
	    return 132.9;
	case "Ba":
	    return 137.3;
	case "Hf":
	    return 178.5;
	case "Ta":
	    return 180.9;
	case "W":
	    return 183.8;
	case "Re":
	    return 186.2;
	case "Os":
	    return 190.2;
	case "Ir":
	    return 192.2;
	case "Pt":
	    return 195.1;
	case "Au":
	    return 197.0;
	case "Hg":
	    return 200.6;
	case "Tl":
	    return 204.4;
	case "Pb":
	    return 207.2;
	case "Bi":
	    return 209.0;
	case "Po":
	    return 209.0;
	case "At":
	    return 210.0;
	case "Rn":
	    return 222.0;
	case "Fr":
	    return 223.0;
	case "Ra":
	    return 226.0;
	default:
	    return 0xFFFFFF;
	}
    }
}
