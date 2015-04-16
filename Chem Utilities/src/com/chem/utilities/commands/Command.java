package com.chem.utilities.commands;

public enum Command {
    EXIT("exit"), HELP("help"), MOLAR_MASS("molarMass"), SI_CONVERTER(
	    "siConverter");

    public final String name;

    Command(String name) {
	this.name = name;
    }
}
