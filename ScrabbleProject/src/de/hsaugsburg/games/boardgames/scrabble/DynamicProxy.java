package de.hsaugsburg.games.boardgames.scrabble;

import de.hsaugsburg.games.boardgames.exceptions.IllegalPieceOperationException;
import de.hsaugsburg.games.boardgames.exceptions.InvalidStateException;
import de.hsaugsburg.games.boardgames.exceptions.OutsideBoardException;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine.State;

public class DynamicProxy implements IScrabbleEngine{

	@Override
	public void addPiece(LetterPiece piece, int row, int column)
			throws IllegalPieceOperationException, OutsideBoardException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engageState(State newState) throws InvalidStateException,
			IllegalPieceOperationException, OutsideBoardException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void givePieces() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePiece(int row, int column)
			throws IllegalPieceOperationException, OutsideBoardException {
		// TODO Auto-generated method stub
		
	}

}
