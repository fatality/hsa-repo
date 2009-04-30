package de.fhaugsburg.games.boardgames.scrabble.application;

import java.io.IOException;

import de.fhaugsburg.games.boardgames.scrabble.consoleui.CP_Scrabble;

public class ScrabbleLauncher {
	/**
	 * Startet das ScrabbleSpiel
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args) throws IOException {
		CP_Scrabble cp_scrabble = new CP_Scrabble();
		cp_scrabble.scrabble();
	}
	
}