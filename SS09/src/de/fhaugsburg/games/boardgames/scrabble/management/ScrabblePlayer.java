package de.fhaugsburg.games.boardgames.scrabble.management;

import java.util.ArrayList;
import java.util.List;

import de.fhaugsburg.games.boardgames.LetterPiece;

public class ScrabblePlayer {
	
	public List<LetterPiece> myPieces = new ArrayList<LetterPiece>();
	
	public ScrabblePlayer(ArrayList<LetterPiece> initialList) {
		myPieces = new ArrayList<LetterPiece>(7);
		receiveAll(initialList);
	}
	
	public ScrabblePlayer() {
		// TODO Auto-generated constructor stub
	}

	public void receive(LetterPiece piece) {
		myPieces.add(piece);
	}
	
	public void receiveAll(List<LetterPiece> pieceList) {
		myPieces.addAll(pieceList);
	}
	
	public void giveAway(LetterPiece piece) {
		if(myPieces.contains(piece)) {
			myPieces.remove(myPieces.indexOf(piece));
		} else {
//			throw new Exception
		}
	}
}