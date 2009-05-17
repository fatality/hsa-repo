package de.hsaugsburg.games.boardgames.test;

import static org.junit.Assert.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import de.hsaugsburg.games.boardgames.GridPoint;
import de.hsaugsburg.games.boardgames.scrabble.consoleui.BoardView;
import de.hsaugsburg.games.boardgames.exceptions.GameException;
import de.hsaugsburg.games.boardgames.scrabble.LetterPiece;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleBoard;
import de.hsaugsburg.games.boardgames.scrabble.ScrabblePlayer;
import de.hsaugsburg.games.boardgames.scrabble.WordManager;

public class WordManagerTest {
	
	Logger logger = Logger.getLogger(WordManager.class.getName());
	ScrabbleBoard board;
	WordManager manager;
	BoardView view;
	ScrabblePlayer player;
	
	@Before
	public void setUp() throws Exception {		
		board = new ScrabbleBoard();
		view = new BoardView(board);
		manager = new WordManager(board);
		logger.setLevel(Level.ALL);
	}

	@Test
	public void testGetPorducedWords() {
		player = new ScrabblePlayer("Test");
		manager.setPlayer(player);
		board.reset();
		board.putPiece(LetterPiece.M, 7, 6); board.getDetails(7, 6).setFixed(); 
		board.putPiece(LetterPiece.A, 8, 6); board.getDetails(8, 6).setFixed();
		board.putPiece(LetterPiece.M, 9, 6); board.getDetails(9, 6).setFixed();
		board.putPiece(LetterPiece.A, 7, 7); board.getDetails(7, 7).setFixed();
		board.putPiece(LetterPiece.B, 8, 7); manager.getLetterList().add(new GridPoint(8, 7));
		board.putPiece(LetterPiece.K, 9, 7); board.getDetails(9, 7).setFixed();
		board.putPiece(LetterPiece.M, 7, 8); board.getDetails(7, 8).setFixed();
		board.putPiece(LetterPiece.C, 8, 8); board.getDetails(8, 8).setFixed();
		board.putPiece(LetterPiece.M, 9, 8); board.getDetails(9, 8).setFixed();
		board.putPiece(LetterPiece.D, 8, 9); manager.getLetterList().add(new GridPoint(8, 9));
		board.putPiece(LetterPiece.M, 5, 9); board.getDetails(5, 9).setFixed(); 
		board.putPiece(LetterPiece.M, 6, 9); board.getDetails(6, 9).setFixed(); 
		board.putPiece(LetterPiece.M, 7, 9); board.getDetails(7, 9).setFixed(); 
		board.putPiece(LetterPiece.M, 7, 10); board.getDetails(7, 10).setFixed();
		board.putPiece(LetterPiece.E, 8, 10); manager.getLetterList().add(new GridPoint(8, 10));
		board.putPiece(LetterPiece.F, 8, 11); manager.getLetterList().add(new GridPoint(8, 11));
		board.putPiece(LetterPiece.G, 8, 12); manager.getLetterList().add(new GridPoint(8, 12));
		board.putPiece(LetterPiece.H, 8, 13); manager.getLetterList().add(new GridPoint(8, 13));
		board.putPiece(LetterPiece.X, 8, 14); manager.getLetterList().add(new GridPoint(8, 14));
		
		/*
		 *    1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 
		 *	A___ .  .  .. .  .  . ___ .  .  .  .. .  . ___A
		 *	B .  __ .  .  . ... .  .  . ... .  .  .  __ . B
		 *	C .  .  __ .  .  .  .. .  .. .  .  .  __ .  . C
		 *	D .. .  .  __ .  .  .  .. .  .  .  __ .  .  ..D
		 *	E .  .  .  .  __ .  .  .  .  .  __ .  .  .  . E
		 *	F . ... .  .  . ... .  .  . [M] .  .  . ... . F
		 *	G .  .  .. .  .  .  .. .  ..[M] .  .  .. .  . G
		 *	H___ .  .  .. .  . [M][A][M][M][M] .. .  . ___H
		 *	I .  .  .. .  .  . [A] B [C] D  E  F  G  H  X I
		 *	J . ... .  .  . ...[M][K][M]... .  .  . ... . J
		 *	K .  .  .  .  __ .  .  .  .  .  __ .  .  .  . K
		 *	L .. .  .  __ .  .  .  .. .  .  .  __ .  .  ..L
		 *	M .  .  __ .  .  .  .. .  .. .  .  .  __ .  . M
		 *	N .  __ .  .  . ... .  .  . ... .  .  .  __ . N
		 *	O___ .  .  .. .  .  . ___ .  .  .  .. .  . ___O
  		 *	  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 
		 */
		
		view.render();
		
		try {
			manager.commitLetterSequence(false);
		}
		catch (GameException e) {
			fail(e.getMessage());
		}
		List<String> words = manager.getPorducedWords();
		assertTrue(words.get(0).compareTo("ABCDEFGHX") == 0);
		assertTrue(words.get(1).compareTo("ABK") == 0);
		assertTrue(words.get(2).compareTo("MMMD") == 0);
		assertTrue(words.get(3).compareTo("ME") == 0);
		manager.calcScore();
		assertTrue(manager.getPoints() == 45);
		manager.getLetterList().clear();
	}
	
	@Test
	public void testGetPorducedWords2() {
		player = new ScrabblePlayer("Test");
		manager.setPlayer(player);
		board.reset();
		board.putPiece(LetterPiece.A, 6, 8); board.getDetails(6, 8).setFixed(); 
		board.putPiece(LetterPiece.B, 7, 8); board.getDetails(7, 8).setFixed();
		board.putPiece(LetterPiece.C, 7, 7); board.getDetails(7, 7).setFixed();
		board.putPiece(LetterPiece.X, 6, 7); manager.getLetterList().add(new GridPoint(6, 7));
		
		/*
		 *    1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 
		 *	A___ .  .  .. .  .  . ___ .  .  .  .. .  . ___A
		 *	B .  __ .  .  . ... .  .  . ... .  .  .  __ . B
		 *	C .  .  __ .  .  .  .. .  .. .  .  .  __ .  . C
		 *	D .. .  .  __ .  .  .  .. .  .  .  __ .  .  ..D
		 *	E .  .  .  .  __ .  .  .  .  .  __ .  .  .  . E
		 *	F . ... .  .  . ... .  .  . ... .  .  . ... . F
		 *	G .  .  .. .  .  .  .. X [A] .  .  .  .. .  . G
		 *	H___ .  .  .. .  .  . [C][B] .  .  .. .  . ___H
		 *	I .  .  .. .  .  .  .. .  .. .  .  .  .. .  . I
		 *	J . ... .  .  . ... .  .  . ... .  .  . ... . J
		 *	K .  .  .  .  __ .  .  .  .  .  __ .  .  .  . K
		 *	L .. .  .  __ .  .  .  .. .  .  .  __ .  .  ..L
		 *	M .  .  __ .  .  .  .. .  .. .  .  .  __ .  . M
		 *	N .  __ .  .  . ... .  .  . ... .  .  .  __ . N
		 *	O___ .  .  .. .  .  . ___ .  .  .  .. .  . ___O
  		 *	  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 
		 */
		
		view.render();
		
		try {
			manager.commitLetterSequence(false);
		}
		catch (GameException e) {
			fail(e.getMessage());
		}
		List<String> words = manager.getPorducedWords();
		assertTrue(words.get(0).compareTo("XA") == 0);
		assertTrue(words.get(1).compareTo("XC") == 0);
		manager.calcScore();
		assertTrue(manager.getPoints() == 13);
		manager.getLetterList().clear();
	}
	
}
