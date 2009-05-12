package de.fhaugsburg.games.boardgames.scrabble.management;


@SuppressWarnings("serial")
public class IllegalPieceOperationException extends RuntimeException{

	public IllegalPieceOperationException(){
		super("You have to put your first word in the middle of the board");
	}
}
