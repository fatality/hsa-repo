package de.hsaugsburg.games.boardgames.scrabble.application;

import de.hsaugsburg.games.boardgames.scrabble.ScrabbleBoard;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.BoardView;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.CommandProcessor;

public class ScrabbleLauncher {
	
	/**
	 * @param args	are not used
	 */
	public static void main(String[] args) {
		final ScrabbleBoard board = new ScrabbleBoard();
		final BoardView view = new BoardView(board);
		final ScrabbleEngine engine = new ScrabbleEngine(board, view);
		final CommandProcessor processor = new CommandProcessor(engine, view);
		view.printHelp();
		processor.process();
	}
	
}
