package de.fhaugsburg.games.boardgames.scrabble;

import java.util.ArrayList;
import java.util.List;
import de.fhaugsburg.games.boardgames.IPiece;
import de.fhaugsburg.games.boardgames.LetterPiece;
import de.fhaugsburg.games.boardgames.Player;
import de.fhaugsburg.games.boardgames.SquareMultiplier;
import de.fhaugsburg.games.boardgames.scrabble.management.CircularList;
import de.fhaugsburg.games.boardgames.scrabble.management.RandomPool;
import de.fhaugsburg.games.boardgames.scrabble.management.ScrabblePlayer;

public class ScrabbleEngine {
	
	@SuppressWarnings("unused")
	private IPiece ipiece;
	public ScrabbleBoard board = new ScrabbleBoard(15,15);
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player currentPlayer = player1;
	private int amountPoints = 0;
	private int WordMultiplier = 1;
	
	@SuppressWarnings("unchecked")
	private CircularList list;
	private RandomPool<LetterPiece> randompool;
	private ScrabblePlayer scrabbleplayer;
	
	@SuppressWarnings("unchecked")
	public ScrabbleEngine() {
		list = new CircularList();
		randompool = new RandomPool<LetterPiece>();
		board = new ScrabbleBoard(15, 15);
		scrabbleplayer = (ScrabblePlayer) list.getCurrent();
		amountPoints = 0;
		addNewPlayer();
	}
	
	/**
	 * Setzt den Spielstein
	 * @param columns
	 * @param rows
	 * @param toSet
	 */
	public void setPiece(int columns, int rows, LetterPiece toSet) {
		board.putPiece(columns, rows, toSet);
		
		if ((board.getSquare(columns, rows).multi == SquareMultiplier.WordMultiplier_2) || (board.getSquare(columns, rows).multi == SquareMultiplier.WordMultiplier_3)) {
			WordMultiplier = WordMultiplier * board.getSquare(columns, rows).getMultiplier();
			amountPoints += toSet.getPoints() * 1;	
		} else {
			amountPoints += toSet.getPoints() * board.getSquare(columns, rows).getMultiplier();
		}
		
	}
	
	/**
	 * Zählt die Punkte des Wortes zusammen
	 */
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
	
	public ArrayList<LetterPiece> getSevenNewPieces() {
		ArrayList<LetterPiece> newPieces = new ArrayList<LetterPiece>();
		
		for (int i = 0; i<7; i++) {
			newPieces.add(randompool.take());
		}
		return newPieces;
	}

	@SuppressWarnings("unchecked")
	public void addNewPlayer() {
		list.add(new ScrabblePlayer(getSevenNewPieces()));
	}

	public String getPlayerName() {
		return currentPlayer.getName();
	}

	public int getPlayerPoints()  {
		return currentPlayer.getPoints();
	}
	
	public ArrayList<LetterPiece> getCompleteNewStones() {
		ArrayList<LetterPiece> newStones = new ArrayList<LetterPiece>();
		for (int i = 0; i<7; i++)
			newStones.add(randompool.take());
		return newStones;
	}
	
	/**
	 * Impl.: giveAway(); ScrabblePlayer
	 * @param piece
	 */
	public void giveAway(LetterPiece piece) {
		scrabbleplayer.giveAway(piece);
		randompool.put(piece);
		scrabbleplayer.receive(randompool.take());
	}

	public void receiveAll() {
		List<LetterPiece> pieceList = new ArrayList<LetterPiece>(0);
		for (int i = 0; i<7; i++) {
			pieceList.add(randompool.take());
		}
		
		for (int i = scrabbleplayer.myPieces.size()-1; i>=0; i--) {
			randompool.put(scrabbleplayer.myPieces.remove(i));
		}
		scrabbleplayer.receiveAll(pieceList);
	}

	/**
	 * Impl.: receive(); ScrabblePlayer
	 */
	public void receive() {
		while (scrabbleplayer.myPieces.size() < 7) {
			scrabbleplayer.receive(randompool.take());
		}
	}
	
}