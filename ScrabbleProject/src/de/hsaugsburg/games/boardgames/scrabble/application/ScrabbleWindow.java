package de.hsaugsburg.games.boardgames.scrabble.application;

import java.io.IOException;
import java.util.logging.LogManager;

import java.lang.reflect.Proxy;
import de.hsaugsburg.games.boardgames.scrabble.EventLogger;
import de.hsaugsburg.games.boardgames.scrabble.IScrabbleEngine;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.RandomPool;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleBoard;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine;
import de.hsaugsburg.games.boardgames.scrabble.graphicalui.ScrabbleGui;

public class ScrabbleWindow {
	public static void main(String[] args) {
//		try {
//			LogManager.getLogManager().readConfiguration(ClassLoader.getSystemResourceAsStream("scrabble.properties"));
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
		IScrabbleEngine engine = new ScrabbleEngine(new ScrabbleBoard(), new RandomPool<LetterPiece>());
		IScrabbleEngine proxy = (IScrabbleEngine) Proxy.newProxyInstance(IScrabbleEngine.class.getClassLoader(),new Class[] { IScrabbleEngine.class },new EventLogger(engine));
		new ScrabbleGui("JScrabble", engine);
	}
	
}