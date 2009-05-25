package de.hsaugsburg.games.boardgames.scrabble;

import de.hsaugsburg.games.boardgames.exceptions.IllegalPieceOperationException;
import de.hsaugsburg.games.boardgames.exceptions.InvalidStateException;
import de.hsaugsburg.games.boardgames.exceptions.OutsideBoardException;
import de.hsaugsburg.games.boardgames.scrabble.ScrabbleEngine.State;

public interface IScrabbleEngine {

	public abstract void engageState(State newState)
			throws InvalidStateException, IllegalPieceOperationException,
			OutsideBoardException;

	public abstract void givePieces();

	public abstract void addPiece(LetterPiece piece, int row, int column)
			throws IllegalPieceOperationException, OutsideBoardException;

	public abstract void removePiece(int row, int column)
			throws IllegalPieceOperationException, OutsideBoardException;

}
