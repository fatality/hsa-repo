package de.hsaugsburg.games.boardgames.scrabble.application;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import de.hsaugsburg.games.boardgames.scrabble.EventLogger;
import de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine;
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
		final IScrabbleEngine engine = new ScrabbleEngine(board, view);
		final CommandProcessor processor = new CommandProcessor(engine, view);
		view.printHelp();
		processor.process();


		
	}
	
}
