package com.chem.utilities;

import java.util.Scanner;

import com.chem.utilities.programs.Menu;
import com.chem.utilities.programs.Program;

public class Main {
    private static final String VERSION = "1.3a";
    public static Program currentProgram;
    public static boolean running;

    
    public static void main(String[] args) {
	System.out.println("Welcome to version " + VERSION
		+ " of Doob's Chem 152 utilities!");
	System.out
		.println("If you are using this, your name is probably \"Dick-Tits McGee.\"");
	System.out.println("Type \"help\" if you're an autistic faglord cock goblin.");
	System.out.println();

	Scanner console = new Scanner(System.in);
	running = true;
	currentProgram = new Menu(console);

	while (running) {
	    currentProgram.run();
	}
	console.close();
    }
}
