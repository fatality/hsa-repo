package de.fhaugsburg.games.boardgames.scrabble.management;

@SuppressWarnings("serial")
public class UnsupportetOperationException extends RuntimeException {
	
	public UnsupportetOperationException() {
		super("Diese Funktion ist nicht enthalten!");
	}
	
}