package com.chem.utilities.programs;

import java.util.Scanner;

import com.chem.utilities.Main;
import com.chem.utilities.commands.Command;

public class Menu implements Program {
    private Scanner console;
    
    public Menu(Scanner console) {
	this.console = console;
    }

    public void run() {
	System.out.print(">> ");
	System.out.flush();
	String input;
	while (!(input = console.nextLine()).isEmpty()) {
	    if (input.equals(Command.EXIT.name)) {
		Main.running = false;
		break;
	    } else if (input.equals(Command.HELP.name)) {
		printHelp();
		break;
	    } else if (input.equals(Command.MOLAR_MASS.name)) {
		Main.currentProgram = new MolarMassCalculator(console);
		break;
	    } else if (input.equals(Command.SI_CONVERTER.name)) {
		Main.currentProgram = new SIConverter(console);
		break;
	    } else {
		System.out.println("Unrecognized command \"" + input + "\"");
		System.out.flush();
		break;
	    }
	}
    }

    private void printHelp() {
	for (int i = 0; i < Command.values().length; i++) {
	    System.out.println("\t\"" + Command.values()[i].name + '\"');
	}
	System.out.flush();
    }
}
