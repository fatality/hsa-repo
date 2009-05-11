package de.fhaugsburg.games.boardgames.connect4.application;

import java.io.IOException;

import de.fhaugsburg.games.boardgames.connect4.consoleui.CommandProcessor;

public class Connect4Launcher {
	
	public static void main (String[] args) throws IOException {
		CommandProcessor cp = new CommandProcessor();
		cp.game();
	}
	
}