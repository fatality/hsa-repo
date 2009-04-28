package de.fhaugsburg.games.boardgames.scrabble;

import de.fhaugsburg.games.boardgames.IPiece;
import de.fhaugsburg.games.boardgames.LetterPiece;
import de.fhaugsburg.games.boardgames.Player;
import de.fhaugsburg.games.boardgames.SquareMultiplier;

public class ScrabbleEngine {
	
	@SuppressWarnings("unused")
	private IPiece ipiece;
	
	public ScrabbleBoard board = new ScrabbleBoard(15,15);
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player currentPlayer = player1;
	private int amountPoints = 0;
	private int WordMultiplier = 1;
	
	public void setPiece(int columns, int rows, LetterPiece toSet) {
		board.putPiece(columns, rows, toSet);
		
		if ((board.getSquare(columns, rows).multi == SquareMultiplier.WordMultiplier_2) || (board.getSquare(columns, rows).multi == SquareMultiplier.WordMultiplier_3)) {
			WordMultiplier = WordMultiplier * board.getSquare(columns, rows).getMultiplier();
			amountPoints += toSet.getPoints() * 1;	
		} else {
			amountPoints += toSet.getPoints() * board.getSquare(columns, rows).getMultiplier();
		}
		
	}
	
	
	public void finishedWord() {
		currentPlayer.addPoints(amountPoints * WordMultiplier);
		WordMultiplier = 1;
		amountPoints = 0;
		System.out.println(currentPlayer.getPoints());
		
		if(currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
		
	}
	
}