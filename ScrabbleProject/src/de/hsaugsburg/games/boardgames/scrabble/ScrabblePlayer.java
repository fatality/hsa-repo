package de.hsaugsburg.games.boardgames.scrabble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import de.hsaugsburg.games.boardgames.Player;

public class ScrabblePlayer extends Player {
	
	private List<LetterPiece> myPieces = new ArrayList<LetterPiece>(7);
	
	public ScrabblePlayer(String s) {
		super(s);
	}
	
	public void receive(LetterPiece piece) {
		myPieces.add(piece);
	}
	
	public void receiveAll(List<LetterPiece> pieceList) {
		Iterator<LetterPiece> it  = pieceList.iterator();
		while (it.hasNext()) {
			myPieces.add(it.next());
		}
	}
	
	public void giveAway(LetterPiece piece) {
		myPieces.remove(piece);
	}
	
	public List<LetterPiece> getMyPieces(){
		return myPieces;
	}
	
}
