package de.hsaugsburg.games.boardgames.scrabble;

import de.hsaugsburg.games.boardgames.exceptions.IllegalPieceOperationException;
import de.hsaugsburg.games.boardgames.exceptions.InvalidStateException;
import de.hsaugsburg.games.boardgames.exceptions.OutsideBoardException;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine.State;

public class ScrabbleProxy implements IScrabbleEngine{

	private IScrabbleEngine ise;
	
	public ScrabbleProxy(IScrabbleEngine ise){
		this.ise = ise;
	}
	
	@Override
	public void addPiece(LetterPiece piece, int row, int column)
			throws IllegalPieceOperationException, OutsideBoardException {
		System.out.println("* vor Aufruf von IScrabble.addPiece, piece: " + piece +
				" row: " + row + " column: " + column);
		ise.addPiece(piece, row, column);
	}

	@Override
	public void engageState(State newState) throws InvalidStateException,
			IllegalPieceOperationException, OutsideBoardException {
		System.out.println("* vor Aufruf von IScrabble.engageState, newState: " + newState);
		ise.engageState(newState);
	}

	@Override
	public void givePieces() {
		
		ise.givePieces();
	}

	@Override
	public void removePiece(int row, int column)
			throws IllegalPieceOperationException, OutsideBoardException {
		System.out.println("* vor Aufruf von IScrabble.removePiece, row: " + row +
				"column:" + column);
		ise.removePiece(row, column);
	}

}
