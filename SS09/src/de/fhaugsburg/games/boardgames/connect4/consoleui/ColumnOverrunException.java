package de.fhaugsburg.games.boardgames.connect4.consoleui;

@SuppressWarnings("serial")
public class ColumnOverrunException extends RuntimeException {
	
	public ColumnOverrunException() {
		super("THIS ROW IS ALREADY FILLED");
	}
	
}