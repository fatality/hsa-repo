package de.fhaugsburg.games.boardgames.connect4.application;

import de.fhaugsburg.games.boardgames.connect4.consoleui.CommandProcessor;

public class Connect4Launcher {

	public static void main (String[] args) throws Exception {
		CommandProcessor cp = new CommandProcessor();
		cp.game();
	}

}