package de.hsaugsburg.games.boardgames.scrabble;

import de.hsaugsburg.games.boardgames.exceptions.IllegalPieceOperationException;
import de.hsaugsburg.games.boardgames.exceptions.InvalidStateException;
import de.hsaugsburg.games.boardgames.exceptions.OutsideBoardException;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine.State;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy implements IScrabbleEngine{
	
	InvocationHandler logger = new EventLogger(null);

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
